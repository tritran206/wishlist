package com.example.wishlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.wishlist.data.DataSource
import com.example.wishlist.data.model.Product
import com.example.wishlist.databinding.FragmentProductDetailBinding

private const val ARG_PRODUCT_INDEX = "productIndex"

class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel:ProductViewModel by activityViewModels()
    private var productIndex: Int = 0
    private var product: Product? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productIndex = it.getInt(ARG_PRODUCT_INDEX)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        val productList = viewModel.productList
        product = productList[productIndex]
        bindButton()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewProductName.text = product?.name
        binding.textViewProductPrice.text = product?.getPrice()
        binding.textViewDescription.text = product?.description
        Glide.with(binding.root)
            .load(product?.pictureUrl)
            .into(binding.imageViewProduct)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bindButton() {
        binding.buttonBuy.setOnClickListener {
            Toast.makeText(activity, "Thanks for buying ${product?.name}", Toast.LENGTH_LONG).show()
            this.findNavController().popBackStack()

        }
    }

}