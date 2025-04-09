package com.example.dicodingevent.data.database

import android.app.Application
import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private val favoriteDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteDatabase.getDatabase(application)
        favoriteDao = db.favoriteDao()
    }

    fun getAllFavorite(): LiveData<List<FavoriteEntity>> = favoriteDao.getAllFavorite()

    fun insert(favorite: FavoriteEntity) {
        executorService.execute { favoriteDao.insert(favorite) }
    }

    fun isFavoriteEvent(id: FavoriteEntity) {
        executorService.execute { favoriteDao.delete(id) }
    }

    fun delete(id: FavoriteEntity) {
        executorService.execute { favoriteDao.delete(id) }
    }
}


