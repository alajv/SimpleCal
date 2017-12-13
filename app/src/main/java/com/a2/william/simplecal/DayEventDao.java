package com.a2.william.simplecal;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by William on 2017-12-12.
 */
@Dao
public interface DayEventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addDayEvent(DayEvent dayEvent);

    @Query("select * from dayevent")
    public List<DayEvent> getAllDateEvents();

    @Query("select * from dayevent where " +
            " year = :year AND month = :month AND dayOfMonth = :dayOfMonth " +
            "order by startTime")
    public List<DayEvent> getDayEventsFromDB(int year, int month, int dayOfMonth);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateDateEvent(DayEvent dayEvent);

    @Query("delete from dayevent")
    void removeAllDayEvents();

    @Query("delete from dayevent where " +
            " year < :year and month < :month and dayOfMonth < :dayOfMonth")
    void removeExpiredEvents(int year, int month, int dayOfMonth);
}
