package com.example.movieapp.data.source

import com.example.movieapp.data.model.Film

object Database {
    private val filmList = mutableListOf<Film>()
    private val izlenecekFilmList = mutableListOf<Film>()
    private val izlenenFilmList = mutableListOf<Film>()

    fun getFilmIndex(film: Film) = filmList.indexOf(film)
    fun getIzlenenFilm() = izlenenFilmList
    fun getIzlenecekFilm() = izlenecekFilmList

    fun izlenecekFilmEkle(
         filmAd: String,
         yonetmenAd: String,
         desc: String,
         isWatched: Boolean,
         date: String
    )
    {
        izlenecekFilmList.add(
            Film(
                id =  (filmList.lastOrNull()?.id ?: 0) + 1,
                filmAd,
                yonetmenAd,
                desc,
                isWatched,
                date,
            )
        )
    }

    fun izlenenFilmEkle(
        filmAd: String,
        yonetmenAd: String,
        desc: String,
        isWatched: Boolean,
        date: String
    )
    {
        izlenenFilmList.add(
            Film(
            id =  (filmList.lastOrNull()?.id ?: 0) + 1,
            filmAd,
            yonetmenAd,
            desc,
            isWatched,
            date,
        )
        )
    }



    fun filmSil(film: Film)
    {
        filmList.remove(film)
        izlenecekFilmList.remove(film)
        izlenenFilmList.remove(film)

    }

    fun izlenenEkle(film: Film)
    {
        izlenecekFilmList.remove(film)
        film.isWatched = false
        izlenenFilmList.add(film)
    }

    fun izlenecekEkle(film: Film)
    {
        izlenenFilmList.remove(film)
        film.isWatched = true
        izlenecekFilmList.add(film)
    }
/*
    fun filmEkle
                (
        filmAd: String,
        yonetmenAd: String,
        desc: String,
        isWatched: Boolean,
        date: String
    )
    {
        Film(
            id =  (filmList.lastOrNull()?.id ?: 0) + 1,
            filmAd,
            yonetmenAd,
            desc,
            isWatched,
            date,
        )
    }*/

}