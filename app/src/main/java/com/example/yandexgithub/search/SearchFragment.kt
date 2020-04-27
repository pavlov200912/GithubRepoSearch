package com.example.yandexgithub.search

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.yandexgithub.database.GitDatabase
import com.example.yandexgithub.databinding.FragmentSearchBinding
import com.example.yandexgithub.network.GitProperty


class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentSearchBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application

        // Create an instance of the ViewModel Factory.
        val dataSource = GitDatabase.getInstance(application).gitDatabaseDao
        val viewModelFactory = SearchViewModelFactory(dataSource, application)

        // Get a reference to the ViewModel associated with this fragment.
        val viewModel = ViewModelProviders.of(
            this, viewModelFactory
        ).get(SearchViewModel::class.java)

        binding.viewModel = viewModel


        viewModel.status.observe(viewLifecycleOwner, Observer {
            // Toast if error with searching query
            if (it == GitApiStatus.INTERNET_ERROR) {
                Toast.makeText(activity, "No internet connection", Toast.LENGTH_SHORT).show()
            }
            if (it == GitApiStatus.QUERY_ERROR) {
                Toast.makeText(activity, "Search query is empty", Toast.LENGTH_SHORT).show()
            }
        })



        binding.lifecycleOwner = this


        val packageManager = requireNotNull(this.activity).packageManager



        binding.button.setOnClickListener {
            // Hide the keyboard.
            val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE)
                    as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)

            // Go to the internet
            viewModel.getGitProperties()
        }

        binding.searchRecycler.adapter =
            SearchRecyclerAdapter(SearchRecyclerAdapter.OnClickListener {
                viewModel.saveClickedRepo(it)
                openBrowser(it, packageManager)
            })
        return binding.root
    }


    private fun openBrowser(gitProperty: GitProperty, packageManager: PackageManager) {
        val webpage: Uri = Uri.parse(gitProperty.htmlUrl)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(activity, "Can't reach browser", Toast.LENGTH_SHORT).show()
        }
    }
}
