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

    /**
     * Mapping for play Post request. Adds one to push counter, checks for win, adds players new points to highscore,
     * and returns info to front end.
     *
     * @param session the session
     * @return the player info
     */
    @Transactional
    @RequestMapping(value = "/play", method = RequestMethod.POST)
    public PlayerInfo play( HttpSession session){
        int newCount = -1;
        Optional<PushCounter> pushCounter= pushCountRepository.findById(1);
        Integer points = (Integer) session.getAttribute("SESSION_POINTS");
        String name =  (String) session.getAttribute("SESSION_NAME");

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
            session.setAttribute("SESSION_POINTS", points);
            Optional<Score> score = highScoreRepository.findById(session.getId());
            if(score.isPresent()){
                score.get().setPoints(points);
                score.get().setName(name);
                highScoreRepository.save(score.get());
            }else{
                highScoreRepository.save(new Score(session.getId(), name, points));
            }
            return new PlayerInfo(points, distanceToNextPrice, win, name);
        }else {
            return new PlayerInfo(points, -1, false, name);
        }

    }

    /**
     * Mapping for current-session GET request. Checks for points and name of player from session
     * and returns them to front end.
     *
     * @param session the session
     * @return the player info
     */
    @Transactional
    @RequestMapping(value = "/current-session", method = RequestMethod.GET)
    public PlayerInfo current( HttpSession session){

        Optional<PushCounter> pushCounter= pushCountRepository.findById(1);

        if(pushCounter.isPresent()){
            int count= (pushCounter.get().getCounter());
            int distanceToNextPrice = ClickCountUtils.distanceToNextPrice(count);
            Integer points = (Integer) session.getAttribute("SESSION_POINTS");
            String name =  (String) session.getAttribute("SESSION_NAME");
            return new PlayerInfo(points, distanceToNextPrice, false, name);
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

        return highScoreRepository.findTop10ByOrderByPointsDesc();

    }

    /**
     * Mapping for name POST request. Saves posted name to session and sets points to 20.
     *
     * @param playerInfo the player info containing the name
     * @param session    the session
     * @return the player info
     */
    @Transactional
    @RequestMapping(value = "/name", method = RequestMethod.POST,  consumes = "application/json", produces = "application/json")
    public PlayerInfo name( @RequestBody  PlayerInfo playerInfo, HttpSession session){

        Optional<PushCounter> pushCounter= pushCountRepository.findById(1);
        System.out.println(playerInfo);
        session.setAttribute("SESSION_NAME", playerInfo.getName());
        String name = (String) session.getAttribute("SESSION_NAME");
        session.setAttribute("SESSION_POINTS", 20);
        int distanceToNextPrice =10;
        if(pushCounter.isPresent()) {
            int count = (pushCounter.get().getCounter());
            distanceToNextPrice = ClickCountUtils.distanceToNextPrice(count);
        }
        return new PlayerInfo(20, distanceToNextPrice, false, name);
    }


}
