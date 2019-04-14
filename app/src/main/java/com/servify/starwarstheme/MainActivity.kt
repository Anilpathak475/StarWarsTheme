package com.servify.starwarstheme

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.past3.ketro.api.Kobserver
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private val movieAdapter by lazy {
        MoviesAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViewMovies.let {
            it.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            it.adapter = movieAdapter
            it.setHasFixedSize(true)
        }

        viewModel.getMovies().observe(this, object : Kobserver<MovieResponse>() {
            override fun onSuccess(data: MovieResponse) {
                if (data.results!!.isNotEmpty()) {
                    viewModel.list.let {
                        it.clear()
                        it.addAll(data.results)
                        movieAdapter.movies = it
                        movieAdapter.notifyDataSetChanged()
                    }
                }

            }

            override fun onException(exception: Exception) {
                super.onException(exception)
                errorHandler(exception)
            }
        })


    }

    private fun errorHandler(exception: Exception) {
        when (exception) {
            is MovieErrorHandler.ErrorConfig.NetworkException -> {
                Toast.makeText(this@MainActivity, exception.message, Toast.LENGTH_LONG).show()
            }
            is MovieErrorHandler.ErrorConfig.GitHubException -> {
                Toast.makeText(this@MainActivity, exception.message, Toast.LENGTH_LONG).show()
            }
            else -> Toast.makeText(this@MainActivity, "Oops! Something went wrong.", Toast.LENGTH_LONG).show()
        }
    }

}
