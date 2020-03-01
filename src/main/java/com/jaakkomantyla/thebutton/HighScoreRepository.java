package com.jaakkomantyla.thebutton;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
//TODO  removing old scores after certain inactivity period
/**
 * The interface High score repository. Saves players scores. Extends Springs CrudRepository.
 * Spring creates database and queries according to this interface and application properties.
 */
public interface HighScoreRepository extends CrudRepository<Score, String> {

    /**
     * Spring generates query to find top 10 scores ordered by points in descending order.
     *
     * @return  list of top 10 scores
     */
    List<Score> findTop10ByOrderByPointsDesc();
}
