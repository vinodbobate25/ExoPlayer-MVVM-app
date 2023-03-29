package com.example.exoplayer_mvvm_app.ui.dashboard

import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.exoplayer_mvvm_app.R
import com.example.exoplayer_mvvm_app.data.model.Movies
import com.example.exoplayer_mvvm_app.databinding.FragmentDashboardBinding
import com.example.exoplayer_mvvm_app.util.DividerItemDecoration
import com.example.exoplayer_mvvm_app.util.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment(R.layout.fragment_dashboard),MoviesListAdapter.OnItemClickListener {

    private var _binding: FragmentDashboardBinding? = null
    private val viewModel by viewModel<DashboardViewModel>()
    var isLoading = false

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var navController: NavController



    fun addMenuProvider(toolbar: Toolbar) {
        toolbar.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }

            override fun onMenuItemSelected(item: MenuItem): Boolean {
                return when (item.itemId) {
                    R.id.action_bookmark -> {
                        navController.navigate(R.id.navigation_bookmark)
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=FragmentDashboardBinding.bind(view)
        navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.apply {
            addMenuProvider(this)
            setupWithNavController(navController, appBarConfiguration)
        }
        val moviesListAdapter=MoviesListAdapter(this)
        binding.apply {
            recylerview.apply {
                adapter=moviesListAdapter
                setHasFixedSize(true)
                addItemDecoration(DividerItemDecoration(context))
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.moviesListSate.collectLatest {
                when (it) {
                    is Resource.Success -> {
                        binding.apply {
                        }
                        isLoading = false
                        it.data?.let { moviesResposne ->
                            moviesListAdapter.submitList(moviesResposne.results.toList())
                        }
                    }
                    is Resource.Error -> {
                        isLoading = true
                        it.message?.let { message ->
                            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                        }
                    }
                    is Resource.Loading -> {
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(movie: Movies) {
        val action=DashboardFragmentDirections.actionMainToDetailfragment(movie)
        findNavController().navigate(action)
    }

    override fun onBookMarkClick(movie: Movies) {
        viewModel.saveBookMark(movie)
        Toast.makeText(context, "Added to the BookMark", Toast.LENGTH_LONG).show()
    }
}