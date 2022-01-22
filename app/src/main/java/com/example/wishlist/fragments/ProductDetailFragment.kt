package com.example.wishlist.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.wishlist.NoProductIdException
import com.example.wishlist.R
import com.example.wishlist.adapters.ReviewAdapter
import com.example.wishlist.data.model.Product
import com.example.wishlist.databinding.FragmentProductDetailBinding
import com.example.wishlist.viewmodel.ProductViewModel

private const val PRODUCT_ID = "productId"

class ProductDetailFragment : Fragment(), OnReviewClickedListener
{

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private var productId: String = ""
    private lateinit var product: Product
    private lateinit var viewModel: ProductViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productId = it.getString(PRODUCT_ID) ?: throw NoProductIdException()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel()
        product = viewModel.getProduct(productId)
        bindButton()
        bindRecyclerView()
        bindProductView()
        bindSwipeRefresh()
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

    private fun getViewModel() {
        val application = requireNotNull(this.activity).application
        viewModel = ProductViewModel.getInstance(application)
    }

    private fun bindProductView() {
        binding.textViewProductName.text = product.productName
        binding.textViewProductPrice.text = product.price
        binding.textViewDescription.text = product.shortDescription
        Glide.with(binding.root)
            .load(getString(R.string.base_url) + product.productImage)
            .into(binding.imageViewProduct)
    }

    private fun bindButton() {
        binding.buttonBuy.setOnClickListener {
            viewModel.addProductToCart(product.productId)
            Toast.makeText(activity, "Thanks for buying ${product.productName}", Toast.LENGTH_LONG).show()
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

    private fun bindSwipeRefresh(){
        binding.swipeRefreshLayout.setOnRefreshListener {
            this.view?.invalidate()
            binding.swipeRefreshLayout.isRefreshing = false
            Toast.makeText(activity, "Refreshing!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToCart() {
        this.findNavController().navigate(ProductDetailFragmentDirections.actionProductDetailFragmentToShoppingCartFragment())
    }
}

interface OnReviewClickedListener {
    fun onReviewClicked(productId: String)
}