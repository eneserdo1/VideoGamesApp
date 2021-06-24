package com.app.videogamesapp.ui.Detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.videogamesapp.data.GetDetail.DetailService
import com.app.videogamesapp.model.GameDetailBase
import com.app.videogamesapp.model.Results
import com.application.wgo.Sqlite.GameDao
import com.application.wgo.Sqlite.GameRoomDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class DetailViewModel(application: Application):AndroidViewModel(application) {
    var mutableDetailData:MutableLiveData<GameDetailBase> = MutableLiveData()
    var progressbarState:MutableLiveData<Boolean> = MutableLiveData()
    var gameDao : GameDao?=null


    fun fetchData(id:String){
        progressbarState.value = true
        getDetail(id)
    }

    private fun getDetail(id: String) {
        val service = DetailService()
        service.getDetail(id){response->
            if (response != null){
                mutableDetailData.value = response
                progressbarState.value = false
            }else{
                mutableDetailData.value = null
                progressbarState.value = false

            }
        }
    }

    init {
        val gameDb = GameRoomDatabase.getDatabase(application)
        gameDao = gameDb!!.gameDao()

    }

    fun insert(game: GameDetailBase){
        GlobalScope.async {
            gameDao!!.insert(game)
        }
    }
}