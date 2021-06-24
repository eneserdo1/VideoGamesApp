package com.application.wgo.Sqlite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.videogamesapp.model.GameDetailBase
import com.app.videogamesapp.model.Results

@Database(entities = [GameDetailBase::class], version = 1)
abstract class GameRoomDatabase : RoomDatabase(){
    abstract fun gameDao(): GameDao

    companion object {
        private var gameRoomInstance: GameRoomDatabase? = null

        fun getDatabase(context: Context): GameRoomDatabase? {
            if (gameRoomInstance == null){
                synchronized(GameRoomDatabase::class.java) {
                    if (gameRoomInstance == null){
                        gameRoomInstance = Room.databaseBuilder(context.applicationContext,
                            GameRoomDatabase::class.java, "game_database").build()
                    }
                }
            }
            return gameRoomInstance
        }
    }
}