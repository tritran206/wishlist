package com.example.wishlist.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wishlist.data.model.Review

@Dao
interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(review: Review)

    // get all
    @Query("SELECT * FROM Review")
    fun getAllReviews(): LiveData<List<Review>>

    @Query("SELECT * FROM Review WHERE id = :reviewId")
    fun getReviewById(reviewId: String): Review

    //get review id
    @Query("SELECT * FROM Review WHERE productId = :productId")
    fun getReviewsByProductId(productId: String): LiveData<List<Review>>

    //delete review
    @Query("DELETE FROM Review WHERE id = :id")
    fun deleteByReviewById(id: String)
}