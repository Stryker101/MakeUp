package com.henryware.makemeup.utils

sealed class ProductListEvent {
    data class NavigateToDetailScreen(val productId: String) : ProductListEvent()
}
