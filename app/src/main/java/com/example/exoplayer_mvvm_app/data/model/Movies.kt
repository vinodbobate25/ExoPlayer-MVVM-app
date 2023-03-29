package com.example.exoplayer_mvvm_app.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "movies")
data class Movies(
    val adult: Boolean?=false,
    val backdrop_path: String?="",
    val genre_ids: List<Int>,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val media_type: String?="",
    val original_language: String?="",
    val original_title: String?="",
    val overview: String?="",
    val popularity: Double?=0.0,
    val poster_path: String?="",
    val release_date: String?="",
    val title: String?="",
    val video: Boolean?=false,
    val vote_average: Double?=0.0,
    val vote_count: Int?=0
):Parcelable