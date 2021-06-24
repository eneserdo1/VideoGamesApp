package com.app.videogamesapp.ui.ViewPagerFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.videogamesapp.R
import com.app.videogamesapp.databinding.FragmentViewPagerBinding
import com.bumptech.glide.Glide


class ViewPagerFragment : Fragment() {
    private lateinit var binding : FragmentViewPagerBinding
    var imageUrl :String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getString("imageUrl")?.let {
                imageUrl = it
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_view_pager, container, false)
        binding = FragmentViewPagerBinding.bind(view)

        println("İmageUrl -- $imageUrl")

        imageUrl?.let {
            Glide.with(requireContext()).load(it).into(binding.gameImageVP)
        }

        return view
    }


}