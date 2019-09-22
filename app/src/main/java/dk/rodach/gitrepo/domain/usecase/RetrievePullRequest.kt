package dk.rodach.gitrepo.domain.usecase

import dk.rodach.gitrepo.data.models.PullRequest
import dk.rodach.gitrepo.data.repository.DataRepository
import dk.rodach.gitrepo.presenter.models.RepoItemModel

class RetrievePullRequest(private val dataRepository: DataRepository) {

    suspend fun retrievePullRequest(repoItemModel: RepoItemModel): List<Result<List<PullRequest>>>{
        return dataRepository.loadPullRequest(repoItemModel.author, repoItemModel.name)
    }
}