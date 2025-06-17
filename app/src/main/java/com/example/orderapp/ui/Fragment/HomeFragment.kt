package com.example.orderapp.ui.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.orderapp.databinding.FragmentHomeBinding
import com.example.orderapp.ui.Adapter.FoodAdapter
import com.example.orderapp.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.appcompat.widget.SearchView
import com.example.orderapp.data.entity.Food


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel : HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var fullList : List<Food>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        val adapter = FoodAdapter(requireContext(),emptyList())
        binding.rv.adapter = adapter
        binding.rv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        viewModel.food.observe(viewLifecycleOwner) {list ->
            adapter.updateList(list)
            fullList = list

        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val filtered = fullList.filter { it.yemek_adi.contains(query.orEmpty(), ignoreCase = true) }
                adapter.updateList(filtered)
                return true

            }

            override fun onQueryTextChange(newText: String?): Boolean {

                val filtered = fullList.filter { it.yemek_adi.contains(newText.orEmpty(), ignoreCase = true) }
                adapter.updateList(filtered)
                return true
            }


        })











        return binding.root
    }


}

