package com.ambrella.criptoapp.screen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation

import androidx.navigation.fragment.findNavController
import com.ambrella.criptoapp.CoinViewModel
import com.ambrella.criptoapp.R
import com.ambrella.criptoapp.databinding.FragmentInfoBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_info.*

class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater)
        return binding.root
    }



    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fromSymbol=requireArguments().getString("title1")
        var viewModel: CoinViewModel = ViewModelProvider(this) [CoinViewModel::class.java]
        if (fromSymbol != null) {
            viewModel.getDetailInfo(fromSymbol).observe(this, Observer {
                tvPrice.text=getString(R.string.StrPrice)+it.price.toString()
                tvMin.text=getString(R.string.strMin)+it.lowhour.toString()
                tvMax.text=getString(R.string.strMax)+ it.highday.toString()
                tvorder.text=getString(R.string.strTransaction)+it.lastmarket
                tvupdate.text=getString(R.string.strUpdate)+it.getFormattedTime()
                tvposition.text= it.fromsymbol+" | "+it.tosymbol
                Picasso.get().load(it.getFullImageUrl()).into(imageView2)

            })
        }
        binding.btdown.setOnClickListener {

            transitionFrag(view,R.id.mainFragment)
        }
        }

    private fun transitionFrag(view: View,fragnId:Int) {
        val navController = Navigation.findNavController(view)
        findNavController().navigate(fragnId, null)
        navController.navigate(fragnId)
    }
}




