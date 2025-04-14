package com.example.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activity_checkout.checkout_cart
import com.example.activity_checkout.order_success
import com.example.adapter.adapter_ordeerhistory
import com.example.order_app.R
import com.example.order_app.Wishlist
import com.example.order_app.databinding.FragmentOrderHistoryBinding
import com.example.viewmodel.order_history_viewmodel

class order_history : Fragment() {
    private lateinit var binding: FragmentOrderHistoryBinding
    private lateinit var viewModel: order_history_viewmodel
    private lateinit var adapter: adapter_ordeerhistory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding= FragmentOrderHistoryBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this).get(order_history_viewmodel::class.java)
        adapter= adapter_ordeerhistory(mutableListOf())
        binding.recyclerOrderhistory.adapter=adapter
        binding.recyclerOrderhistory.layoutManager = LinearLayoutManager(requireContext())

        viewModel.itemList.observe(viewLifecycleOwner) { items ->
            adapter.updateData(items)

        }
        viewModel.fetchItems()

        return binding.root
    }


}
