package com.example.swipeassessment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.swipeassessment.Domain.model.PostProductDetails
import com.example.swipeassessment.Domain.model.ProductDetails
import com.example.swipeassessment.R
import com.example.swipeassessment.Remote.model.ProductDetailsDto
import com.example.swipeassessment.Utility.Resource
import com.example.swipeassessment.databinding.FragmentSecondBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ViewModelOwner
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddProductFragment : BottomSheetDialogFragment() {

    private val viewModel : ProductViewModel by viewModel(owner = {
        ViewModelOwner.from(
            requireActivity(),
            requireActivity()
        )
    })

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    private var productTypeString = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.spFilter?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, p3: Long) {
                productTypeString = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding?.addProduct ?.setOnClickListener {

            val productPriceString = binding?.productPrice?.text.toString()
            val productNameString = binding?.productName?.text.toString()


            val taxOnProductString = binding?.taxOnProduct?.text.toString()

            // Check for empty strings or handle invalid input gracefully
            val productPrice = if (productPriceString.isNotEmpty()) productPriceString.toDouble() else 0.0
            val taxOnProduct = if (taxOnProductString.isNotEmpty()) taxOnProductString.toDouble() else 0.0

            val productDetails = PostProductDetails( productPrice, productNameString, productTypeString, taxOnProduct)
            viewModel.postProduct(productDetails)
        }

        lifecycleScope.launch {
            viewModel.productDetails.collectLatest {response->

                when(response){
                    is Resource.Success -> {
                        hideProgressBar()

                        if(response.data == null){
                            Toast.makeText(requireActivity(),"Posted Successfully",Toast.LENGTH_SHORT).show()
                        }
                    }

                    is Resource.loading -> {
                        showProgressBar()
                    }

                    is Resource.Error -> {
                        hideProgressBar()
                        Toast.makeText(requireActivity(),"Error in posting, Please try again later!! ${response.message}",Toast.LENGTH_SHORT).show()

                    }
                }

            }
        }





    }

    private fun showProgressBar(){
        binding?.postDetailProgressBar?.visibility = View.VISIBLE

    }

    private fun hideProgressBar(){
        binding?.postDetailProgressBar?.visibility = View.INVISIBLE

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}