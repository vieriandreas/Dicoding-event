package com.example.dicodingevent.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favorite: FavoriteEntity)

    @Query("SELECT * from favorite")
    fun getAllFavorite(): LiveData<List<FavoriteEntity>>

    @Query("SELECT * FROM favorite WHERE id = :id LIMIT 1")
    fun isFavoriteEvent(id: Int) : LiveData<FavoriteEntity>

    @Delete
    fun delete(favorite: FavoriteEntity)

}