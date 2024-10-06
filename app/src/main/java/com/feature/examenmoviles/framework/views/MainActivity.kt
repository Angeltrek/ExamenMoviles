package com.feature.examenmoviles.framework.views

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.feature.examenmoviles.databinding.ActivityMainBinding
import com.feature.examenmoviles.framework.viewmodel.MainViewModel

class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeBinding()
//        initializeObservers()

        // viewModel.get...
    }

    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

//    private fun initializeObservers() {
////        viewModel.data.observe(this) { deta -<
////            data?.let{
////                setupRecyclerView(it.parameter)
////            }
////        }
//        TODO("Not yet implemented")
//    }
//
//    private fun setupRecyclerView(data: List<String>) {
//        TODO("Not yet implemented")
//    }

}