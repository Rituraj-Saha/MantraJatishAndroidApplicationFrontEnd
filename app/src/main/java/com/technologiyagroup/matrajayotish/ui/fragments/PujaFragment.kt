package com.technologiyagroup.matrajayotish.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.technologiyagroup.bookmypujo.utils.GenFuns
import com.technologiyagroup.matrajayotish.adaptor.RecyclerViewAdaptor
import com.technologiyagroup.matrajayotish.databinding.FragmentPujaBinding
import com.technologiyagroup.matrajayotish.model.user.NetworkResult
import com.technologiyagroup.matrajayotish.util.Logger
import com.technologiyagroup.matrajayotish.viewModel.puja.PujaViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PujaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class PujaFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentPujaBinding
    private val pujaViewModel: PujaViewModel by viewModels()
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
        binding = FragmentPujaBinding.inflate(layoutInflater)
        val view = binding.root
        binding.recyclPuja.layoutManager = LinearLayoutManager(this.requireContext())

        pujaViewModel.pujaResponse.observe(this){
            when(it) {
                is NetworkResult.Loading -> {
                    //binding.progressbar.isVisible = it.isLoading
                    Logger.log("userNetwork","in loading..")
                }

                is NetworkResult.Failure -> {
//                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
//                    binding.progressbar.isVisible = false

                    Logger.log("userNetwork","failed"+it.errorMessage)
                    Toast.makeText(this.requireContext(),"Error occured", Toast.LENGTH_LONG).show()
                }
                is  NetworkResult.Success -> {
//                    movieAdapter.updateMovies(it.data)
//                    binding.progressbar.isVisible = false
                    Logger.log("userNetwork",it.data.responseBody.toString())
                    if(it.data.responseCode.equals("200"))
                    {
                        val adapter = RecyclerViewAdaptor(GenFuns.commaSeparatedToList(it.data.responseBody))
                        binding.recyclPuja.adapter = adapter

                    }
                    else{
                        Toast.makeText(this.requireContext(),"Error occured", Toast.LENGTH_LONG)
                        Logger.log("userNetwork","Error occurerd")
                    }
                }
            }
        }

        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PujaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PujaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            pujaViewModel.getPuja(GenFuns.getStarIdFromSp(this@PujaFragment.requireContext()),resources.configuration.locale.language);
        }

    }
}