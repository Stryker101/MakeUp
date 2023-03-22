package com.henryware.makemeup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.henryware.makemeup.databinding.FragmentProductDetailsBinding
import com.henryware.makemeup.response.BrandsItem

class ProductDetailsFragment : Fragment() {

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: ProductDetailsFragmentArgs by navArgs()
    lateinit var brandsItem: BrandsItem

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        brandsItem = args.item
        Glide.with(requireContext())
            .load(brandsItem.image_link)
            .placeholder(R.drawable.no_ivailable)
            .into(binding.itemImage)
        binding.itemName.text = brandsItem.name
        binding.itemPrice.text = brandsItem.price
        binding.itemDescription.text = brandsItem.description
    }
}
