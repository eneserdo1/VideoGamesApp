package com.application.wgo.Sqlite

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.videogamesapp.model.GameDetailBase
import com.app.videogamesapp.model.Results


@Dao
interface GameDao {

    @Insert
    fun insert(game: GameDetailBase)

    @get:Query("SELECT * FROM games")
    val allGames: LiveData<List<GameDetailBase>>


    @Query("DELETE FROM games")
    fun deleteAllGames()


    @Query("SELECT * FROM games WHERE gameId = :id ")
    fun getGame(id:String):GameDetailBase


    @Query("DELETE FROM games WHERE gameId = :id ")
    fun deleteGame(id:String)
}