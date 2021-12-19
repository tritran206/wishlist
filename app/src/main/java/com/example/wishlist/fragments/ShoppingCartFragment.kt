package com.example.wishlist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.wishlist.viewmodel.ProductViewModel
import com.example.wishlist.viewmodel.ProductViewModelFactory
import com.example.wishlist.ShoppingCartAdapter
import com.example.wishlist.databinding.FragmentShoppingCartBinding

class ShoppingCartFragment : Fragment() {
    private var _binding: FragmentShoppingCartBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: ProductViewModel
    private val adapter = ShoppingCartAdapter()

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
            adapter.submitList(viewModel.getProductsFromIds(it))
        }
    }
}