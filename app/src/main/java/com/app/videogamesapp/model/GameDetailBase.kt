package com.app.videogamesapp.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "games")
data class GameDetailBase(

    @PrimaryKey
    @SerializedName("id") val gameId: Int,
    @ColumnInfo(name="name")
    @SerializedName("name") val name: String? = null,
    @ColumnInfo(name="name_original")
    @SerializedName("name_original") val name_original: String? = null,
    @ColumnInfo(name="description")
    @SerializedName("description") val description: String? = null,
    @ColumnInfo(name="metacritic")
    @SerializedName("metacritic") val metacritic: Int? = null,
    @ColumnInfo(name="released")
    @SerializedName("released") val released: String? = null,
    @ColumnInfo(name="background_image")
    @SerializedName("background_image") val background_image: String? = null,
    @ColumnInfo(name="rating")
    @SerializedName("rating") val rating: Double? = null,
    @ColumnInfo(name="description_raw")
    @SerializedName("description_raw") val description_raw: String? = null
)