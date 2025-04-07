package com.example.dicodingevent.data.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favorite")
data class FavoriteEntity(
    @ColumnInfo("summary")
    val summary: String,

    @ColumnInfo("mediaCover")
    val mediaCover: String,

    @ColumnInfo("registrants")
    val registrants: Int,

    @ColumnInfo("imageLogo")
    val imageLogo: String,

    @ColumnInfo("link")
    val link: String,

    @ColumnInfo("description")
    val description: String,

    @ColumnInfo("ownerName")
    val ownerName: String,

    @ColumnInfo("cityName")
    val cityName: String,

    @ColumnInfo("quota")
    val quota: Int,

    @ColumnInfo("name")
    val name: String,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id: Int,

    @ColumnInfo("beginTime")
    val beginTime: String,

    @ColumnInfo("endTime")
    val endTime: String,

    @ColumnInfo("category")
    val category: String
) : Parcelable
