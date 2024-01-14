package com.example.swipeassessment.ui.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.swipeassessment.Domain.model.ProductDetails
import com.example.swipeassessment.R

class ProductAdapter() : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val tvPrice : TextView = itemView.findViewById(R.id.tvPrice)
        val tvProductName : TextView = itemView.findViewById(R.id.tvProductName)
        val tvType : TextView = itemView.findViewById(R.id.tvType)
        val ivProductImage : ImageView = itemView.findViewById(R.id.ivProductImage)
        val tvTax : TextView = itemView.findViewById(R.id.tvTax)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout. item_product_preview, parent, false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = differ.currentList[position]

        holder.apply {
            Glide.with(itemView).load(currentItem.image).error(R.drawable.baseline_error_24).into(holder.ivProductImage)
            tvPrice.text = "Price - ${currentItem.price}"
            tvProductName.text = currentItem.product_name
            tvType.text = currentItem.product_type
            tvTax.text = "Tax - ${currentItem.tax}"
        }



    }

    private val differCallback = object : DiffUtil.ItemCallback<ProductDetails>(){
        override fun areItemsTheSame(oldItem: ProductDetails, newItem: ProductDetails): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ProductDetails, newItem: ProductDetails): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallback)

}
