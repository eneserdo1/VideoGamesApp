package com.app.videogamesapp.data.GetDetail


import com.app.videogamesapp.model.GameDetailBase
import com.app.videogamesapp.model.ListResponseBase
import retrofit2.Call
import retrofit2.http.*


interface IDetail {

    @GET("games/{id}")
    fun getGameDetail(@Path("id") id:String, @HeaderMap headerMap: Map<String,String>, @Query("key") key:String ) : Call<GameDetailBase>
}