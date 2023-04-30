package com.example.dmartassignment.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dmartassignment.R
import com.example.dmartassignment.databinding.ItemGridBinding
import com.example.dmartassignment.databinding.ItemListBinding
import com.example.dmartassignment.model.DataItemType
import com.example.dmartassignment.request.Item
import com.example.dmartassignment.util.AppUtil

class ChildAdapter(private val viewType: Int,
                   private val itemList: MutableList<Item>,
                   private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            DataItemType.LIST -> {
                val binding = ItemListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ListViewHolder(binding)
            }

            else -> {
                val binding = ItemGridBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return GridViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ListViewHolder -> {
                holder.bindListView(itemList[position])
            }

            is GridViewHolder -> {
                holder.bindGridView(itemList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    inner class ListViewHolder(private val itemListBinding: ItemListBinding)
        : RecyclerView.ViewHolder(itemListBinding.root)
    {
        fun bindListView(item : Item) {
            itemListBinding.txtName.text = item.name.toString()
            itemListBinding.txtValue.text = item.value.toString()
            itemListBinding.txtDescription.text = item.description.toString()

            AppUtil.loadImage(
                imageUrl = item.image.toString(),
                imageView = itemListBinding.imageView,
                context = context
            )
        }
    }

    inner class GridViewHolder(private val itemGridBinding: ItemGridBinding)
        : RecyclerView.ViewHolder(itemGridBinding.root)
    {
        fun bindGridView(item : Item) {
            AppUtil.loadImage(
                imageUrl = item.image.toString(),
                imageView = itemGridBinding.imageView,
                context = context
            )
        }
    }
}