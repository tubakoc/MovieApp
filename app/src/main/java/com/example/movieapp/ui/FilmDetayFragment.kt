package com.example.movieapp.ui


import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.movieapp.R
import com.example.movieapp.common.viewBinding
import com.example.movieapp.data.model.Film
import com.example.movieapp.data.source.Database
import com.example.movieapp.databinding.FragmentFilmDetayBinding

class FilmDetayFragment : Fragment(R.layout.fragment_film_detay) {

    private val binding by viewBinding (FragmentFilmDetayBinding::bind)

    private val args:FilmDetayFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding)
        {
            tvFilmAd.text = args.film.filmAd
            tvDate.text = args.film.date
            tvYonetmen.text=args.film.yonetmenAd
            tvDsc.text = args.film.filmAciklama

            btnDelete.setOnClickListener {
                AlertDialog.Builder(requireContext())
                    .setTitle("Delete Film")
                    .setMessage("Are you sure")
                    .setPositiveButton("Yes"){_,_->
                        Database.filmSil(args.film)
                    }.setNegativeButton("No",null)
                    .show()


            }
        }
    }
}