package com.example.movieapp.data.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val id: Int,
    val filmAd: String,
    val yonetmenAd: String,
    val filmAciklama: String,
    var isWatched: Boolean,
    val date: String,
) : Parcelable