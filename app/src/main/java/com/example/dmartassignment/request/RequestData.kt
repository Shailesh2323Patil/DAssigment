package com.example.dmartassignment.request

import com.google.gson.annotations.SerializedName

data class RequestData(
    @SerializedName("data") var data: ArrayList<Data> = arrayListOf()
)

data class Data(
    @SerializedName("type") var type: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("grid") var grid: String? = null,
    @SerializedName("category") var category: String? = null,
    @SerializedName("list") var list: ArrayList<Item> = arrayListOf()
)

data class Item(
    @SerializedName("image") var image: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("value") var value: String? = null,
    @SerializedName("description") var description: String? = null
)