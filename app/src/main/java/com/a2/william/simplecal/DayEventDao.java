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

    /*
    Fills List with all dayEvents from specified day
     */
    @Query("select * from dayevent where " +
            " year = :year AND month = :month AND dayOfMonth = :dayOfMonth " +
            "order by startTime")
    List<DayEvent> getDayEventsFromDB(int year, int month, int dayOfMonth);

    /*
    Function for this not implemented yet.
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateDateEvent(DayEvent dayEvent);

    @Query("delete from dayevent")
    void removeAllDayEvents();

    /*
    Removes all passed dayEvents from DB. Gets called with
    current day in MainActivity.
     */
    @Query("delete from dayevent where " +
            " year < :year and month < :month and dayOfMonth < :dayOfMonth")
    void removeExpiredEvents(int year, int month, int dayOfMonth);

    /*
    Removes specific dayEvent from DB.
     */
    @Query("delete from dayevent where " +
            "year = :year and month = :month and dayOfMonth = :dayOfMonth and eventName = :eventName and id = :id")
    void deleteDayEvent(int year, int month, int dayOfMonth, String eventName, int id);
}
