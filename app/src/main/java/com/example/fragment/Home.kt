package com.example.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Datarepo.categories
import com.example.Datarepo.store_follow
import com.example.adapter.adapter_choose_gv
import com.example.adapter.adapter_newprd
import com.example.adapter.adapter_store_follow
import com.example.activity_checkout.checkout_cart
import com.example.model.new_prd
import com.example.order_app.Wishlist
import com.example.product_Detail.*
import com.example.order_app.databinding.HomeBinding

class HomeFragment : Fragment() {

   private lateinit var binding: HomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeBinding.inflate(inflater, container, false)

        setupListeners()
        setupNewProductsRecyclerView()
        setupStoreFollowRecyclerView()
        setupCategoryGridView()
        setupPopularproduct()

        return binding.root
    }


    private fun setupListeners() {
        binding.bar.wishIcon.setOnClickListener {
            startActivity(Intent(requireContext(), Wishlist::class.java))
        }
        binding.bar.order.setOnClickListener {
            startActivity(Intent(requireContext(), checkout_cart::class.java))
            binding.bar.notice.visibility=View.INVISIBLE

        }
    }

    private fun setupNewProductsRecyclerView() {
        new_prd.fetchProducts(binding.progressBar) { products ->
            products.forEach { product ->
                println("Product: ${product.name}")
            }
        }

        val adapter = adapter_newprd(new_prd.newprds) { selectedProduct ->
            val intent = Intent(requireContext(), ProductDetail::class.java).apply {
                putExtra("text", selectedProduct.name) // Tên sản phẩm
                putExtra("img", selectedProduct.image) // Ảnh sản phẩm
                putExtra("price", selectedProduct.price) // Giá sản phẩm
            }
            startActivity(intent)
        }

        binding.rvnewprd.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }


    private fun setupPopularproduct() {
        com.example.model.popular_prd.fetchProducts(binding.progressBar2) { products ->
            products.forEach { product ->
                println("pupular_prd: ${product.name}")
            }
        }
        val adapter = adapter_newprd(com.example.model.popular_prd.popular) { selectedProduct ->
            val intent = Intent(requireContext(), ProductDetail::class.java).apply {
                putExtra("text", selectedProduct.name)
                putExtra("img", selectedProduct.image)
                putExtra("price", selectedProduct.price)
            }
            startActivity(intent)
        }
        binding.rvpprd.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupStoreFollowRecyclerView() {
        val adapter = adapter_store_follow(store_follow.stores)
        binding.rvstore.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }
    private fun setupCategoryGridView() {
        val adapter = adapter_choose_gv(requireContext(), categories.categories)
        binding.gvchoose.adapter = adapter

        binding.gvchoose.setOnItemClickListener { _, _, position, _ ->
            val targetActivity = when (position) {
                0 -> beverages::class.java
                1 -> BreadAndBakeryActivity::class.java
                2 -> Vegetables::class.java
                3 -> Fruit::class.java
                4 -> Egg::class.java
                5 -> FrozenVegetablesActivity::class.java
                6 -> HomeCare::class.java
                7 -> PetCare::class.java
                else -> null
            }
            targetActivity?.let { startActivity(Intent(requireContext(), it)) }
        }
    }
}
