package dk.rodach.gitrepo.presenter.models

data class RepoDetailsModel(val repoItem: RepoItemModel,
                            val pullRequestList: List<PullRequestModel>)