package dk.rodach.gitrepo.presenter.screens.pullrequest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dk.rodach.gitrepo.R
import dk.rodach.gitrepo.databinding.RepoDetailsFragmentBinding
import dk.rodach.gitrepo.presenter.extensions.observe
import dk.rodach.gitrepo.presenter.extensions.viewModelActivity
import dk.rodach.gitrepo.presenter.models.RepoDetailsModel
import dk.rodach.gitrepo.presenter.screens.MainViewModel
import dk.rodach.gitrepo.presenter.utils.ModelEvent

class RepoDetailsFragment : Fragment() {

    private lateinit var binding: RepoDetailsFragmentBinding

    companion object {
        fun newInstance() = RepoDetailsFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.repo_details_fragment, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner

            //uses the activity's scope for the view model, so we easy can share data between the fragments
            mainViewModel = viewModelActivity(MainViewModel::class.java) {
                observe(status, ::statusListener)
                observe(repoDetails, ::setupRecyclerView)
            }
        }

        return binding.root
    }

    private fun statusListener(status: ModelEvent<MainViewModel.Status>?) {
        status?.getNotHandledContent()?.let {
            when (it) {
                MainViewModel.Status.ERROR -> Toast.makeText(
                    context,
                    binding.mainViewModel?.error?.localizedMessage,
                    Toast.LENGTH_LONG
                ).show()
                MainViewModel.Status.LOADING -> binding.mainViewModel?.loading?.value = true
                MainViewModel.Status.LOADING_DONE -> binding.mainViewModel?.loading?.value = false
            }
        }
    }

    private fun setupRecyclerView(repoDetailsModel: RepoDetailsModel?) {

        repoDetailsModel?.let { repoDetails ->
            context?.let { context ->
                val adapter = RepoDetailsAdapter(context, repoDetails.pullRequestList)

                binding.pullRequestRecyclerView.layoutManager = LinearLayoutManager(context)
                binding.pullRequestRecyclerView.adapter = adapter
            }
        }
    }
}