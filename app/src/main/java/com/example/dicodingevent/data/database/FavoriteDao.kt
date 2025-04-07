package com.example.dicodingevent.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favorite: FavoriteEntity)

    @Query("SELECT * from favorite")
    fun getAllFavorite(): LiveData<List<FavoriteEntity>>

    @Query("SELECT EXISTS(SELECT * from favorite WHERE id = :id)")
    fun isFavoriteEvent(id: Int) : LiveData<Boolean>

    @Query("DELETE FROM favorite WHERE id = :id")
    fun delete(id: Int)

}