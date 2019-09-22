package dk.rodach.gitrepo.presenter.mappers

import dk.rodach.gitrepo.data.models.PullRequest
import dk.rodach.gitrepo.presenter.models.PullRequestModel
import dk.rodach.gitrepo.presenter.models.RepoDetailsModel
import dk.rodach.gitrepo.presenter.models.RepoItemModel
import java.text.SimpleDateFormat

class RepoDetailsMapper {
    fun transformData(repoItemModel: RepoItemModel, pullRequestList: List<PullRequest>): RepoDetailsModel {

        val pullRequests = pullRequestList.map {
            val info = createInfo(it)

            PullRequestModel(it.title, info)
        }

        return RepoDetailsModel(repoItemModel, pullRequests)
    }

    private fun createInfo(pullRequest: PullRequest): String {
        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(pullRequest.created_at)

        val dateString = SimpleDateFormat("dd MMM yyyy").format(date)

        val number = pullRequest.number.toString()
        val username = pullRequest.user.login

        return "#$number opened $dateString by $username"

    }
}