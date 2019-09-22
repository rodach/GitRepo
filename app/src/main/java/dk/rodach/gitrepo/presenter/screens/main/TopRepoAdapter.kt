package dk.rodach.gitrepo.presenter.screens.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dk.rodach.gitrepo.R
import dk.rodach.gitrepo.databinding.RepoItemBinding
import dk.rodach.gitrepo.presenter.models.RepoItemModel

class TopRepoAdapter (context: Context, private val items: List<RepoItemModel>, var clickCallback: ((repoItem: RepoItemModel) -> Unit)? = null) : RecyclerView.Adapter<TopRepoAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(DataBindingUtil.inflate(inflater, R.layout.repo_item, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(private val binding: RepoItemBinding): RecyclerView.ViewHolder(binding.root){
        init {
            itemView.setOnClickListener {
                binding.repoItem?.let { safeRepoItem ->
                    clickCallback?.let { safeCallback ->
                        safeCallback(safeRepoItem)
                    }
                }
            }
        }

        fun bind(repoItemModel: RepoItemModel) = with(binding){
            repoItem = repoItemModel
            executePendingBindings()
        }
    }
}