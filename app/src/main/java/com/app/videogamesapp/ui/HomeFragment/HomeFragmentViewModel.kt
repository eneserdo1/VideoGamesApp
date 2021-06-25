package com.app.videogamesapp.ui.HomeFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.videogamesapp.data.Repository.Repository
import com.app.videogamesapp.model.ListResponseBase
import com.app.videogamesapp.model.Results

class HomeFragmentViewModel:ViewModel() {
    private val service = Repository()
    var mutableListData : MutableLiveData<ListResponseBase> = MutableLiveData()
    var progressbarValue : MutableLiveData<Boolean> = MutableLiveData()
    var isSearchedData : MutableLiveData<Boolean> = MutableLiveData()


    fun getAllList(){
        progressbarValue.value = true
        service.getList { data->
            if (data != null){
                mutableListData.value = data
                progressbarValue.value = false
            }else{
                mutableListData.value = null
                progressbarValue.value = false
            }
        }
    }


    fun filterList(term: String, adapter: GameListAdapter) {
        if (term.length > 2 ) {
            println("term-  $term")
            val list = adapter.originalList.filter {
                it.name!!.contains(term, true)
            }
            adapter.filterList = list as ArrayList<Results>
            adapter.notifyDataSetChanged()
            isSearchedData.value = !adapter.filterList.isNullOrEmpty()

        } else {
            isSearchedData.value = !adapter.originalList.isNullOrEmpty()
            adapter.filterList = adapter.originalList
            adapter.notifyDataSetChanged()

        }

    }
}