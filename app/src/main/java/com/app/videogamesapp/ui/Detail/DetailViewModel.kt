package com.app.videogamesapp.ui.Detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.videogamesapp.data.Repository.RemoteRepository
import com.app.videogamesapp.data.remote.RemoteDataSource
import com.app.videogamesapp.model.GameDetailBase
import com.app.weatherapplicationmvvm.utils.Status
import com.application.wgo.Sqlite.GameDao
import com.application.wgo.Sqlite.GameRoomDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    var mutableDetailData: MutableLiveData<GameDetailBase> = MutableLiveData()
    var progressbarState: MutableLiveData<Boolean> = MutableLiveData()
    var gameDao: GameDao? = null


    init {
        val gameDb = GameRoomDatabase.getDatabase(application)
        gameDao = gameDb!!.gameDao()
    }


    fun fetchData(id: String,dataSource:RemoteDataSource) {
        getDetail(id,dataSource)
    }

    private fun getDetail(id: String,dataSource:RemoteDataSource) {
        val service = RemoteRepository(dataSource)
        viewModelScope.launch {
            service.getDetail(id).collect {
                when(it.status){
                    Status.SUCCESS->{
                        mutableDetailData.value = it.data
                        progressbarState.value = false
                    }
                    Status.ERROR->{
                        mutableDetailData.value = null
                        progressbarState.value = false
                    }
                    Status.LOADING->{
                        progressbarState.value = true
                    }
                }
            }
        }


    }


    fun insert(game: GameDetailBase) {
        GlobalScope.async {
            gameDao!!.insert(game)
        }
    }
}