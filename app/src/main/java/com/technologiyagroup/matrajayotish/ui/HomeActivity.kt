package com.technologiyagroup.matrajayotish.ui

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.technologiyagroup.bookmypujo.utils.GenFuns
import com.technologiyagroup.matrajayotish.MainActivity
import com.technologiyagroup.matrajayotish.R
import com.technologiyagroup.matrajayotish.databinding.ActivityHomeBinding
import com.technologiyagroup.matrajayotish.ui.fragments.*
import com.technologiyagroup.matrajayotish.util.Constants
import com.technologiyagroup.matrajayotish.util.Logger
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    var selected = "home"
    lateinit var mantramFragment: MantramFragment
    lateinit var jantramFragment: JantramFragment
    lateinit var homeFragment: HomeFragment
    lateinit var pujaFragment: PujaFragment
    lateinit var tipsFragment: TipsFragment
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        if(intent.getStringExtra("CURRENT_LANG")!=null){
            if(intent.getStringExtra("CURRENT_LANG").equals("BN"))
            {
                binding.swLang.isChecked = false;
            }
            else{
                binding.swLang.isChecked = true;
            }
        }
        else
        {
            binding.swLang.isChecked = true;
        }



        binding.swLang.setOnClickListener {
            if( binding.swLang.isChecked)
            {
                val message = "EN"
                var myLocale = Locale(message)
                val res: Resources = resources
                val dm: DisplayMetrics = res.getDisplayMetrics()
                val conf: Configuration = res.getConfiguration()
                conf.locale = myLocale
                res.updateConfiguration(conf, dm)
                val refresh = Intent(this, HomeActivity::class.java)
                Logger.log("lang",message)
                refresh.putExtra("CURRENT_LANG",message)
                startActivity(refresh);
                finish()
            }
            else
            {
                val message = "BN"
                var myLocale = Locale(message)
                val res: Resources = resources
                val dm: DisplayMetrics = res.getDisplayMetrics()
                val conf: Configuration = res.getConfiguration()
                conf.locale = myLocale
                res.updateConfiguration(conf, dm)
                val refresh = Intent(this, HomeActivity::class.java)
                Logger.log("lang",message)
                refresh.putExtra("CURRENT_LANG",message)
                startActivity(refresh);
                finish()
            }
        }



        mantramFragment = MantramFragment();
        jantramFragment = JantramFragment()
        homeFragment = HomeFragment()
        pujaFragment = PujaFragment()
        tipsFragment = TipsFragment()

        GenFuns.replaceFragment(homeFragment,this,binding.frameHome)

        binding.txtName.text = "Hi, "+GenFuns.getNameFromSp(this)
        binding.jantramTxt.setOnClickListener {
            selected = "jantram";
            selector()

        }
        binding.mantramTxt.setOnClickListener {
            selected = "mantram"
            selector()
        }
        binding.pujaTxt.setOnClickListener {
            selected = "puja"
            selector()
        }
        binding.tipsTxt.setOnClickListener{
            selected = "tips"
            selector()
        }
        binding.home.setOnClickListener {
            selected = "home"
            selector()
        }
        binding.linLogout.setOnClickListener {
            GenFuns.logout(this,true)
        }
    }
    fun selector(){
        when(selected)
        {
            "jantram" ->{
                binding.janytramLin.setBackgroundResource(R.drawable.dash_underliner_home)
                binding.mantramLin.setBackgroundResource(0)
                binding.pujaLin.setBackgroundResource(0)
                binding.tipsLin.setBackgroundResource(0)
                GenFuns.replaceFragment(jantramFragment,this,binding.frameHome)
            }
            "mantram" ->{
                binding.janytramLin.setBackgroundResource(0)
                binding.mantramLin.setBackgroundResource(R.drawable.dash_underliner_home)
                binding.pujaLin.setBackgroundResource(0)
                binding.tipsLin.setBackgroundResource(0)
                GenFuns.replaceFragment(mantramFragment,this,binding.frameHome)
            }
            "puja" ->{
                binding.janytramLin.setBackgroundResource(0)
                binding.mantramLin.setBackgroundResource(0)
                binding.pujaLin.setBackgroundResource(R.drawable.dash_underliner_home)
                binding.tipsLin.setBackgroundResource(0)
                GenFuns.replaceFragment(pujaFragment,this,binding.frameHome)
            }
            "tips" ->{
                binding.janytramLin.setBackgroundResource(0)
                binding.mantramLin.setBackgroundResource(0)
                binding.pujaLin.setBackgroundResource(0)
                binding.tipsLin.setBackgroundResource(R.drawable.dash_underliner_home)
                GenFuns.replaceFragment(tipsFragment,this,binding.frameHome)
            }
            "home" ->{
                binding.janytramLin.setBackgroundResource(0)
                binding.mantramLin.setBackgroundResource(0)
                binding.pujaLin.setBackgroundResource(0)
                binding.tipsLin.setBackgroundResource(0)
                GenFuns.replaceFragment(homeFragment,this,binding.frameHome)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val builder: AlertDialog.Builder = androidx.appcompat.app.AlertDialog.Builder(this)

        builder.setTitle("Confirm")
        builder.setMessage("Are you sure?")

        builder.setPositiveButton(
            "YES",
            DialogInterface.OnClickListener { dialog, which -> // Do nothing but close the dialog

                selected = "home"
                selector()
                finish()
                dialog.dismiss()
            })

        builder.setNegativeButton(
            "NO",
            DialogInterface.OnClickListener { dialog, which -> // Do nothing
                GenFuns.replaceFragment(homeFragment,this,binding.frameHome)
                selected = "home"
                selector()
                dialog.dismiss()
            })

        val alert: AlertDialog = builder.create()
        alert.show()

    }
}