package dk.rodach.gitrepo.presenter.screens.pullrequest

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dk.rodach.gitrepo.R
import dk.rodach.gitrepo.databinding.PullRequestItemBinding
import dk.rodach.gitrepo.presenter.models.PullRequestModel

class RepoDetailsAdapter (context: Context, private val items: List<PullRequestModel>) : RecyclerView.Adapter<RepoDetailsAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(DataBindingUtil.inflate(inflater, R.layout.pull_request_item, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(private val binding: PullRequestItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(pullRequestModel: PullRequestModel) = with(binding){
            pullRequest = pullRequestModel
            executePendingBindings()
        }
    }
}