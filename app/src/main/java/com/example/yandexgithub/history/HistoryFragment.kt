package com.example.yandexgithub.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.yandexgithub.R
import com.example.yandexgithub.database.GitDatabase
import com.example.yandexgithub.databinding.FragmentHistoryBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HistoryFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentHistoryBinding>(inflater,
            R.layout.fragment_history, container, false)

        val application = requireNotNull(this.activity).application

        // Create an instance of the ViewModel Factory.
        val dataSource = GitDatabase.getInstance(application).gitDatabaseDao
        val viewModelFactory = HistoryViewModelFactory(dataSource, application)

        // Get a reference to the ViewModel associated with this fragment.
        val historyViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(HistoryViewModel::class.java)
        binding.viewModel = historyViewModel

        val adapter = HistoryRecyclerAdapter(HistoryRecyclerAdapter.OnClickListener {
            Toast.makeText(activity, "Cliclked: ${it.name}", Toast.LENGTH_SHORT).show()
        })
        binding.historyRecycler.adapter = adapter

        binding.setLifecycleOwner(this)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
