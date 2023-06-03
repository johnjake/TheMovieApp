package com.themovieguide.data.sources.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: MutableList<T>): List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(obj: T)

    @Delete
    fun delete(obj: T)
}
