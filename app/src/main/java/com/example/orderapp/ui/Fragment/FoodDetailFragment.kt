package com.example.orderapp.ui.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.orderapp.R
import com.example.orderapp.databinding.FragmentFoodDetailBinding
import com.example.orderapp.viewModels.FoodDetailViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodDetailFragment : Fragment() {


    private val viewModel : FoodDetailViewModel by viewModels()
    private lateinit var binding: FragmentFoodDetailBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentFoodDetailBinding.inflate(inflater,container,false)

        val bundle : FoodDetailFragmentArgs by navArgs()
        val item =bundle.item

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${item.yemek_resim_adi}"

        Glide.with(this).load(url).override(500,750).into(binding.imgFood)
        binding.textPrice.text = "₺ ${item.yemek_fiyat}"
        binding.txtFoodName.text = item.yemek_adi


        val qua = binding.textViewQuantity.text.toString().toIntOrNull() ?: 1
        val price = item.yemek_fiyat.toString().toInt()
        val totalPrice = (qua * price).toString()

        binding.totalPriceText.text = "₺ ${totalPrice}"

        binding.btnIncrease.setOnClickListener {
            val current = binding.textViewQuantity.text.toString().toIntOrNull() ?: 1
            val newCount = current + 1
            binding.textViewQuantity.text = newCount.toString()

            val total = newCount * item.yemek_fiyat.toString().toInt()
            binding.totalPriceText.text = "₺ $total"
        }

        binding.btnDecrease.setOnClickListener {
            val current = binding.textViewQuantity.text.toString().toIntOrNull() ?: 1
            val newCount = current - 1
            binding.textViewQuantity.text = newCount.toString()

            val total = newCount * item.yemek_fiyat.toString().toInt()
            binding.totalPriceText.text = "₺ $total"
        }

        binding.btnAddToCart.setOnClickListener {
            val current = binding.textViewQuantity.text.toString().toIntOrNull() ?: 1
            val  yemek_adi = item.yemek_adi
            val  yemek_resim_adi =item.yemek_resim_adi
            val yemek_fiyat =item.yemek_fiyat.toString().toInt()
            val kullanici_adi = "Ozanssmngl"

            viewModel.addCartItem(yemek_adi,yemek_resim_adi,yemek_fiyat,current,kullanici_adi)
            Snackbar.make(it,"The product has been added to your cart", Snackbar.LENGTH_SHORT).show()



        }

        binding.backArrow.setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.tohomefragment)

        }



        return binding.root
    }


}