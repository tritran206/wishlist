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

        val application = requireNotNull(this.activity).application
        val viewModelFactory = ProductViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProductViewModel::class.java)
        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)

        val adapter = ShoppingCartAdapter(viewModel.getProductsForCart())
        binding.recyclerViewCart.adapter = adapter

        return binding.root
    }


}