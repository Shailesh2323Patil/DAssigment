package com.example.dmartassignment.ui

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dmartassignment.databinding.ActivityListBinding

import com.example.dmartassignment.model.*
import com.example.dmartassignment.ui.adapter.MainAdapter
import com.example.dmartassignment.viewmodel.ListViewModel

class ListActivity : AppCompatActivity() {

    private var mTag = ListActivity::class.java.simpleName

    private lateinit var binding : ActivityListBinding

    var mRecyclerViewList = mutableListOf<DataItem>()

    private lateinit var viewModel : ListViewModel

    private val mAdapter = MainAdapter(dataList = mRecyclerViewList, context = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ListViewModel::class.java]

        initView()
        setObserver()
        viewModel.getListData()
    }

    private fun initView() {
        binding.mainRecyclerView.setHasFixedSize(true)
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView.adapter = mAdapter
    }

    private fun setObserver() {
        viewModel.mListData.observe(this) { listData ->
            mRecyclerViewList.addAll(listData)
            mAdapter.notifyDataSetChanged()
        }
    }
}