package com.example.wishlist.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.wishlist.R
import com.example.wishlist.adapters.ProductAdapter
import com.example.wishlist.data.model.Product
import com.example.wishlist.databinding.FragmentWishListBinding
import com.example.wishlist.services.ProductService
import com.example.wishlist.viewmodel.ProductViewModel
import java.util.concurrent.Executor
import java.util.concurrent.Executors

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
        setHasOptionsMenu(true)
        _binding = FragmentWishListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel()
        bindLiveData()
        bindProductRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuItem_goToCart) { goToCart() }
        return super.onOptionsItemSelected(item)
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

    private fun goToCart() {
        this.findNavController().navigate(WishListFragmentDirections.actionWishListFragmentToShoppingCartFragment())
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