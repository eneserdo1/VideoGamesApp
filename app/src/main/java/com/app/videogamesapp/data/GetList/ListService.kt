package com.app.videogamesapp.data.GetList


import com.app.videogamesapp.data.BaseServiceBuilder
import com.app.videogamesapp.model.ListResponseBase
import com.app.videogamesapp.utils.Constants.Companion.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListService{


    fun getList(onResult: (ListResponseBase?) -> Unit){
        val headerMap = HashMap<String, String>()
        headerMap["x-rapidapi-key"] = "8fa3445883msh87106b1f47b56ecp1d1b6bjsnc3bb3e4d3e28"
        headerMap["x-rapidapi-host"] = "rawg-video-games-database.p.rapidapi.com"


        val retrofit = BaseServiceBuilder.buildService(IList::class.java)
        retrofit.getGameList(headerMap,API_KEY).enqueue(
            object : Callback<ListResponseBase> {
                override fun onFailure(call: Call<ListResponseBase>, t: Throwable) {
                    onResult(null)
                    println("Hata-- ${t.message}")
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

