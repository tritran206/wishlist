package com.example.wishlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.wishlist.data.model.Product
import com.example.wishlist.data.model.ReviewAdapter
import com.example.wishlist.databinding.FragmentProductDetailBinding

private const val PRODUCT_ID = "productId"

class ProductDetailFragment : Fragment(), OnReviewClickedListener
{

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private var productId: String = ""
    private lateinit var product: Product
    private lateinit var viewModel:ProductViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productId = it.getString(PRODUCT_ID) ?: ""
        }
        val application = requireNotNull(this.activity).application
        val viewModelFactory = ProductViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProductViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        product = viewModel.getProduct(productId)
        bindButton()
        bindRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewProductName.text = product.name
        binding.textViewProductPrice.text = product.getFormatPrice()
        binding.textViewDescription.text = product.description
        Glide.with(binding.root)
            .load(product.pictureUrl)
            .into(binding.imageViewProduct)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bindButton() {
        binding.buttonBuy.setOnClickListener {
            viewModel.addProductToCart(product.id)
            Toast.makeText(activity, "Thanks for buying ${product.name}", Toast.LENGTH_LONG).show()
            this.findNavController().popBackStack()
        }

        binding.buttonAddReview.setOnClickListener {
            this.findNavController().navigate(
                ProductDetailFragmentDirections.actionProductDetailFragmentToAddReviewFragment(productId, null)
            )
        }
    }

    private fun bindRecyclerView() {
        val adapter = ReviewAdapter( this)

        viewModel.reviewList.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.submitList(viewModel.filterReviews(productId, it))
            }
        })
        binding.recyclerViewReview.adapter = adapter
    }

    override fun onReviewClicked(reviewId: String) {
        this.findNavController().navigate(
            ProductDetailFragmentDirections.actionProductDetailFragmentToAddReviewFragment(productId, reviewId)
        )
    }


}

interface OnReviewClickedListener {
    fun onReviewClicked(productId: String)
}