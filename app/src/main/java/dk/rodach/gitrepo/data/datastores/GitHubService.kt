package dk.rodach.gitrepo.data.datastores

import dk.rodach.gitrepo.data.models.PullRequest
import dk.rodach.gitrepo.data.models.gitsearch.GitSearchResult
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("search/repositories?q=stars%3A>0&s=stars&type=Repositories")
    suspend fun listRepos(): GitSearchResult


    @GET("/repos/{ownerData}/{repo}/pulls")
    suspend fun pullRequest(@Path("ownerData")owner: String, @Path("repo")repo: String): List<PullRequest>
}