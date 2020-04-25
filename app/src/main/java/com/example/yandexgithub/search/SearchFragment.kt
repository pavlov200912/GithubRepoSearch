package com.example.yandexgithub.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.yandexgithub.R
import com.example.yandexgithub.databinding.FragmentSearchBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SearchFragment : Fragment() {
    private val viewModel: SearchViewModel by lazy {
        ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentSearchBinding.inflate(inflater)

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if(it == GitApiStatus.INTERNET_ERROR) {
                Toast.makeText(activity, "No internet connection", Toast.LENGTH_SHORT).show()
            }
            if (it == GitApiStatus.QUERY_ERROR) {
                Toast.makeText(activity, "Search query is empty", Toast.LENGTH_SHORT).show()
            }
        })

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.button.setOnClickListener {
            // Hide the keyboard.
            val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE)
                    as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)

            // Go to the internet
            viewModel.getGitProperties()
        }

        binding.searchRecycler.adapter = SearchRecyclerAdapter(SearchRecyclerAdapter.OnClickListener {
            Toast.makeText(activity, "Clicked: ${it.getReadableHtml()}", Toast.LENGTH_SHORT).show()
        })
        return binding.root
    }

}
