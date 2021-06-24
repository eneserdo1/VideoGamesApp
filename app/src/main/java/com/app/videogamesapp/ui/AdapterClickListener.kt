package com.app.videogamesapp.ui

import com.app.videogamesapp.model.GameDetailBase
import com.app.videogamesapp.model.Results

interface AdapterClickListener {

    fun selectData(data:Results)
    fun selectFavouriteData(data:GameDetailBase)

}