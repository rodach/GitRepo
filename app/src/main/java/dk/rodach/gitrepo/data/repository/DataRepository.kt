package dk.rodach.gitrepo.data.repository

import dk.rodach.gitrepo.data.datastores.DataStore
import dk.rodach.gitrepo.data.models.PullRequest
import dk.rodach.gitrepo.data.models.gitsearch.GitSearchResult

class DataRepository {

    suspend fun loadTopList(): List<Result<GitSearchResult>> {
        var result = DataStore.loadSearchResult()

        return if (result != null){
            listOf(Result.success(result))
        } else {
            return try {
                result = DataStore.service.listRepos()

                DataStore.saveResult(result)

                listOf(Result.success(result))
            } catch (e: Exception){
                listOf(Result.failure(e))
            }
        }
    }

    suspend fun loadPullRequest(owner: String, repo: String): List<Result<List<PullRequest>>>{
        return try {
            val result = DataStore.service.pullRequest(owner, repo)

            listOf(Result.success(result))
        } catch (e: Exception){
            listOf(Result.failure(e))
        }
    }
}