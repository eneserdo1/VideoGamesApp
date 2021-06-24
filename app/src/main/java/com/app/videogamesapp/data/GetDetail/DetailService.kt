package com.app.videogamesapp.data.GetDetail


import com.app.videogamesapp.data.BaseServiceBuilder
import com.app.videogamesapp.model.GameDetailBase
import com.app.videogamesapp.utils.Constants.Companion.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailService{


    fun getDetail(id:String, onResult: (GameDetailBase?) -> Unit){
        val headerMap = HashMap<String, String>()
        headerMap["x-rapidapi-key"] = "8fa3445883msh87106b1f47b56ecp1d1b6bjsnc3bb3e4d3e28"
        headerMap["x-rapidapi-host"] = "rawg-video-games-database.p.rapidapi.com"


        val retrofit = BaseServiceBuilder.buildService(IDetail::class.java)
        retrofit.getGameDetail(id,headerMap,API_KEY).enqueue(
            object : Callback<GameDetailBase> {
                override fun onFailure(call: Call<GameDetailBase>, t: Throwable) {
                    onResult(null)
                    println("DetailService Hata-- ${t.message}")
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
}

