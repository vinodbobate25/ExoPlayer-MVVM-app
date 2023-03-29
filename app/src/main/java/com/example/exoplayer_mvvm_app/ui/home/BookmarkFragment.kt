package com.example.exoplayer_mvvm_app.ui.home

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.exoplayer_mvvm_app.R
import com.example.exoplayer_mvvm_app.data.model.Movies
import com.example.exoplayer_mvvm_app.databinding.FragmentHomeBinding
import com.example.exoplayer_mvvm_app.util.DividerItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarkFragment : Fragment(R.layout.fragment_home), BookmarkAdapter.OnItemClickListener {

    private val bookmarkViewModel by viewModel<BookmarkViewModel>()
    lateinit var navController: NavController


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.apply {
            setupWithNavController(navController, appBarConfiguration)
        }
        val bookmarkAdapter = BookmarkAdapter(this)
        binding.apply {
            recylerview.apply {
                adapter = bookmarkAdapter
                setHasFixedSize(true)
                addItemDecoration(DividerItemDecoration(context))
            }
        }
        bookmarkViewModel.fetchMovies().observe(viewLifecycleOwner)
        {
            bookmarkAdapter.submitList(it.toList())
        }

    }

    override fun onItemClick(movies: Movies) {
        val action=BookmarkFragmentDirections.actionBookmarkToDetailfragment(movies)
        findNavController().navigate(action)
    }

    override fun onBookMarkClick(Movies: Movies) {
    }
}