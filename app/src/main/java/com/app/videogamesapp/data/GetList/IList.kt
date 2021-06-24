package com.app.videogamesapp.data.GetList


import com.app.videogamesapp.model.ListResponseBase
import retrofit2.Call
import retrofit2.http.*


interface IList {

    @GET("games")
    fun getGameList(@HeaderMap headerMap: Map<String,String>, @Query("key") key:String ) : Call<ListResponseBase>
}