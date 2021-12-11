package com.example.wishlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
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
    lateinit var viewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishListBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = ProductViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProductViewModel::class.java)

        adapter = ProductAdapter(
            productList = viewModel.productList,
            listener = this
        )

        binding.buttonToCart.setOnClickListener { 
            this.findNavController().navigate(WishListFragmentDirections.actionWishListFragmentToShoppingCartFragment())
        }

        val recyclerProducts = binding.recyclerProducts
        recyclerProducts.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onProductClicked(productId: String) {
        this.findNavController().navigate(
            WishListFragmentDirections.actionWishListFragmentToProductDetailFragment(productId)
        )
    }
}

interface OnProductClickedListener {
    fun onProductClicked(productId: String)
}