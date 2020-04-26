package com.example.yandexgithub.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.yandexgithub.database.GitRepo
import com.example.yandexgithub.databinding.HistoryviewItemBinding
import com.example.yandexgithub.network.GitProperty
import com.example.yandexgithub.search.SearchRecyclerAdapter

class HistoryRecyclerAdapter(private val onClickListener: HistoryRecyclerAdapter.OnClickListener) : ListAdapter<GitRepo,
        HistoryRecyclerAdapter.GitRepoViewHolder>(HistoryRecyclerAdapter) {
    class GitRepoViewHolder(
        private var binding:
        HistoryviewItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gitRepo: GitRepo) {
            binding.repo = gitRepo
            binding.executePendingBindings()
        }
    }
    companion object DiffCallback : DiffUtil.ItemCallback<GitRepo>() {
        override fun areItemsTheSame(oldItem: GitRepo, newItem: GitRepo): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: GitRepo, newItem: GitRepo): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepoViewHolder {

        return GitRepoViewHolder(
            HistoryviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HistoryRecyclerAdapter.GitRepoViewHolder, position: Int) {
        val gitRepo = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(gitRepo)
        }
        holder.bind(gitRepo)
    }

    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [GitProperty]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [GitProperty]
     */
    class OnClickListener(val clickListener: (gitRepo: GitRepo) -> Unit) {
        fun onClick(gitRepo: GitRepo) = clickListener(gitRepo)
    }
}