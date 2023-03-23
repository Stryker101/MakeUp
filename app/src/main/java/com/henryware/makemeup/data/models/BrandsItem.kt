package com.henryware.makemeup.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "products"
)
data class BrandsItem(
    @PrimaryKey
    val id: Int?,
//    val api_featured_image: String?,
    val brand: String?,
//    val category: String?,
//    val created_at: String?,
//    val currency: String?,
    val description: String?,
    val image_link: String?,
    val name: String?,
    val price: String?,
//    val price_sign: String?,
//    val product_api_url: String?,
//    val product_colors: List<ProductColor>?,
//    val product_link: String?,
    val product_type: String?
//    val rating: Double?,
//    val tag_list: List<String>?,
//    val updated_at: String?,
//    val website_link: String?
) : Serializable
