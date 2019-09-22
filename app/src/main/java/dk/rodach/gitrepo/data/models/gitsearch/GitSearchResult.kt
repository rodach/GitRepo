package dk.rodach.gitrepo.data.models.gitsearch

import com.google.gson.annotations.SerializedName


data class GitSearchResult (

	@SerializedName("total_count") val total_count : Int,
	@SerializedName("incomplete_results") val incomplete_results : Boolean,
	@SerializedName("items") val items : List<Items>
)