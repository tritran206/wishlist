package com.example.wishlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.wishlist.databinding.FragmentShoppingCartBinding

class ShoppingCartFragment : Fragment() {
    private var _binding: FragmentShoppingCartBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = requireNotNull(this.activity).application
        val viewModelFactory = ProductViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProductViewModel::class.java)

        val adapter = ShoppingCartAdapter()
        binding.recyclerViewCart.adapter = adapter

        viewModel.cart.observe(viewLifecycleOwner) {
            adapter.cartItems = viewModel.getProductsFromIds(it)
            adapter.notifyDataSetChanged()
        }
    }


}