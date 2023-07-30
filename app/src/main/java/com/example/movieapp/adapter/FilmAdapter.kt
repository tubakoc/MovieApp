package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.model.Film
import com.example.movieapp.databinding.ItemFilmBinding

class FilmAdapter (
    private val itemClick: (Film) -> Unit ,
    private val deleteMovieClick:(Film,Boolean) ->Unit
        ):RecyclerView.Adapter<FilmAdapter.FilmViewHolder>()
{
    private val filmList = mutableListOf<Film>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FilmViewHolder(
            ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )


    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) = holder.bind(filmList[position])

    inner class FilmViewHolder(private val binding: ItemFilmBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(film: Film) {
            with(binding) {

                filmIsim.text = film.filmAd
                yonetmenIsmi.text = film.yonetmenAd
                cikisTarih.text = film.date
                root.setOnClickListener {
                    itemClick(film)
                }

                checkBox.setOnClickListener{
                    deleteMovieClick(film,true)
                }
            }
        }
    }

    fun upDateList(list: List<Film>)
    {
        filmList.clear()
        filmList.addAll(list)
        notifyItemRangeChanged(0,list.size)
    }

    override fun getItemCount() = filmList.size

}