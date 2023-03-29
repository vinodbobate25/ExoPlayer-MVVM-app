package com.example.exoplayer_mvvm_app.ui.notifications


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import androidx.navigation.NavArgs
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.exoplayer_mvvm_app.R
import com.example.exoplayer_mvvm_app.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment() : Fragment(R.layout.fragment_detail) {

    private val detailViewModel by viewModel<DetailViewModel>()
    lateinit var navController: NavController
    private val args by navArgs<DetailFragmentArgs>()
    private var player: ExoPlayer? = null

    private var playWhenReady = true
    private var currentItem = 0
    private var playbackPosition = 0L
    lateinit var binding:FragmentDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=FragmentDetailBinding.bind(view)
        navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.apply {
            setupWithNavController(navController, appBarConfiguration)
        }
        val args=args.movie
        binding.apply {
            tvMovie.text=args.title
            Glide.with(imgMoviePoster)
                .load("https://image.tmdb.org/t/p/w250_and_h141_face"+args.backdrop_path)
                .into(imgMoviePoster)
            tvDesc.text=args.overview
        }
        player = ExoPlayer.Builder(requireContext())
            .build()
            .also { exoPlayer ->
                binding.videoView.player = exoPlayer
                val mediaItem = MediaItem.fromUri(getString(R.string.media_url_mp4))
                exoPlayer.setMediaItem(mediaItem)
            }
        player!!.play()
    }


}