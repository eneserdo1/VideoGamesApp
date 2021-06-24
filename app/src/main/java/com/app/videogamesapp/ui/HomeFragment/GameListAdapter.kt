package com.app.videogamesapp.ui.HomeFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.app.videogamesapp.databinding.RecyclerItemGamesBinding
import com.app.videogamesapp.model.Results
import com.app.videogamesapp.ui.AdapterClickListener

class GameListAdapter(var itemClickListener: AdapterClickListener) : RecyclerView.Adapter<GameListAdapter.MyHolder>() {
    private lateinit var binding : RecyclerItemGamesBinding
    var originalList :  ArrayList<Results> = arrayListOf()
    var filterList :  ArrayList<Results> = arrayListOf()


    fun setList(newList : ArrayList<Results>){
        originalList = newList
        filterList = newList
        notifyDataSetChanged()
    }

    inner class MyHolder(var binding: RecyclerItemGamesBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data:Results) = with(itemView){
            binding.gameImage.load(data.background_image)
            binding.ratingTxt.text = data.rating.toString()
            binding.releasedTxt.text = data.released.toString()
            binding.nameTxt.text = data.name

            itemView.setOnClickListener {
                itemClickListener.selectData(data)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        binding = RecyclerItemGamesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyHolder(binding)

    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(filterList[position])

    }

    override fun getItemCount(): Int {
        return filterList.size
    }
}