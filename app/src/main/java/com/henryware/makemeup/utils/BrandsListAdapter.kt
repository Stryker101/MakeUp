package com.henryware.makemeup.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.henryware.makemeup.databinding.ProductItemBinding
import com.henryware.makemeup.response.BrandsItem

class BrandsListAdapter(private val listener: OnItemClickListener) :
    ListAdapter<BrandsItem, BrandsListAdapter.BrandsViewHolder>(BrandsListComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandsViewHolder {
        return BrandsViewHolder(
            ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: BrandsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    class BrandsViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(brandsItem: BrandsItem, listener: OnItemClickListener) {
            binding.apply {
                root.setOnClickListener {
                    listener.onItemClick(brandsItem)
                }

                productName.text = brandsItem.brand
            }
        }
    }

    class BrandsListComparator : DiffUtil.ItemCallback<BrandsItem>() {
        override fun areItemsTheSame(oldItem: BrandsItem, newItem: BrandsItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: BrandsItem, newItem: BrandsItem) =
            oldItem.id == newItem.id
    }

    interface OnItemClickListener {
        fun onItemClick(brandsItem: BrandsItem)
    }
}
