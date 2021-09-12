package com.example.wishlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.wishlist.data.DataSource
import com.example.wishlist.databinding.FragmentProductDetailBinding

private const val ARG_PRODUCT_INDEX = "productIndex"

class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel:ProductViewModel by activityViewModels()
    private var productIndex: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productIndex = it.getInt(ARG_PRODUCT_INDEX)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        val productList = viewModel.productList
        val product = productList?.get(productIndex!!)
        binding.textViewProductName.text = product?.name
        binding.textViewProductPrice.text = product?.getPrice()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}