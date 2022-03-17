package com.example.wishlist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.wishlist.viewmodel.ProductViewModel
import com.example.wishlist.adapters.ShoppingCartAdapter
import com.example.wishlist.adapters.OnCartClickedListener
import com.example.wishlist.databinding.FragmentShoppingCartBinding

class ShoppingCartFragment : Fragment(), OnCartClickedListener {
    private var _binding: FragmentShoppingCartBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: ProductViewModel
    private val adapter = ShoppingCartAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel()
        bindRecyclerView()
        bindLiveData()
        bindButtons()
    }

    private fun getViewModel() {
        val application = requireNotNull(this.activity).application
        viewModel = ProductViewModel.getInstance(application)
    }

    private fun bindRecyclerView() {
        binding.recyclerViewCart.adapter = adapter
    }

    private fun bindLiveData() {
        viewModel.cart.observe(viewLifecycleOwner) {
            val productList = viewModel.getProductsFromIds(it)
            if (productList.isEmpty()) {
                binding.containerEmptyList.visibility = View.VISIBLE
            } else{
                binding.containerEmptyList.visibility = View.INVISIBLE
            }
            adapter.submitList(productList)
        }
    }

    override fun removeItemFromCart(productId: String) {
        viewModel.removeItemById(productId)
    }

    private fun bindButtons() {
        binding.fabCart.setOnClickListener {
            viewModel.clearCart()
            Toast.makeText(activity, "You have checked out. Thanks for buying!", Toast.LENGTH_LONG).show()}

        binding.buttonEmptyList.setOnClickListener {
            this.findNavController().navigate(ShoppingCartFragmentDirections.actionShoppingCartFragmentToWishListFragment())
        }
    }
}