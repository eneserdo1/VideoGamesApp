package com.app.videogamesapp.network

import com.app.videogamesapp.model.GameDetailBase
import com.app.videogamesapp.model.ListResponseBase
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {

    @GET("games/{id}")
    suspend fun getGameDetail(@Path("id") id:String, @HeaderMap headerMap: Map<String,String>, @Query("key") key:String ) : Response<GameDetailBase>

    @GET("games")
    suspend fun getGameList(@HeaderMap headerMap: Map<String,String>, @Query("key") key:String ) : Response<ListResponseBase>
}