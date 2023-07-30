package com.example.movieapp.ui


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.adapter.FilmAdapter
import com.example.movieapp.common.viewBinding
import com.example.movieapp.data.model.Film
import com.example.movieapp.data.source.Database
import com.example.movieapp.databinding.FragmentIzlenecekFilmlerBinding


class IzlenecekFilmlerFragment : Fragment(R.layout.fragment_izlenecek_filmler) {

    private val izlenecekFilmAdapter = FilmAdapter(::itemClick,::checkDelete)

    private val binding by viewBinding (FragmentIzlenecekFilmlerBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding)
        {
            setData(Database.getIzlenecekFilm())
        }
    }

    private fun setData(list: List<Film>)
    {
        binding.rvIzlenecekFilm.adapter = izlenecekFilmAdapter
        izlenecekFilmAdapter.upDateList(list)
    }
    private fun itemClick(film:Film)
    {
        val action = IzlenecekFilmlerFragmentDirections.izlenecekToDetay(film)
        findNavController().navigate(action)
    }
    fun checkDelete(film: Film, isChecked: Boolean)

    {
        if (isChecked)
        {
            Database.izlenenEkle(film)
        }
        else
        {
            Database.izlenecekEkle(film)
        }
        setData(Database.getIzlenecekFilm())

    }
}