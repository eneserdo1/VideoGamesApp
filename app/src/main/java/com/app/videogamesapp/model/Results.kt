package com.app.videogamesapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class Results (

	@SerializedName("id") val id : Int?=null,
	@SerializedName("name") val name : String?=null,
	@SerializedName("released") val released : String?=null,
	@SerializedName("background_image") val background_image : String?=null,
	@SerializedName("rating") val rating : Double?=null,
	@SerializedName("rating_top") val rating_top : Int?=null,
	@SerializedName("ratings_count") val ratings_count : Int?=null,
	@SerializedName("metacritic") val metacritic : Int?=null,

)