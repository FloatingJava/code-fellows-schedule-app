package com.floatingjava.planner.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DateSavedRepository extends JpaRepository<DateSaved, Long> {
    @Query(value = "update date_saved set created_at_minutes = ?1", nativeQuery = true)
    public void updatedOurTime(long time);
    @Query(value = "select count(*) from date_saved", nativeQuery = true)
    public int findCountOfRows();
}
