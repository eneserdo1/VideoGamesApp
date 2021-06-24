package com.app.videogamesapp.ui.HomeFragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.app.videogamesapp.databinding.FragmentHomeBinding
import com.app.videogamesapp.model.GameDetailBase
import com.app.videogamesapp.model.Results
import com.app.videogamesapp.ui.AdapterClickListener
import com.app.videogamesapp.ui.Detail.DetailActivity
import com.app.videogamesapp.ui.ViewPagerFragment.ViewPagerFragment
import com.app.videogamesapp.utils.FragmentViewPagerAdapter


class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var viewModel: HomeFragmentViewModel
    private lateinit var adapter : GameListAdapter
    private var viewPagerList : ArrayList<String> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)

        initRecyclerview()
        observeData()
        return binding.root
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
                println("Data--- $data")
                val response = data.results as ArrayList<Results>
                var i = 0
                for (item in response){
                    if (i<3){
                    viewPagerList.add(item.background_image.toString())
                    i++
                    }else{
                        break
                    }
                }
                adapter.setList(response)
                initPager()

            } else {
                println("data yok")
            }
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.getAllList()
    }

    private fun initPager() {
        val fragments = arrayListOf<Fragment>()

        val fragment1 = ViewPagerFragment()
        val bundle1 = Bundle()
        bundle1.putString("imageUrl", viewPagerList[0])
        fragment1.arguments = bundle1
        fragments.add(fragment1)

        val fragment2 = ViewPagerFragment()
        val bundle2 = Bundle()
        bundle2.putString("imageUrl", viewPagerList[1])
        fragment2.arguments = bundle2
        fragments.add(fragment2)

        val fragment3 = ViewPagerFragment()
        val bundle3 = Bundle()
        bundle3.putString("imageUrl", viewPagerList[2])
        fragment3.arguments = bundle3
        fragments.add(fragment3)

        println("ViewpagerList -- $viewPagerList")

        val fragmentAdapter = FragmentViewPagerAdapter(childFragmentManager, fragments)
        binding.viewPager.adapter = fragmentAdapter
        fragmentAdapter.notifyDataSetChanged()
        binding.viewPager.currentItem = 0
        var lastState = 0
        var lastPosition = 0
        binding.viewPager.addOnPageChangeListener(object :
                ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                if (lastState == 0 && state == 1) {
                    isSliderRun = false
                    lastState = state
                }
            }

            override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (position == 2) {
                    isSliderRun = false
                    lastPosition = position
                } else {
                    Handler().postDelayed({
                        if (lastState == 1 && lastPosition == 2 && position == 1) {
                            isSliderRun = true
                        }
                        lastPosition = position
                    }, 2000)
                }
            }
        })
        handlerSlider()
    }
    var isSliderRun = true
    fun handlerSlider() {
        if (isSliderRun) {
            if (binding.viewPager.currentItem == 3) {
                Handler().postDelayed({
                    binding.viewPager.currentItem = 0
                    handlerSlider()
                }, 2000)
            } else {
                Handler().postDelayed({
                    binding.viewPager.currentItem =
                            binding.viewPager.currentItem + 1
                    handlerSlider()
                }, 3000)
            }
        } else {
            Handler().postDelayed({
                handlerSlider()
            }, 1000)
        }
    }

}