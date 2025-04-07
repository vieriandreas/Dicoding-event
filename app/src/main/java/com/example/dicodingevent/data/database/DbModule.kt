package com.example.dicodingevent.data.database

import android.content.Context
import androidx.room.Room

class DbModule(private val context: Context) {
    private val db = Room.databaseBuilder(
        context,
        FavoriteDatabase::class.java, "favorite.db"
    ).build()

    val favoriteDao = db.favoriteDao()

}
