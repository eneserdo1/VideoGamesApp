package com.app.videogamesapp.ui.FavouriteFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.videogamesapp.databinding.FragmentFavouriteBinding
import com.app.videogamesapp.model.GameDetailBase
import com.app.videogamesapp.model.Results
import com.app.videogamesapp.ui.AdapterClickListener
import com.app.videogamesapp.ui.Detail.DetailActivity


class FavouriteFragment : Fragment() {
    private lateinit var binding : FragmentFavouriteBinding
    private lateinit var viewModel: FavouriteViewModel
    private lateinit var adapter: FavouriteListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavouriteBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(FavouriteViewModel::class.java)

        initRecyclerview()
        observeData()
        return binding.root
    }

    private fun initRecyclerview() {
        adapter = FavouriteListAdapter(object : AdapterClickListener {
            override fun selectData(data: Results) {
            }
            override fun selectFavouriteData(data: GameDetailBase) {
                val intent = Intent(requireContext(),DetailActivity::class.java)
                intent.putExtra("id",data.gameId.toString())
                startActivity(intent)
            }

        })
        binding.favouriteRv.layoutManager = LinearLayoutManager(requireContext())
        binding.favouriteRv.adapter = adapter
    }


    private fun observeData() {
        viewModel.favouriteGames.observe(viewLifecycleOwner, Observer { response ->
            response?.let {
                println("Gelen Favori -- ${it}")
                adapter.setList(response as ArrayList<GameDetailBase>)
            }
        })
    }


}