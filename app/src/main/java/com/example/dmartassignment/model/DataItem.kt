package com.example.dmartassignment.model

import com.example.dmartassignment.request.Item

data class DataItem(val viewType : Int) {
    var banner: Banner? = null
    var dataList: DataList? = null
    var dataGrid: DataGrid? = null

    constructor(viewType: Int, banner: Banner) : this(viewType) {
        this.banner = banner
    }

    constructor(viewType: Int, dataList: DataList) : this(viewType) {
        this.dataList = dataList
    }

    constructor(viewType: Int, dataGrid: DataGrid) : this(viewType) {
        this.dataGrid = dataGrid
    }
}

data class Banner(val image: String?)

data class DataList(val list: ArrayList<Item>)

data class DataGrid(val category: String?, val grid: String?, val list: ArrayList<Item>)