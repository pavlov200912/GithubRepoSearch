package com.example.yandexgithub.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.yandexgithub.R
import com.example.yandexgithub.databinding.SearchviewItemBinding
import com.example.yandexgithub.network.GitProperty

class SearchRecyclerAdapter(private val onClickListener: OnClickListener ) : ListAdapter<GitProperty,
        SearchRecyclerAdapter.GitPropertyViewHolder>(DiffCallback) {

    class GitPropertyViewHolder(
        private var binding:
        SearchviewItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gitProperty: GitProperty) {
            binding.property = gitProperty
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<GitProperty>() {
        override fun areItemsTheSame(oldItem: GitProperty, newItem: GitProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: GitProperty, newItem: GitProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitPropertyViewHolder {

        return GitPropertyViewHolder(
            SearchviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GitPropertyViewHolder, position: Int) {
        val gitProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(gitProperty)
        }
        holder.bind(gitProperty)
    }

    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [GitProperty]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [GitProperty]
     */
    class OnClickListener(val clickListener: (gitProperty: GitProperty) -> Unit) {
        fun onClick(gitProperty: GitProperty) = clickListener(gitProperty)
    }
}
