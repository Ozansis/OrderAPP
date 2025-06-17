package com.example.orderapp.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.orderapp.data.entity.CartFood
import com.example.orderapp.databinding.CartViewBinding
import com.example.orderapp.viewModels.CartViewModel

class CartItemAdapter(
    private val context: Context,
    private var list: List<CartFood>,
    private val viewModel: CartViewModel
) : RecyclerView.Adapter<CartItemAdapter.CardViewHolder>() {

    inner class CardViewHolder(val view: CartViewBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = CartViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = list[position]
        val t = holder.view

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${item.yemek_resim_adi}"
        Glide.with(context).load(url).override(500, 750).into(t.imgFoodThumb)

        val price = item.yemek_fiyat.toInt()
        val quantity = item.yemek_siparis_adet.toInt()
        val totalPrice = price * quantity

        t.txtItemName.text = item.yemek_adi
        t.txtPrice.text = "₺ $price"
        t.txtQuantity.text = quantity.toString()
        t.txtTotalPrice.text = "₺ $totalPrice"

        t.btnDelete.setOnClickListener {
            val pos = holder.adapterPosition
            if (pos != RecyclerView.NO_POSITION) {

                removeAt(pos)

                viewModel.deleteFood(item.sepet_yemek_id.toInt(), "Ozanssmngl")
            }
        }
    }

    override fun getItemCount(): Int = list.size

    fun updateList(newList: List<CartFood>) {
        list = newList
        notifyDataSetChanged()
    }

    fun removeAt(position: Int) {
        val mutableList = list.toMutableList()
        mutableList.removeAt(position)
        list = mutableList
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, list.size - position)
    }
}
