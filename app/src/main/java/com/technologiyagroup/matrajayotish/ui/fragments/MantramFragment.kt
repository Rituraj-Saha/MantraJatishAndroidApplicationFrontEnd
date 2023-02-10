package com.technologiyagroup.matrajayotish.ui.fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.technologiyagroup.bookmypujo.utils.GenFuns
import com.technologiyagroup.matrajayotish.R
import com.technologiyagroup.matrajayotish.databinding.FragmentMantramBinding
import com.technologiyagroup.matrajayotish.model.user.NetworkResult
import com.technologiyagroup.matrajayotish.util.Logger
import com.technologiyagroup.matrajayotish.viewModel.jantram.JantramViewModel
import com.technologiyagroup.matrajayotish.viewModel.mantra.MantraViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_mantram.*
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MantramFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class MantramFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentMantramBinding
    private var mediaPlayer = MediaPlayer()
    private var handler:Handler = Handler()

    private val mantraViewModel: MantraViewModel by viewModels()

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
        binding = FragmentMantramBinding.inflate(layoutInflater)
        val view = binding.root
        binding.playerSeekbar.max = 100

        binding.imgPlayPause.setOnClickListener {
            if(mediaPlayer.isPlaying){
                handler.removeCallbacks(updater)
                mediaPlayer.pause()
                binding.imgPlayPause.setImageResource(R.drawable.baseline_play_arrow_24)
            }else{
                mediaPlayer.start();
                binding.imgPlayPause.setImageResource(R.drawable.baseline_pause_24)
                updateSeekBar()
            }
        }
        mantraViewModel.mantraResponse.observe(this){
            when(it) {
                is NetworkResult.Loading -> {
                    binding.progressbar.isVisible = it.isLoading
                    Logger.log("userNetwork","in loading..")
                }

                is NetworkResult.Failure -> {
//                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
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
                        prepareMediaPlayer(it.data.responseBody)

                    }
                    else{
                        Toast.makeText(this.requireContext(),"Error occured", Toast.LENGTH_LONG)
                        Logger.log("userNetwork","Error occurerd")
                    }
                }
            }
        }


        binding.playerSeekbar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {


            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                var playPosition = (mediaPlayer.duration/100)*p0!!.progress
                mediaPlayer.seekTo(playPosition)
                binding.txtCurrentTime.text = milliSecondToTime(mediaPlayer.currentPosition)
            }

        })
        mediaPlayer.setOnBufferingUpdateListener { mediaPlayer, i ->
            binding.playerSeekbar.secondaryProgress = i
        }

        return view
    }
    fun prepareMediaPlayer(src:String){
        try {
            mediaPlayer.setDataSource(src)
            mediaPlayer.prepare()
            binding.txtTotalDuration.text = milliSecondToTime(mediaPlayer.duration)
        }
        catch (exception:Exception)
        {
            Logger.log("mediaPlayerLog: ",exception.message+"")
        }
    }

    private var updater = Runnable {
         kotlin.run {
            updateSeekBar()
            var currentDuration = mediaPlayer.currentPosition
            binding.txtCurrentTime.text = milliSecondToTime(currentDuration)
        }
    }

    fun updateSeekBar(){
        if(mediaPlayer.isPlaying)
        {
            Logger.log("seekBarUpdate:",""+mediaPlayer.currentPosition+":"+mediaPlayer.duration)
            binding.playerSeekbar.setProgress(((mediaPlayer.currentPosition.toDouble()/mediaPlayer.duration.toDouble())*100).toInt())
            handler.postDelayed(updater,100)
        }
    }

    fun milliSecondToTime(miliSeconds:Int):String{
        var timerString = ""
        var secondsString:String

        var hours = (miliSeconds / (1000*60*60)) as Int
        var minutes = (miliSeconds % (1000*60*60)/ (1000*60)) as Int
        var seconds = ((miliSeconds % (1000*60*60)) % (1000*60)/1000) as Int

        if(hours>0){
            timerString = hours.toString() + ":"
        }
        if(seconds<10){
            secondsString = "0"+seconds
        }
        else{
            secondsString = ""+seconds
        }
        timerString = timerString+minutes+":"+secondsString
        return timerString
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MantramFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MantramFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            mantraViewModel.getMantra(GenFuns.getStarIdFromSp(this@MantramFragment.requireContext()));
        }

    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.reset()
    }
}