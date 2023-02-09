package com.technologiyagroup.bookmypujo.utils

import android.R
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.technologiyagroup.matrajayotish.ui.LoginActivity
import com.technologiyagroup.matrajayotish.ui.fragments.HomeFragment
import com.technologiyagroup.matrajayotish.util.Constants


object GenFuns {

    fun replaceFragment(fragment: Fragment,activity: Activity,frame: View) {
        val fragmentManager: FragmentManager = (activity as AppCompatActivity).supportFragmentManager

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(frame.id, fragment)
        fragmentTransaction.addToBackStack(fragment.toString())
        if (fragment is HomeFragment) {
        } else {
//            fragmentTransaction.addToBackStack(fragment.toString())
        }
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        fragmentTransaction.commit()
    }

    fun getNameFromSp(context: Context):String{
        val sharedPreferences = context.getSharedPreferences(Constants.PREF_NAME,Context.MODE_PRIVATE)
        return sharedPreferences.getString(Constants.UNAME,"")!!
    }
    fun getStarIdFromSp(context: Context):String{
        val sharedPreferences = context.getSharedPreferences(Constants.PREF_NAME,Context.MODE_PRIVATE)
        return sharedPreferences.getString(Constants.STAR_ID,"")!!
    }

    fun logout(context: Activity, showToastMessage: Boolean) {
        if (showToastMessage)
            Toast.makeText(context,"Logged out successfully",
                Toast.LENGTH_LONG
            ).show()
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(
            Constants.PREF_NAME, Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.commit()

        //RTR Changed to RememberMe in place of login
        val intent = Intent(context, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
        context.finish()
    }

}