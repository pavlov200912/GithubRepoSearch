package com.example.yandexgithub.history

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.yandexgithub.R
import com.example.yandexgithub.database.GitDatabase
import com.example.yandexgithub.databinding.FragmentHistoryBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HistoryFragment : Fragment() {
    val viewModel by lazy {
        val application = requireNotNull(this.activity).application

        // Create an instance of the ViewModel Factory.
        val dataSource = GitDatabase.getInstance(application).gitDatabaseDao
        val viewModelFactory = HistoryViewModelFactory(dataSource, application)

        // Get a reference to the ViewModel associated with this fragment.

        ViewModelProviders.of(
            this, viewModelFactory
        ).get(HistoryViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentHistoryBinding>(
            inflater,
            R.layout.fragment_history, container, false
        )

        binding.viewModel = viewModel

        val adapter = HistoryRecyclerAdapter(HistoryRecyclerAdapter.OnClickListener {
            viewModel.changeFavorite(it)
        })
        binding.historyRecycler.adapter = adapter

        binding.lifecycleOwner = this

        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.show_fav_menu -> {
                viewModel.updateHistory(showAll = false)
                true
            }
            R.id.show_all_menu -> {
                viewModel.updateHistory(showAll = true)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
