package com.example.dmartassignment.ui.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.example.dmartassignment.R
import com.example.dmartassignment.databinding.ChildRecyclerViewBinding
import com.example.dmartassignment.databinding.ItemBannerBinding
import com.example.dmartassignment.model.*
import com.example.dmartassignment.request.Item
import com.example.dmartassignment.util.AppUtil

class MainAdapter(private val dataList: MutableList<DataItem>,
                  private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            R.layout.item_banner -> {
                val binding = ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BannerItemViewHolder(binding)
            }
            else -> {
                val binding = ChildRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ChildRecyclerViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BannerItemViewHolder -> {
                dataList[position].banner?.let { holder.bindBannerView(banner = it) }
            }
            is ChildRecyclerViewHolder -> {
                when(dataList[position].viewType) {
                    DataItemType.LIST -> {
                        dataList[position].dataList?.let { holder.bindListRecyclerView(dataList = it) }
                    }
                    DataItemType.GRID -> {
                        dataList[position].dataGrid?.let { holder.bindGridRecyclerView(dataGrid = it) }
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataList[position].viewType) {
            DataItemType.BANNER -> R.layout.item_banner

            else -> R.layout.child_recycler_view
        }
    }

    inner class BannerItemViewHolder(private val binding: ItemBannerBinding)
        : RecyclerView.ViewHolder(binding.root)
    {
        fun bindBannerView(banner: Banner) {
            loadImageData(
                imageUrl = banner.image.toString(),
                imageView = binding.bannerIv,
                context = context
            )
        }

        private fun loadImageData(imageUrl : String, imageView : ImageView, context: Context) {
            Glide.with(context)
                .asBitmap()
                .load(imageUrl)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        bitmap: Bitmap,
                        transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                    ) {
                        var width = bitmap.width
                        var height = bitmap.height

                        if(width > 600) {
                            binding.bannerIv.scaleType = ImageView.ScaleType.FIT_XY
                        }
                        else {
                            binding.bannerIv.scaleType = ImageView.ScaleType.FIT_CENTER
                        }

                        val params: ViewGroup.LayoutParams = binding.bannerIv.layoutParams
                        params.height = height
                        binding.bannerIv.layoutParams = params

                        AppUtil.loadImage(
                            imageUrl = imageUrl,
                            imageView = imageView,
                            context = context
                        )
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })
        }
    }

    inner class ChildRecyclerViewHolder(private val binding: ChildRecyclerViewBinding)
        : RecyclerView.ViewHolder(binding.root)
    {
        init {
            binding.childRecyclerView.setHasFixedSize(false)
        }

        fun bindListRecyclerView(dataList: DataList) {
            val adapter = ChildAdapter(viewType = DataItemType.LIST,
                                        itemList = dataList.list,
                                        context = context)

            binding.childRecyclerView.layoutManager = GridLayoutManager(context, 1)
            binding.childRecyclerView.adapter = adapter
        }

        fun bindGridRecyclerView(dataGrid: DataGrid)
        {
            val adapter = ChildAdapter(viewType = DataItemType.GRID,
                                        itemList = dataGrid.list,
                                        context = context)

            binding.childRecyclerView.layoutManager = GridLayoutManager(context, dataGrid.grid?.toInt()!!)
            binding.childRecyclerView.adapter = adapter
        }
    }
}