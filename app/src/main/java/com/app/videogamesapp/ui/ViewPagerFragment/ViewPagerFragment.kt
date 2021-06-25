package com.app.videogamesapp.ui.ViewPagerFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.videogamesapp.R
import com.app.videogamesapp.databinding.FragmentViewPagerBinding
import com.app.videogamesapp.ui.Detail.DetailActivity
import com.app.videogamesapp.utils.clickListener
import com.bumptech.glide.Glide


class ViewPagerFragment : Fragment() {
    private lateinit var binding : FragmentViewPagerBinding
    var imageUrl :String?=""
    var gameId :String?=""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            it.getString("imageUrl")?.let {
                imageUrl = it
            }

            it.getString("id")?.let {
                gameId = it
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_view_pager, container, false)
        binding = FragmentViewPagerBinding.bind(view)

        imageUrl?.let {
            Glide.with(requireContext()).load(it).into(binding.gameImageVP)
        }

        buttonsListener()
        return view
    }

    private fun buttonsListener() {
        binding.gameImageVP.clickListener {
            val intent = Intent(requireContext(),DetailActivity::class.java)
            intent.putExtra("id",gameId)
            startActivity(intent)
        }
    }
}