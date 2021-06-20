package com.app.videogamesapp.ui.FavouriteFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.videogamesapp.R
import com.app.videogamesapp.databinding.FragmentFavouriteBinding


class FavouriteFragment : Fragment() {
    private lateinit var binding : FragmentFavouriteBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavouriteBinding.inflate(inflater,container,false)
        return binding.root
    }


}