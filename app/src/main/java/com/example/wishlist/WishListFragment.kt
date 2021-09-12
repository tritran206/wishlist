package com.example.wishlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.wishlist.data.DataSource
import com.example.wishlist.databinding.FragmentWishListBinding

class WishListFragment :
    Fragment(),
    OnProductClickedListener
{

    private var _binding: FragmentWishListBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishListBinding.inflate(inflater, container, false)
        val data = DataSource().getProducts()
        adapter = ProductAdapter(data, this)
        val recyclerProducts = binding.recyclerProducts
        recyclerProducts.adapter = adapter
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onProductClicked(productIndex: Int) {
        this.findNavController().navigate(
            WishListFragmentDirections.actionWishListFragmentToProductDetailFragment(productIndex)
        )
    }
}

interface OnProductClickedListener {
    fun onProductClicked(productIndex: Int)
}