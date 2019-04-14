package com.servify.starwarstheme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view.view.*

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {
    var movies: List<Movie> = mutableListOf()

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            itemView.textViewMovieName.text = movie.title
            itemView.textViewProdecure.text = movie.director
            itemView.textViewReleaseDate.text = movie.releaseDate
        }
    }
}