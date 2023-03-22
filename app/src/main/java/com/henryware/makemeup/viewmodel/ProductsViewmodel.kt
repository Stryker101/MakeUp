package com.henryware.makemeup.viewmodel // ktlint-disable filename

import androidx.lifecycle.* // ktlint-disable no-wildcard-imports
import com.henryware.makemeup.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: ProductsRepository
) : ViewModel() {
    val products = repository.getProducts().asLiveData()

}
