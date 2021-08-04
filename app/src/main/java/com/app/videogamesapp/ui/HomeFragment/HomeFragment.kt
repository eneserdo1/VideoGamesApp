package com.app.videogamesapp.ui.HomeFragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.app.videogamesapp.data.remote.RemoteDataSource
import com.app.videogamesapp.databinding.FragmentHomeBinding
import com.app.videogamesapp.model.GameDetailBase
import com.app.videogamesapp.model.Results
import com.app.videogamesapp.ui.AdapterClickListener
import com.app.videogamesapp.ui.Detail.DetailActivity
import com.app.videogamesapp.ui.ViewPagerFragment.ViewPagerFragment
import com.app.videogamesapp.utils.FragmentViewPagerAdapter
import com.app.videogamesapp.utils.goneAlpha
import com.app.videogamesapp.utils.visibleAlpha


class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var viewModel: HomeFragmentViewModel
    private lateinit var adapter : GameListAdapter
    private lateinit var dataSource: RemoteDataSource
    private var viewPagerList : ArrayList<Results> = arrayListOf()
    private var searchState : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        dataSource = RemoteDataSource()
        initRecyclerview()
        observeData()
        searchviewListener()
        return binding.root
    }

    private fun searchviewListener() {
        binding.searchview.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                p0?.let {
                    if (p0.length > 2) {
                        // Girilen string değeri viewmodelda bulunan fonksiyona gönderiliyor
                            binding.viewPagerLayout.goneAlpha()
                        viewModel.filterList(p0.toString(), adapter)
                        searchState = true
                    }else{
                        if (searchState){
                            viewModel.filterList(p0.toString(), adapter)
                            binding.viewPagerLayout.visibleAlpha()
                            searchState = false
                        }
                    }
                }
                return false
            }
        })
    }

    private fun initRecyclerview() {
        adapter = GameListAdapter(object : AdapterClickListener {
            override fun selectData(data: Results) {
                val intent = Intent(requireContext(),DetailActivity::class.java)
                intent.putExtra("id",data.id.toString())
                startActivity(intent)
            }
            override fun selectFavouriteData(data: GameDetailBase) {
            }
        })
        binding.gameRv.layoutManager = LinearLayoutManager(requireContext())
        binding.gameRv.adapter = adapter
    }

    private fun observeData() {

        viewModel.mutableListData.observe(viewLifecycleOwner, Observer { data ->
            if (data != null) {
                val response = data.results as ArrayList<Results>
                var i = 0
                for (item in response){
                    if (i<3){
                    viewPagerList.add(item)
                    i++
                    }else{
                        break
                    }
                }
                adapter.setList(response)
                initPager()
            } else {
                println("Data Yok")
            }
        })

        /** Veri çekme işlemi sırasında progressbar gösterme durumunu takip ediyor */
        viewModel.progressbarValue.observe(viewLifecycleOwner, Observer { response->
            response?.let {
                if (it){
                    binding.mainLayout.goneAlpha()
                    binding.progressbar.visibleAlpha()
                }else{
                    binding.progressbar.goneAlpha()
                    binding.mainLayout.visibleAlpha()
                }
            }
        })

        /** searchview'da filtrelenen liste durumuna göre ekran durumunu takip ediyor */
        viewModel.isSearchedData.observe(viewLifecycleOwner, Observer {response->
            response?.let {
                if (it){
                    binding.mainLayout.visibleAlpha()
                    binding.noSearchedDataTxt.goneAlpha()
                }else{
                    binding.mainLayout.goneAlpha()
                    binding.noSearchedDataTxt.visibleAlpha()
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.getAllList(dataSource)
    }

    private fun initPager() {
        val fragments = arrayListOf<Fragment>()

        val fragment1 = ViewPagerFragment()
        val bundle1 = Bundle()
        bundle1.putString("imageUrl", viewPagerList[0].background_image)
        bundle1.putString("id", viewPagerList[0].id.toString())
        fragment1.arguments = bundle1
        fragments.add(fragment1)

        val fragment2 = ViewPagerFragment()
        val bundle2 = Bundle()
        bundle2.putString("imageUrl", viewPagerList[1].background_image)
        bundle2.putString("id", viewPagerList[1].id.toString())
        fragment2.arguments = bundle2
        fragments.add(fragment2)

        val fragment3 = ViewPagerFragment()
        val bundle3 = Bundle()
        bundle3.putString("imageUrl", viewPagerList[2].background_image)
        bundle3.putString("id", viewPagerList[2].id.toString())
        fragment3.arguments = bundle3
        fragments.add(fragment3)


        val fragmentAdapter = FragmentViewPagerAdapter(childFragmentManager, fragments)
        binding.viewPager.adapter = fragmentAdapter
        fragmentAdapter.notifyDataSetChanged()
        binding.viewPager.currentItem = 0

    }

}
