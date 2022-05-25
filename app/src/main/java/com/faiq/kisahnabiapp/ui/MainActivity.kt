package com.faiq.kisahnabiapp.ui

import NabiAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.faiq.kisahnabiapp.data.NabiRespon
import com.faiq.kisahnabiapp.data.utills.OnItemClickCallback
import com.faiq.kisahnabiapp.databinding.ActivityMainBinding
import com.faiq.kisahnabiapp.ui.detail.DetailActivity



class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private var _viewModel : MainViewModel? = null
    private val viewModel get() = _viewModel as MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // inisialisasi untuk
        _viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.getData()

        viewModel.kisahResponse.observe(this) { showData(it) }
        viewModel.isLoading.observe(this) { showLoading(it) }
        viewModel.isError.observe(this) { showError(it) }
    }

    private fun showData(data: List<NabiRespon>?) {
        binding.recyclerMain.apply {
            val mAdapter = NabiAdapter()
            mAdapter.setData(data)
            layoutManager= GridLayoutManager(this@MainActivity, 2)
            adapter = mAdapter
            mAdapter.setOnItemClickCallback(object : OnItemClickCallback {
                override fun OnItemClicked(item: NabiRespon) {
                    startActivity(
                        Intent(applicationContext, DetailActivity::class.java)
                            .putExtra(DetailActivity.EXTRA_DATA, item)
                    )
                }
            })

        }
    }

    private fun showLoading(isLoading: Boolean?) {
        if (isLoading == true){
            binding.progressMain.visibility = View.VISIBLE
            binding.recyclerMain.visibility = View.INVISIBLE
        }
        binding.progressMain.visibility = View.INVISIBLE
        binding.recyclerMain.visibility = View.VISIBLE
    }

    private fun showError(error: Throwable?) {

        Log.e("MainActivity", "Show Error: $error")
    }
}