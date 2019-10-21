package com.floatingjava.planner.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DateSavedRepository extends JpaRepository<DateSaved, Long> {
    //If something does exist, and it is X minutes old, rewrite the saved timer.
    //because we're using ID of 1 for SQL verification.
    //If I were to redo it again, I would just limit the response to the last value in the table and validate against that,
    //vice resetting ID 1 every time.
    //or if we could just pull directly from their database then all is well.  :)
    @Query(value = "update date_saved set created_at_minutes = ?1", nativeQuery = true)
    public void updatedOurTime(long time);
    //If nothing exists, this would do the initial save to database.
    @Query(value = "select count(*) from date_saved", nativeQuery = true)
    public int findCountOfRows();
}
