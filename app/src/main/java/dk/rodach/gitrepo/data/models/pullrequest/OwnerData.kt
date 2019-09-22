package dk.rodach.gitrepo.data.models

import com.google.gson.annotations.SerializedName


data class OwnerData (

	@SerializedName("login") val login : String,
	@SerializedName("id") val id : Int,
	@SerializedName("node_id") val node_id : String,
	@SerializedName("avatar_url") val avatar_url : String,
	@SerializedName("gravatar_id") val gravatar_id : String,
	@SerializedName("url") val url : String,
	@SerializedName("type") val type : String,
	@SerializedName("site_admin") val site_admin : Boolean
)