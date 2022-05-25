package com.faiq.kisahnabiapp.ui.detail

import android.content.Intent
import android.nfc.NfcAdapter.EXTRA_DATA
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.faiq.kisahnabiapp.R
import com.faiq.kisahnabiapp.data.NabiRespon
import com.faiq.kisahnabiapp.databinding.ActivityDetailBinding
import com.faiq.kisahnabiapp.ui.MainActivity



class DetailActivity : AppCompatActivity() {

    private var _binding : ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<NabiRespon>(EXTRA_DATA)
        binding.apply {
            Glide.with(this@DetailActivity).load(data?.imageUrl).into(detailImage)
            detailNama.text = data?.name
            detailTempat.text = data?.tmp
            detailUsia.text = "Usia :" + data?.usia
            detailDesk.text = data?.description
        }
    }

    companion object {
        const val EXTRA_DATA = "extra data"
    }
}