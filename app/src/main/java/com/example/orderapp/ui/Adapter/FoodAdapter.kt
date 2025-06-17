package com.example.orderapp.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.orderapp.data.entity.Food
import com.example.orderapp.databinding.FoodViewBinding
import com.example.orderapp.ui.Fragment.HomeFragmentDirections

class FoodAdapter(val context: Context, var foodList: List<Food>): RecyclerView.Adapter<FoodAdapter.CardViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = FoodViewBinding.inflate(LayoutInflater.from(context))
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val food = foodList[position]
        var v = holder.view

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.yemek_resim_adi}"

        Glide.with(context).load(url).override(500,750).into(v.imgFood)

        v.txtFiyat.text = "₺${food.yemek_fiyat}"
        v.txtName.text = food.yemek_adi

        val favIcon = v.btnfavori

        favIcon.setOnClickListener {
            favIcon.isSelected = !favIcon.isSelected

        }

        v.imageButton.setOnClickListener {
            val gecis = HomeFragmentDirections.homeToDetail(item = food)
            Navigation.findNavController(it).navigate(gecis)

        }


    }

    override fun getItemCount(): Int {
        return foodList.size
    }


    inner class CardViewHolder(val view : FoodViewBinding): RecyclerView.ViewHolder(view.root)

    fun updateList(newList: List<Food>) {
        foodList = newList
        notifyDataSetChanged()
    }

}