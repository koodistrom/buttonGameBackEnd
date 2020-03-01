package com.jaakkomantyla.thebutton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * Controller for requests.
 */
@RestController
public class MyController {
    /**
     * The Push count repository.
     */
    @Autowired
    PushCountRepository pushCountRepository;

    /**
     * The High score repository.
     */
    @Autowired
    HighScoreRepository highScoreRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){

        return "hello world";

    }

    /**
     * Mapping for play Post request. Adds one to push counter, checks for win, adds players new points to highscore,
     * and returns info to front end.
     *
     * @param playerInfo before click
     * @return the player info after click
     */
    @Transactional
    @RequestMapping(value = "/play", method = RequestMethod.POST)
    public PlayerInfo play( @RequestBody  PlayerInfo playerInfo){
        String id = playerInfo.getId();
        Optional<Score> score = highScoreRepository.findById(playerInfo.getId());

        Integer points = score.get().getPoints();
        String name =  score.get().getName();

        int newCount = -1;
        Optional<PushCounter> pushCounter= pushCountRepository.findById(1);

        System.out.println("points " + points + "name " + name);

        if(points == null){
            points = 0;
        }

        if(pushCounter.isPresent() && points>0){
            points --;
            newCount= ((pushCounter.get().getCounter()+1)%500);

            int pointsWon = ClickCountUtils.checkWin(newCount);
            boolean win = !(pointsWon==0);
            int distanceToNextPrice = ClickCountUtils.distanceToNextPrice(newCount);
            pushCountRepository.setCountForPushCounter(newCount,1);
            System.out.println(newCount);
            points +=pointsWon;

            score.get().setPoints(points);
            score.get().setName(name);
            highScoreRepository.save(score.get());

            return new PlayerInfo(points, distanceToNextPrice, win, name, id);
        }else {
            return new PlayerInfo(points, -1, false, name, id);
        }

    }

    /**
     * Mapping for current-session GET request. Checks for points and name of player from session
     * and returns them to front end.
     *
     * @param playerInfo object holding  users id
     * @return the player info
     */
    @Transactional
    @RequestMapping(value = "/current-session", method = RequestMethod.POST)
    public PlayerInfo current( @RequestBody  PlayerInfo playerInfo){
        String id = playerInfo.getId();
        Optional<PushCounter> pushCounter= pushCountRepository.findById(1);
        Optional<Score> playerScore= highScoreRepository.findById(id);
        Integer points = 0;
        String name = null;
        if(playerScore.isPresent()){
            points = playerScore.get().getPoints();
            name =  playerScore.get().getName();
        }
        if(pushCounter.isPresent()){
            int count= (pushCounter.get().getCounter());
            int distanceToNextPrice = ClickCountUtils.distanceToNextPrice(count);

            return new PlayerInfo(points, distanceToNextPrice, false, name, id);
        }else {
            return (null);
        }

    }

    /**
     * Mapping for hs GET request.
     *
     * @return  top 10 player names, scores and ids as a list
     */
    @Transactional
    @RequestMapping(value = "/hs", method = RequestMethod.GET)
    public List<Score> hs(){

        for(int i = 0; i<15; i++){
            highScoreRepository.save( new Score(i +"", "name"+ i ,(int) (Math.random()*30)));
        }

        return highScoreRepository.findTop10ByOrderByPointsDesc();

    }

    /**
     * Mapping for name POST request. Saves posted name to session and sets points to 20.
     *
     * @param playerInfo the player info containing the name
     * @return the player info
     */
    @Transactional
    @RequestMapping(value = "/name", method = RequestMethod.POST,  consumes = "application/json", produces = "application/json")
    public PlayerInfo name( @RequestBody  PlayerInfo playerInfo){
        String id = playerInfo.getId();
        String name = playerInfo.getName();

        Optional<PushCounter> pushCounter= pushCountRepository.findById(1);
        highScoreRepository.save(new Score(id, name, 20));

        int distanceToNextPrice =10;
        if(pushCounter.isPresent()) {
            int count = (pushCounter.get().getCounter());
            distanceToNextPrice = ClickCountUtils.distanceToNextPrice(count);
        }
        return new PlayerInfo(20, distanceToNextPrice, false, name, playerInfo.getId());
    }


}
