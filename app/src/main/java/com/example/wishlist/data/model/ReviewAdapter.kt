package com.example.wishlist.data.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wishlist.OnProductClickedListener
import com.example.wishlist.OnReviewClickedListener
import com.example.wishlist.databinding.ListItemReviewBinding

class ReviewAdapter(val reviewList: List<Review>, val listener: OnReviewClickedListener): RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    inner class ReviewViewHolder(val binding: ListItemReviewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding = ListItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviewList[position]
        holder.binding.reviewName.text = review.user
        holder.binding.reviewRating.text = review.rating.toString()
        holder.binding.reviewText.text = review.text

        holder.binding.root.setOnClickListener{
            listener.onReviewClicked(review.id)
        }

    }

    override fun getItemCount(): Int {
       return reviewList.size
    }
}