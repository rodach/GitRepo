package dk.rodach.gitrepo.data.models.gitsearch

import com.google.gson.annotations.SerializedName
import dk.rodach.gitrepo.data.models.OwnerData


data class Items (

    @SerializedName("id") val id : Int,
    @SerializedName("node_id") val node_id : String,
    @SerializedName("name") val name : String,
    @SerializedName("full_name") val full_name : String,
    @SerializedName("owner") val ownerData : OwnerData,
    @SerializedName("private") val private : Boolean,
    @SerializedName("description") val description : String,
    @SerializedName("fork") val fork : Boolean,
    @SerializedName("url") val url : String,
    @SerializedName("created_at") val created_at : String,
    @SerializedName("updated_at") val updated_at : String,
    @SerializedName("pushed_at") val pushed_at : String,
    @SerializedName("homepage") val homepage : String,
    @SerializedName("size") val size : Int,
    @SerializedName("stargazers_count") val stargazers_count : Int,
    @SerializedName("watchers_count") val watchers_count : Int,
    @SerializedName("language") val language : String,
    @SerializedName("forks_count") val forks_count : Int,
    @SerializedName("open_issues_count") val open_issues_count : Int,
    @SerializedName("master_branch") val master_branch : String,
    @SerializedName("default_branch") val default_branch : String,
    @SerializedName("score") val score : Double
)