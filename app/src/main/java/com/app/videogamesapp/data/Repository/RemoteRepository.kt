package com.app.videogamesapp.data.Repository

import com.app.videogamesapp.data.remote.RemoteDataSource
import com.app.videogamesapp.network.BaseServiceBuilder
import com.app.videogamesapp.network.APIInterface
import com.app.videogamesapp.model.GameDetailBase
import com.app.videogamesapp.model.ListResponseBase
import com.app.videogamesapp.utils.Constants
import com.app.videogamesapp.utils.Constants.Companion.API_KEY
import com.app.videogamesapp.utils.Constants.Companion.Rapidapi_Host
import com.app.videogamesapp.utils.Constants.Companion.Rapidapi_Key
import com.app.weatherapplicationmvvm.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class RemoteRepository(val dataSource:RemoteDataSource) {

    suspend fun getDetail(id:String):Flow<Resource<GameDetailBase>>{
        val headerMap = HashMap<String, String>()
        headerMap["x-rapidapi-key"] = Rapidapi_Key
        headerMap["x-rapidapi-host"] = Rapidapi_Host

        return flow {
            try {
                emit(Resource.loading())
                emit(dataSource.getGameVideoDetail(id,headerMap, API_KEY))
            }catch (e:Exception){

                e.printStackTrace()
            }
        }
    }

    suspend fun getList():Flow<Resource<ListResponseBase>>{
        val headerMap = HashMap<String, String>()
        headerMap["x-rapidapi-key"] = Rapidapi_Key
        headerMap["x-rapidapi-host"] = Rapidapi_Host

        return flow {
            try {
                emit(Resource.loading())
                emit(dataSource.getGameVideoList(headerMap, API_KEY))
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }


}