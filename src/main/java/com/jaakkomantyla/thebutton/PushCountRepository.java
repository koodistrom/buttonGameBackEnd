package com.jaakkomantyla.thebutton;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * The interface Push count repository. Saves push counter that tracks when points are won.
 * Extends Springs CrudRepository.
 * Spring creates database and queries according to this interface and application properties.
 */
public interface PushCountRepository extends CrudRepository<PushCounter, Integer> {

    /**
     * Generates query for Setting new count for push counter.
     *
     * @param counter the count
     * @param id      the id of counter (needed for the database)
     * @return the count of push counter
     */
    @Modifying
    @Query("update PushCounter pc set pc.counter = ?1 where pc.id = ?2")
    int setCountForPushCounter(Integer counter, Integer id);

}
