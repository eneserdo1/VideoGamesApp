package com.app.videogamesapp.data.Repository

import com.app.videogamesapp.data.BaseServiceBuilder
import com.app.videogamesapp.data.Interfaces.APIInterface
import com.app.videogamesapp.model.GameDetailBase
import com.app.videogamesapp.model.ListResponseBase
import com.app.videogamesapp.utils.Constants
import com.app.videogamesapp.utils.Constants.Companion.Rapidapi_Host
import com.app.videogamesapp.utils.Constants.Companion.Rapidapi_Key
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    val retrofit = BaseServiceBuilder.buildService(APIInterface::class.java)

    fun getDetail(id:String, onResult: (GameDetailBase?) -> Unit){
        val headerMap = HashMap<String, String>()
        headerMap["x-rapidapi-key"] = Rapidapi_Key
        headerMap["x-rapidapi-host"] = Rapidapi_Host

        retrofit.getGameDetail(id,headerMap, Constants.API_KEY).enqueue(
            object : Callback<GameDetailBase> {
                override fun onFailure(call: Call<GameDetailBase>, t: Throwable) {
                    onResult(null)
                    println("GetDetail Error - ${t.message}")
                }
                override fun onResponse(
                    call: Call<GameDetailBase>,
                    response: Response<GameDetailBase>
                ) {
                    val detailResponse = response.body()
                    onResult(detailResponse)
                }

            }
        )
    }

    fun getList(onResult: (ListResponseBase?) -> Unit){
        val headerMap = HashMap<String, String>()
        headerMap["x-rapidapi-key"] = Rapidapi_Key
        headerMap["x-rapidapi-host"] = Rapidapi_Host

        retrofit.getGameList(headerMap, Constants.API_KEY).enqueue(
            object : Callback<ListResponseBase> {
                override fun onFailure(call: Call<ListResponseBase>, t: Throwable) {
                    onResult(null)
                    println("GetList Error - ${t.message}")
                }

                override fun onResponse(
                    call: Call<ListResponseBase>,
                    response: Response<ListResponseBase>
                ) {
                    val listResponse = response.body()
                    onResult(listResponse)
                }

            }
        )
    }
}