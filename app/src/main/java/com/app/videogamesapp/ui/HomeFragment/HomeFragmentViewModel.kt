package com.app.videogamesapp.ui.HomeFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.videogamesapp.data.GetList.ListService
import com.app.videogamesapp.model.ListResponseBase

class HomeFragmentViewModel:ViewModel() {
    var mutableListData : MutableLiveData<ListResponseBase> = MutableLiveData()


    fun getAllList(){

        val service = ListService()
        service.getList { data->
            if (data != null){
                mutableListData.value = data

            }else{
                mutableListData.value = null

            }
        }
    }
}