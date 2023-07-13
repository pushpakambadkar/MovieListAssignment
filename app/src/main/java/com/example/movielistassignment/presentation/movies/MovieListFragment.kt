package com.example.movielistassignment.presentation.movies

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.core.di.DaggerCoreComponent
import com.example.movielistassignment.MainActivity
import com.example.movielistassignment.MovieApplication
import com.example.movielistassignment.R
import com.example.movielistassignment.databinding.FragmentMovieListBinding
import com.example.movielistassignment.extension.gone
import com.example.movielistassignment.extension.visible
import com.example.movielistassignment.presentation.common.MovieDataState
import com.example.movielistassignment.presentation.di.DaggerAppComponent
import com.example.movielistassignment.presentation.utility.GridSpacingItemDecoration
import javax.inject.Inject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/**
 * A simple [Fragment] subclass.
 * Use the [MovieListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var gridSpacingItemDecoration: GridSpacingItemDecoration
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: FragmentMovieListBinding
    private val movieList = arrayListOf<MovieData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as MovieApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        initView()
        movieViewModel = ViewModelProvider(this, viewModelFactory)[MovieViewModel::class.java]
        movieViewModel.getConfigurations()
        observeData()
    }

    private fun setToolbar() {
        val toolbar = (requireActivity() as MainActivity).toolbar
        toolbar.title = getString(R.string.str_movie_screen)
        toolbar.setNavigationIcon(R.drawable.arrow_back)
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initView() {
        binding.movieList.apply {
            adapter = MovieAdapter(movieList)
            layoutManager = GridLayoutManager(requireActivity(), 2)
            addItemDecoration(gridSpacingItemDecoration)
        }
    }

    private fun observeData() {
        movieViewModel.movieData.observe(this.viewLifecycleOwner) {
            when (it) {
                is MovieDataState.LoadingState -> {
                    binding.progress.visible()
                    binding.movieList.gone()
                    binding.noMovies.gone()
                }
                is MovieDataState.SuccessState -> {
                    binding.progress.gone()
                    movieList.addAll(it.data)
                    binding.movieList.adapter?.notifyItemRangeChanged(0, it.data.size)
                    checkMovieListEmpty()
                }
                is MovieDataState.ErrorState -> {
                    binding.progress.gone()
                    checkMovieListEmpty()
                }
            }
        }
        binding.movieList.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (movieList.isNotEmpty() && (recyclerView.layoutManager as? LinearLayoutManager)?.findLastCompletelyVisibleItemPosition() == movieList.size - 1) {
                    movieViewModel.loadMovies()
                }
            }
        })
    }

    private fun checkMovieListEmpty() {
        if (movieList.isEmpty()) {
            binding.movieList.gone()
            binding.noMovies.visible()
        } else {
            binding.movieList.visible()
            binding.noMovies.gone()
        }
    }
}