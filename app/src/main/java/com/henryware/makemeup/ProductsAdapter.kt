package com.henryware.makemeup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.henryware.makemeup.databinding.ProductItemBinding
import com.henryware.makemeup.response.BrandsItem

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this)
                .load(item.image_link)
                .placeholder(R.drawable.no_ivailable)
                .into(holder.binding.imageView)
            holder.binding.productName.text = item.name
            holder.binding.productBrand.text = item.brand
            setOnClickListener {
                onItemClickListener?.let { it(item) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ItemViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<BrandsItem>() {
        override fun areItemsTheSame(oldItem: BrandsItem, newItem: BrandsItem): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: BrandsItem, newItem: BrandsItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    private var onItemClickListener: ((BrandsItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (BrandsItem) -> Unit) {
        onItemClickListener = listener
    }
}
