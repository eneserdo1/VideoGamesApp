package com.app.videogamesapp.ui.FavouriteFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.app.videogamesapp.model.GameDetailBase
import com.application.wgo.Sqlite.GameDao
import com.application.wgo.Sqlite.GameRoomDatabase

class FavouriteViewModel(application: Application) : AndroidViewModel(application) {
    var gameDao : GameDao?=null
    val favouriteGames: LiveData<List<GameDetailBase>>

    init {
        val gameDb = GameRoomDatabase.getDatabase(application)
        gameDao = gameDb!!.gameDao()
        favouriteGames = gameDao!!.allGames
    }


}