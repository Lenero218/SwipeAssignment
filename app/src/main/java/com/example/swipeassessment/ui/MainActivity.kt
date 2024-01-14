package com.example.swipeassessment.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.swipeassessment.R
import com.example.swipeassessment.Utility.NetworkManager
import com.example.swipeassessment.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)


        binding.addProduct.setOnClickListener {
            navController.navigate(R.id.action_FirstFragment_to_SecondFragment)
        }


        val networkNetwork = NetworkManager(this)

        networkNetwork.observe(this){networkConnected ->
            if(networkConnected){
                val snackBar = Snackbar.make(binding.root,"Successfully Connected with Internet", Snackbar.LENGTH_SHORT)
                    snackBar.show()
              //  Toast.makeText(this,"Connected Successfully",Toast.LENGTH_LONG).show()
            }else{
                val snackBar =  Snackbar.make(binding.root,"No internet connection, switching to OFFLINE", Snackbar.LENGTH_INDEFINITE)
                snackBar.setAction("OK") {
                    snackBar.dismiss()
                }
                snackBar.show()
               // Toast.makeText(this,"No Internet Connection, going offline",Toast.LENGTH_LONG).show()
            }
        }

    }



    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}