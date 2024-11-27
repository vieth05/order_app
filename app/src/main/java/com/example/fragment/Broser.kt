package com.example.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.Data.Product
import com.example.adapter.BrowseAdapter
import com.example.activity_checkout.checkout_cart
import com.example.order_app.R
import com.example.order_app.Wishlist
import com.example.order_app.databinding.BrowserBinding
import com.example.product_Detail.ProductDetail
import com.google.firebase.database.*

class BrowserFragment : Fragment(R.layout.browser) {

    private var _binding: BrowserBinding? = null
    private val binding get() = _binding!!
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val browseReference = firebaseDatabase.getReference("browse")
    private val dataList = mutableListOf<Product>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BrowserBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.navi.barTitle.text = "browse"
        binding.navi.notice.visibility = View.GONE

        binding.navi.wishIcon.setOnClickListener {
            startActivity(Intent(requireContext(), Wishlist::class.java))
        }

        binding.navi.order.setOnClickListener {
            startActivity(Intent(requireContext(), checkout_cart::class.java))
        }

        val adapter = BrowseAdapter(requireContext(),dataList){product ->
            val intent=Intent(requireContext(), ProductDetail::class.java)
            intent.putExtra("text", product.name)
            intent.putExtra("img", product.image)
            intent.putExtra("price", product.price)
            startActivity(intent)
        }
        binding.gvbs.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter
        }

        fetchData(adapter)
        binding.navi.searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(editable: Editable?) {
                val query = editable.toString()
                adapter.filter.filter(query)
            }
        })

        return view
    }

    private fun fetchData(adapter: BrowseAdapter) {
        showProgressBar()

        browseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(Product::class.java)
                    if (item != null) {
                        dataList.add(item)
                    }
                }
                adapter.notifyDataSetChanged()
                hideProgressBar()
            }

            override fun onCancelled(error: DatabaseError) {
                hideProgressBar()
                Toast.makeText(requireContext(), "Lỗi tải dữ liệu", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

