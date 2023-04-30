package com.example.dmartassignment.ui

import android.content.Context
import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dmartassignment.databinding.ActivityListBinding

import com.example.dmartassignment.model.*
import com.example.dmartassignment.request.RequestData
import com.example.dmartassignment.ui.adapter.MainAdapter
import com.example.dmartassignment.util.AppConstants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class ListActivity : AppCompatActivity() {

    private var mTag = ListActivity::class.java.simpleName

    private lateinit var binding : ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var requestData : RequestData = getListData(this)

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

        binding.mainRecyclerView.setHasFixedSize(true)
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MainAdapter(dataList = recyclerViewList, context = this)
        binding.mainRecyclerView.adapter = adapter
    }

    private fun getListData(context: Context): RequestData {
        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("data.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.e(mTag, ioException.toString())
        }

        val listCountryType = object : TypeToken<RequestData>() {}.type
        return Gson().fromJson(jsonString, listCountryType)
    }
}