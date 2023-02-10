package com.technologiyagroup.matrajayotish.ui.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.technologiyagroup.bookmypujo.utils.GenFuns
import com.technologiyagroup.matrajayotish.R
import com.technologiyagroup.matrajayotish.databinding.FragmentJantramBinding
import com.technologiyagroup.matrajayotish.model.user.NetworkResult
import com.technologiyagroup.matrajayotish.ui.HomeActivity
import com.technologiyagroup.matrajayotish.util.Constants
import com.technologiyagroup.matrajayotish.util.Logger
import com.technologiyagroup.matrajayotish.viewModel.jantram.JantramViewModel
import com.technologiyagroup.matrajayotish.viewModel.user.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [JantramFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class JantramFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentJantramBinding
    private val jantramViewModel: JantramViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentJantramBinding.inflate(layoutInflater)
        val view = binding.root
//        return inflater.inflate(R.layout.fragment_jantram, container, false)
        jantramViewModel.jantramResponse.observe(this){
            when(it) {
                is NetworkResult.Loading -> {
                    binding.progressbar.isVisible = it.isLoading
                    Logger.log("userNetwork","in loading..")
                }

                is NetworkResult.Failure -> {
                    Toast.makeText(this.requireContext(), it.errorMessage, Toast.LENGTH_SHORT).show()
                    binding.progressbar.isVisible = false

                    Logger.log("userNetwork","failed"+it.errorMessage)
                    Toast.makeText(this.requireContext(),"Error occured", Toast.LENGTH_LONG).show()
                }
                is  NetworkResult.Success -> {
//                    movieAdapter.updateMovies(it.data)
                    binding.progressbar.isVisible = false
                    Logger.log("userNetwork",it.data.responseBody.toString())
                    if(it.data.responseCode.equals("200"))
                    {
                        Glide.with(this.requireContext()).load(it.data.responseBody).into(binding.imgJantram)

                    }
                    else{
                        Toast.makeText(this.requireContext(),"Error occured",Toast.LENGTH_LONG)
                        Logger.log("userNetwork","Error occurerd")
                    }
                }
            }
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            jantramViewModel.getJantram(GenFuns.getStarIdFromSp(this@JantramFragment.requireContext()));
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment JantramFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            JantramFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}