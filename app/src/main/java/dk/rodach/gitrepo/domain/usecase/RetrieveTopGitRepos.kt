package dk.rodach.gitrepo.domain.usecase

import dk.rodach.gitrepo.data.models.gitsearch.GitSearchResult
import dk.rodach.gitrepo.data.repository.DataRepository

class RetrieveTopGitRepos(private val dataRepository: DataRepository) {

    suspend fun retrieveTopGitRepos(): List<Result<GitSearchResult>>{
        return dataRepository.loadTopList()
    }

}