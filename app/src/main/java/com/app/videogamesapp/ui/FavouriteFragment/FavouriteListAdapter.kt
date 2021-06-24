package com.app.videogamesapp.ui.FavouriteFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.app.videogamesapp.databinding.RecyclerItemGamesBinding
import com.app.videogamesapp.model.GameDetailBase
import com.app.videogamesapp.ui.AdapterClickListener

class FavouriteListAdapter(var itemClickListener: AdapterClickListener) : RecyclerView.Adapter<FavouriteListAdapter.MyHolder>() {
    private lateinit var binding : RecyclerItemGamesBinding
    var originalList :  ArrayList<GameDetailBase> = arrayListOf()
    var filterList :  ArrayList<GameDetailBase> = arrayListOf()


    fun setList(newList : ArrayList<GameDetailBase>){
        originalList = newList
        filterList = newList
        notifyDataSetChanged()
    }

    inner class MyHolder(var binding: RecyclerItemGamesBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data:GameDetailBase) = with(itemView){
            binding.gameImage.load(data.background_image)
            binding.ratingTxt.text = data.rating.toString()
            binding.releasedTxt.text = data.released.toString()
            binding.nameTxt.text = data.name

            itemView.setOnClickListener {
                itemClickListener.selectFavouriteData(data)
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