package com.example.orderapp.ui.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orderapp.R
import com.example.orderapp.databinding.FragmentCartBinding
import com.example.orderapp.ui.Adapter.CartItemAdapter
import com.example.orderapp.viewModels.CartViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private val viewModel : CartViewModel by viewModels()
    private lateinit var binding: FragmentCartBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCartBinding.inflate(inflater,container,false)


        val adapter = CartItemAdapter(requireContext(),emptyList(),viewModel)
        binding.rv.adapter = adapter
        binding.rv.layoutManager= LinearLayoutManager(requireContext())

        viewModel.cartFood.observe(viewLifecycleOwner) { list ->

            adapter.updateList(list)

            if (list.isEmpty()) {
                binding.rv.visibility = View.GONE
                binding.txtTotal.text = "₺ 0"
            } else {
                binding.rv.visibility = View.VISIBLE
                val total = list.sumOf { it.yemek_fiyat.toInt() * it.yemek_siparis_adet.toInt() }
                binding.txtTotal.text = "₺ $total"
            }
        }


        binding.backArrow.setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.toHome)

        }

        binding.buttonConfirm.setOnClickListener {

            viewModel.cleanCart("Ozanssmngl")

            adapter.updateList(emptyList())

            binding.txtTotal.text = "₺ 0"
            binding.rv.visibility = View.GONE

            Snackbar.make(it,"Your cart has been confirmed", Snackbar.LENGTH_SHORT).show()



        }








        return binding.root
    }


}