package com.app.videogamesapp.data.remote

import androidx.room.PrimaryKey
import com.app.videogamesapp.model.GameDetailBase
import com.app.videogamesapp.model.ListResponseBase
import com.app.videogamesapp.network.APIInterface
import com.app.videogamesapp.network.BaseServiceBuilder
import com.app.weatherapplicationmvvm.utils.Resource
import retrofit2.Response


class RemoteDataSource {


    suspend fun getGameVideoDetail(id:String,headerMap:Map<String,String>,key: String): Resource<GameDetailBase>{
        val api = BaseServiceBuilder.buildService(APIInterface::class.java)
        return getResponse(request = { api.getGameDetail(id, headerMap, key) },"Error get game detail")
    }

    suspend fun getGameVideoList(headerMap:Map<String,String>,key: String): Resource<ListResponseBase>{
        val api = BaseServiceBuilder.buildService(APIInterface::class.java)
        return getResponse(request = { api.getGameList(headerMap,key) },"Error get game detail")
    }

    private suspend fun  <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): Resource<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return Resource.success(result.body())
            } else {
                Resource.error(defaultErrorMessage, null)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            return Resource.error("Error ${e.message}", null)
        }
    }

}