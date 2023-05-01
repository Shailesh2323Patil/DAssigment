package com.example.dmartassignment.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.dmartassignment.model.*
import com.example.dmartassignment.request.RequestData
import com.example.dmartassignment.util.AppConstants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class ListViewModel(application: Application) : AndroidViewModel(application) {

    private var mTag = ListViewModel::class.java.simpleName

    private val context = getApplication<Application>().applicationContext

    var mListData = MutableLiveData<MutableList<DataItem>>()

    fun getListData() {
        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("data.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.e(mTag, ioException.toString())
        }

        val listItemType = object : TypeToken<RequestData>() {}.type

        var requestData : RequestData = Gson().fromJson(jsonString, listItemType)

        var recyclerViewList = mutableListOf<DataItem>()

        requestData.data.map { data ->
            when(data.type) {
                AppConstants.typeBanner -> recyclerViewList.add(DataItem(viewType = DataItemType.BANNER, Banner(image = data.image)))

                AppConstants.typeDataList -> recyclerViewList.add(DataItem(viewType = DataItemType.LIST, DataList(list = data.list)))

                else -> {
                    recyclerViewList.add(DataItem(viewType = DataItemType.GRID, DataGrid(category = data.category, grid = data.grid, list = data.list)))
                }
            }
        }

        mListData.postValue(recyclerViewList)
    }
}