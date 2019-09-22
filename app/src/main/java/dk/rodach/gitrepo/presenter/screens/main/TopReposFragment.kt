package dk.rodach.gitrepo.presenter.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dk.rodach.gitrepo.R
import dk.rodach.gitrepo.databinding.TopReposFragmentBinding
import dk.rodach.gitrepo.presenter.extensions.inTransaction
import dk.rodach.gitrepo.presenter.extensions.observe
import dk.rodach.gitrepo.presenter.extensions.viewModelActivity
import dk.rodach.gitrepo.presenter.models.RepoItemModel
import dk.rodach.gitrepo.presenter.screens.MainViewModel
import dk.rodach.gitrepo.presenter.screens.MainViewModel.Status.*
import dk.rodach.gitrepo.presenter.screens.pullrequest.RepoDetailsFragment
import dk.rodach.gitrepo.presenter.utils.ModelEvent

class TopReposFragment : Fragment() {

    private lateinit var binding: TopReposFragmentBinding

    companion object {
        fun newInstance() = TopReposFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.top_repos_fragment, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner

            //uses the activity's scope for the view model, so we easy can share data between the fragments
            mainViewModel = viewModelActivity(MainViewModel::class.java){
                observe(status, ::statusListener)
                observe(repoItems, ::setupRecyclerView)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainViewModel?.loadData()
    }

    private fun statusListener(status: ModelEvent<MainViewModel.Status>?) {
        status?.getNotHandledContent()?.let {
            when(it){
                ERROR -> Toast.makeText(context, binding.mainViewModel?.error?.localizedMessage, Toast.LENGTH_LONG).show()
                LOADING -> binding.mainViewModel?.loading?.value = true
                LOADING_DONE -> binding.mainViewModel?.loading?.value = false
            }
        }
    }

    private fun setupRecyclerView(list: List<RepoItemModel>?) {
        list?.let {repoItems ->
            context?.let {context ->
                val adapter = TopRepoAdapter(context, repoItems)
                adapter.clickCallback = fun(repoItem: RepoItemModel){
                    binding.mainViewModel?.loadPullRequest(repoItem)

                    fragmentManager?.inTransaction {
                        addToBackStack(TopReposFragment::class.java.name)
                        replace(R.id.container, RepoDetailsFragment.newInstance())
                    }
                }

                binding.repoRecyclerView.layoutManager = LinearLayoutManager(context)
                binding.repoRecyclerView.adapter = adapter
            }
        }
    }

}
