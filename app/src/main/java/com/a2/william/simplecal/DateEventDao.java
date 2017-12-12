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
public interface DateEventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addDateEvent(DateEvent dateEvent);

    @Query("select * from dateevent")
    public List<DateEvent> getAllDateEvents();

    @Query("select * from dateevent where " +
            " year = :year AND month = :month AND dayOfMonth = :dayOfMonth " +
            "order by startTime")
    public List<DateEvent> getEventsWhere(int year, int month, int dayOfMonth);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateDateEvent(DateEvent dateEvent);

    @Query("delete from dateevent")
    void removeAllDateEvents();
}
