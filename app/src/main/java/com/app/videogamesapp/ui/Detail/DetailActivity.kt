package com.app.videogamesapp.ui.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.app.videogamesapp.R
import com.app.videogamesapp.databinding.ActivityDetailBinding
import com.app.videogamesapp.model.GameDetailBase
import com.app.videogamesapp.model.Results
import com.app.videogamesapp.utils.clickListener
import com.app.videogamesapp.utils.goneAlpha
import com.app.videogamesapp.utils.visibleAlpha

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    private lateinit var viewModel : DetailViewModel
    var id : String?=null
    var detailResult:GameDetailBase?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        id = intent.getStringExtra("id")

        observeData()
        buttonsListener()

    }

    private fun buttonsListener() {
        binding.likeButton.clickListener {
            binding.likeButton.playAnimation()
            detailResult?.let {
                viewModel.insert(it)
            }
        }
    }

    private fun observeData() {
        viewModel.mutableDetailData.observe(this, Observer { response->
            if (response != null){
                binding.gameImageview.load(response.background_image)
                binding.nameTxt.text = response.name
                binding.descriptionTxt.text = response.description_raw
                binding.releaseTxt.text=response.released
                binding.metacriticTxt.text = response.metacritic.toString()
                detailResult = response

            }else{
                Toast.makeText(this,getString(R.string.error_message),Toast.LENGTH_LONG).show()
            }
        })


        viewModel.progressbarState.observe(this, Observer {response->
            response?.let {
                if (it){
                    binding.dataLayout.goneAlpha()
                    binding.progressbar.visibleAlpha()
                }else{
                    binding.progressbar.goneAlpha()
                    binding.dataLayout.visibleAlpha()
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        id?.let {
            viewModel.fetchData(it)
        }
    }
}