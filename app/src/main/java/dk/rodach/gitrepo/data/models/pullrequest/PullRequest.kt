package dk.rodach.gitrepo.data.models

import com.google.gson.annotations.SerializedName

data class PullRequest (

    @SerializedName("url") val url : String,
    @SerializedName("id") val id : Int,
    @SerializedName("node_id") val node_id : String,
    @SerializedName("review_comments_url") val review_comments_url : String,
    @SerializedName("review_comment_url") val review_comment_url : String,
    @SerializedName("number") val number : Int,
    @SerializedName("state") val state : String,
    @SerializedName("locked") val locked : Boolean,
    @SerializedName("title") val title : String,
    @SerializedName("user") val user : User,
    @SerializedName("body") val body : String,
    @SerializedName("active_lock_reason") val active_lock_reason : String,
    @SerializedName("created_at") val created_at : String,
    @SerializedName("updated_at") val updated_at : String,
    @SerializedName("closed_at") val closed_at : String,
    @SerializedName("merged_at") val merged_at : String,
    @SerializedName("merge_commit_sha") val merge_commit_sha : String,
    @SerializedName("assignee") val assignee : Assignee,
    @SerializedName("author_association") val author_association : String,
    @SerializedName("draft") val draft : Boolean
)