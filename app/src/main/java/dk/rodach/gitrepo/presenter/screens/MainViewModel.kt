package dk.rodach.gitrepo.presenter.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dk.rodach.gitrepo.data.repository.DataRepository
import dk.rodach.gitrepo.domain.usecase.RetrievePullRequest
import dk.rodach.gitrepo.domain.usecase.RetrieveTopGitRepos
import dk.rodach.gitrepo.presenter.mappers.RepoDetailsMapper
import dk.rodach.gitrepo.presenter.models.RepoDetailsModel
import dk.rodach.gitrepo.presenter.models.RepoItemModel
import dk.rodach.gitrepo.presenter.screens.MainViewModel.Status.*
import dk.rodach.gitrepo.presenter.utils.ModelEvent
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    lateinit var error : Throwable

    val status: MutableLiveData<ModelEvent<Status>> = MutableLiveData()
    val repoItems: MutableLiveData<List<RepoItemModel>> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val repoDetails: MutableLiveData<RepoDetailsModel> = MutableLiveData()
    val currentRepoItem: MutableLiveData<RepoItemModel> = MutableLiveData()

    fun loadData(){
        status.value = ModelEvent(LOADING)
        viewModelScope.launch {
            RetrieveTopGitRepos(DataRepository()).retrieveTopGitRepos()[0].fold(
                onSuccess = { result ->
                    repoItems.value = result.items.map { RepoItemModel(it.name, it.ownerData.login, it.stargazers_count.toString()) }
                    status.value = ModelEvent(LOADING_DONE)
                },
                onFailure = {
                    error = it
                    status.value = ModelEvent(ERROR)
                }
            )
        }
    }

    fun loadPullRequest(repoItem: RepoItemModel) {
        status.value = ModelEvent(LOADING)
        currentRepoItem.value = repoItem
        viewModelScope.launch {
            RetrievePullRequest(DataRepository()).retrievePullRequest(repoItem)[0].fold(
                onSuccess = {
                    status.value = ModelEvent(LOADING_DONE)
                    repoDetails.value = RepoDetailsMapper()
                        .transformData(repoItem, it)
                },
                onFailure = {
                    error = it
                    status.value = ModelEvent(ERROR)
                }
            )
        }
    }

    enum class Status {
        ERROR,
        LOADING,
        LOADING_DONE
    }

}
