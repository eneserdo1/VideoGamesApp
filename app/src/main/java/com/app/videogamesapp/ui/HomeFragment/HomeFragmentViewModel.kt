package com.app.videogamesapp.ui.HomeFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.videogamesapp.data.Repository.RemoteRepository
import com.app.videogamesapp.data.remote.RemoteDataSource
import com.app.videogamesapp.model.ListResponseBase
import com.app.videogamesapp.model.Results
import com.app.weatherapplicationmvvm.utils.Status
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragmentViewModel : ViewModel() {
    var mutableListData: MutableLiveData<ListResponseBase> = MutableLiveData()
    var progressbarValue: MutableLiveData<Boolean> = MutableLiveData()
    var isSearchedData: MutableLiveData<Boolean> = MutableLiveData()


    fun getAllList(dataSource: RemoteDataSource) {
        val service = RemoteRepository(dataSource)

        viewModelScope.launch {
            service.getList().collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        mutableListData.value = it.data
                        progressbarValue.value = false
                    }
                    Status.LOADING -> {
                        progressbarValue.value = true
                    }
                    Status.ERROR -> {
                        mutableListData.value = null
                        progressbarValue.value = false
                    }
                }

            }
        }
    }


    fun filterList(term: String, adapter: GameListAdapter) {
        if (term.length > 2) {
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