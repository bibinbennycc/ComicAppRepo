package com.codetest.comicapp.feature.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.codetest.comicapp.R
import com.codetest.comicapp.common.Resource
import com.codetest.comicapp.feature.model.ComicModel
import com.codetest.comicapp.feature.viewmodel.ComicViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class ComicActivity : AppCompatActivity() {

    private val viewModel: ComicViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setObservers()
        getCurrentComic()
    }

    //Method get the current comic information from the view model
    private fun getCurrentComic() {
        progress_bar.visibility = View.VISIBLE
        viewModel.getCurrentComicInfo()
    }

    //Method set the listener for the current comic item and handling the response
    private fun setObservers() {
        viewModel.currentComic.observe(this, { response ->
            progress_bar.visibility = View.GONE
            when (response) {
                is Resource.Success -> setComicInfo(response.data)
                is Resource.Error -> handleComicInfoError(response.message)
            }
        })
    }

    //Method set the comic information in the ui
    private fun setComicInfo(comicModel: ComicModel?) {
        if (!comicModel?.imageUrl.isNullOrEmpty()) {
            Glide.with(baseContext).load(comicModel?.imageUrl)
                .into(comic_image)
        } else {
            comic_image.setImageResource(R.drawable.ic_launcher_foreground)
        }
        comic_title.text = if (!comicModel?.title.isNullOrEmpty()) comicModel?.title else "Comic"
        if (!comicModel?.subtitle.isNullOrEmpty()) {
            comic_description.isVisible = true
            comic_description.text = comicModel?.subtitle
        } else {
            comic_description.isVisible = false
        }
    }

    //Method handling the error scenario for the current comic model
    private fun handleComicInfoError(message: String?) {
        setComicInfo(null)
        Toast.makeText(
            this,
            message ?: getString(R.string.something_went_wrong),
            Toast.LENGTH_SHORT
        ).show()
    }
}