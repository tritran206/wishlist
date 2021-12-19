package com.example.wishlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wishlist.databinding.FragmentWishListBinding
import com.example.wishlist.viewmodel.ProductViewModel
import com.example.wishlist.viewmodel.ProductViewModelFactory

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel()
        bindLiveData()
        bindButton()
        bindProductRecyclerView()
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

    private fun getViewModel() {
        val application = requireNotNull(this.activity).application
        viewModel = ProductViewModel.getInstance(application)
    }

    private fun bindLiveData() {
        //Observer for liveData
        viewModel.productList.observe(viewLifecycleOwner, Observer {
            viewModel.updateCurrentProductList(it)
            adapter.submitList(it)
        })
    }

    private fun bindButton() {
        binding.buttonToCart.setOnClickListener {
            this.findNavController().navigate(WishListFragmentDirections.actionWishListFragmentToShoppingCartFragment())
        }
    }

    private fun bindProductRecyclerView() {
        adapter = ProductAdapter(listener = this)
        val recyclerProducts = binding.recyclerProducts
        recyclerProducts.adapter = adapter
    }
}

interface OnProductClickedListener {
    fun onProductClicked(productId: String)
}