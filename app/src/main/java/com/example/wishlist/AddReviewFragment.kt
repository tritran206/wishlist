package com.example.wishlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wishlist.data.model.Review
import com.example.wishlist.databinding.FragmentAddReviewBinding
import com.example.wishlist.databinding.FragmentProductDetailBinding
import java.util.*

// TODO: Rename parameter arguments, choose names that match
private const val PRODUCT_ID = "productId"
private const val REVIEW_ID = "reviewId"

class AddReviewFragment : Fragment() {

    private var _binding: FragmentAddReviewBinding? = null
    private val binding get() = _binding!!
    private lateinit var productId: String
    lateinit var viewModel: ProductViewModel
    private var reviewId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productId = it.getString(PRODUCT_ID) ?: ""
            reviewId = it.getString(REVIEW_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddReviewBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = ProductViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProductViewModel::class.java)

        bindReview()

        bindButton()

        return binding.root
    }

    private fun bindReview() {
        reviewId?.let{
            val review = viewModel.getReview(it)

            binding.editTextName.setText(review.user)
            binding.editTextName.isEnabled = false
            binding.ratingBarRating.rating = review.rating.toFloat()
            binding.editTextReviewDescription.setText(review.text)
    }}

    private fun bindButton() {
        binding.buttonSubmit.setOnClickListener {
            val review = Review (
                productId = productId,
                id = reviewId ?: UUID.randomUUID().toString(),
                rating = binding.ratingBarRating.rating.toDouble(),
                user = binding.editTextName.text.toString(),
                text = binding.editTextReviewDescription.text.toString()
            )
            viewModel.addReview(review)

            Toast.makeText(activity, "Thanks for Reviewing", Toast.LENGTH_LONG).show()
            this.findNavController().popBackStack()
        }
    }

}