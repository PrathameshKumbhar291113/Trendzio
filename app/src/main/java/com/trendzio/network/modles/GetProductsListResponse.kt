package com.trendzio.network.modles


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class GetProductsListResponse(
    @SerializedName("limit")
    var limit: Int?, // 30
    @SerializedName("products")
    var products: List<Product?>?,
    @SerializedName("skip")
    var skip: Int?, // 0
    @SerializedName("total")
    var total: Int? // 100
) : Parcelable {
    @Parcelize
    data class Product(
        @SerializedName("brand")
        var brand: String?, // Apple
        @SerializedName("category")
        var category: String?, // smartphones
        @SerializedName("description")
        var description: String?, // An apple mobile which is nothing like apple
        @SerializedName("discountPercentage")
        var discountPercentage: Double?, // 12.96
        @SerializedName("id")
        var id: Int?, // 1
        @SerializedName("images")
        var images: List<String?>?,
        @SerializedName("price")
        var price: Int?, // 549
        @SerializedName("rating")
        var rating: Double?, // 4.69
        @SerializedName("stock")
        var stock: Int?, // 94
        @SerializedName("thumbnail")
        var thumbnail: String?, // https://cdn.dummyjson.com/product-images/1/thumbnail.jpg
        @SerializedName("title")
        var title: String? // iPhone 9
    ) : Parcelable
}