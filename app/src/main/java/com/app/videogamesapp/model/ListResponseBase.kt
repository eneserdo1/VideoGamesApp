package com.app.videogamesapp.model


import com.google.gson.annotations.SerializedName



data class ListResponseBase (

	@SerializedName("count") val count : Int?=null,
	@SerializedName("next") val next : String?=null,
	@SerializedName("previous") val previous : String?=null,
	@SerializedName("results") val results : List<Results>?=null,
	@SerializedName("seo_title") val seo_title : String?=null,
	@SerializedName("seo_description") val seo_description : String?=null,
	@SerializedName("seo_keywords") val seo_seo_keywordswords : String?=null,
	@SerializedName("seo_h1") val seo_h1 : String?=null,
	@SerializedName("noindex") val noindex : Boolean?=null,
	@SerializedName("nofollow") val nofollow : Boolean?=null,
	@SerializedName("description") val description : String?=null,
	@SerializedName("nofollow_collections") val nofollow_collections : List<String>?=null
)