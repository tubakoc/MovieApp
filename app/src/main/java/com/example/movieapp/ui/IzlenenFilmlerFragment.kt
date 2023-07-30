package com.example.movieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.adapter.FilmAdapter
import com.example.movieapp.common.viewBinding
import com.example.movieapp.data.model.Film
import com.example.movieapp.data.source.Database
import com.example.movieapp.databinding.FragmentIzlenenFilmlerBinding


class IzlenenFilmlerFragment : Fragment(R.layout.fragment_izlenen_filmler) {

    private val izlenenFilmAdapter = FilmAdapter(::itemClick,::checkDelete)

    private val binding by viewBinding (FragmentIzlenenFilmlerBinding::bind)



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding)
        {
            setData(Database.getIzlenenFilm())
        }
    }
    private fun setData(list: List<Film>)
    {
        binding.rcvIzlenen.adapter = izlenenFilmAdapter
        izlenenFilmAdapter.upDateList(list)
    }

    private fun itemClick(film:Film)
    {
        val action = IzlenenFilmlerFragmentDirections.izlenenToDetay(film)
        findNavController().navigate(action)
    }

    fun checkDelete(film: Film, isChecked: Boolean)

    {
        if (isChecked)
        {
            Database.izlenecekEkle(film)
        }
        else
        {
            Database.izlenenEkle(film)
        }
        setData(Database.getIzlenenFilm())

    }


}