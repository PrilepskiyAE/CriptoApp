package com.ambrella.criptoapp.screen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.ambrella.criptoapp.CoinViewModel
import com.ambrella.criptoapp.R
import com.ambrella.criptoapp.adapters.CoinInfoAdapter
import com.ambrella.criptoapp.databinding.FragmentMainBinding
import com.ambrella.criptoapp.pojo.CoinPriceInfo
import kotlinx.android.synthetic.main.fragment_main.*
import androidx.lifecycle.Observer

class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: CoinViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // val navController = Navigation.findNavController(view)

        val adapter= CoinInfoAdapter()
        adapter.onCoinClicLisener=object : CoinInfoAdapter.OnCoinClicLisener
        {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                navigationFrag(coinPriceInfo.fromsymbol)

            }

        }
        rvCoinPriceList.adapter=adapter
        viewModel= ViewModelProvider(this) [CoinViewModel::class.java]
        viewModel.priceList.observe(this@MainFragment, Observer {
            adapter.coinInfoList= it
        })
    }

    private fun navigationFrag(message:String) {

       // findNavController().navigate(R.id.infoFragment, null)
        val bundle = Bundle()
        bundle.putString("title1", message)
        findNavController().navigate(R.id.action_mainFragment_to_infoFragment, bundle)
    }
}