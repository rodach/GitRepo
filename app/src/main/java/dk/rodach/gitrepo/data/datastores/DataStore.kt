package dk.rodach.gitrepo.data.datastores

import dk.rodach.gitrepo.data.models.gitsearch.GitSearchResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataStore {

    private var gitSearchResult: GitSearchResult? = null


    fun saveResult(result: GitSearchResult) {
        gitSearchResult = result
    }

    fun loadSearchResult() = gitSearchResult

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service =  retrofit.create(GitHubService::class.java)
}