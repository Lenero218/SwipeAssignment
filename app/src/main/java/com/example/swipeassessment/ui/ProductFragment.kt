package com.example.swipeassessment.ui

import android.os.Build
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swipeassessment.ui.Adapter.ProductAdapter
import com.example.swipeassessment.Utility.Resource
import com.example.swipeassessment.databinding.FragmentFirstBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ViewModelOwner
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ProductFragment : Fragment() {

    private val viewModel : ProductViewModel by viewModel(owner = {
        ViewModelOwner.from(
            requireActivity(),
            requireActivity()
        )
    })
    lateinit var productAdapter: ProductAdapter

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.searchProduct = ::searchedProduct

        setupRecyclerView()


        lifecycleScope.launch {
            viewModel.productDetails.collectLatest {response->
                when(response){
                    is Resource.Success ->{
                            hideProgressBar()
                            response.data?.let{productDetails->
                                    productAdapter.differ.submitList(productDetails)
                            }
                    }

                    is Resource.loading -> {
                        showProgressBar()
                    }

                    is Resource.Error -> {
                        hideProgressBar()
                        response.message?.let{
                            Toast.makeText(activity,"An error occured", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun requestFocus(){

        binding?.searchView?.requestFocus()

    }

    private fun showProgressBar(){
        binding?.paginationProgressBar?.visibility = View.VISIBLE

    }

    private fun hideProgressBar(){
         binding?.paginationProgressBar?.visibility = View.INVISIBLE

    }

    private fun searchedProduct(search : Editable){
        viewModel.searchProduct(search.toString())
    }

    private fun setupRecyclerView(){
        productAdapter = ProductAdapter()

        binding?.rvProductDetails?.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}