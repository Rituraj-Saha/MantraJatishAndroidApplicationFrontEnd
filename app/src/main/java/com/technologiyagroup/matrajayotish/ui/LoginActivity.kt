package com.technologiyagroup.matrajayotish.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.technologiyagroup.bookmypujo.utils.GenFuns
import com.technologiyagroup.matrajayotish.databinding.ActivityLoginBinding
import com.technologiyagroup.matrajayotish.model.user.NetworkResult
import com.technologiyagroup.matrajayotish.util.Constants
import com.technologiyagroup.matrajayotish.util.Logger
import com.technologiyagroup.matrajayotish.viewModel.user.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    private val userViewModel: UserViewModel by viewModels()
   lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        sharedPreferences  = this.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

        binding.btnSignin.setOnClickListener {
            lifecycleScope.launch {
                var uPhone = binding.etPhone.text.toString()
                userViewModel.getUser(uPhone);
            }
        }

        userViewModel.userResponse.observe(this) {
            when(it) {
                is NetworkResult.Loading -> {
                    //binding.progressbar.isVisible = it.isLoading
                    Logger.log("userNetwork","in loading..")
                }

                is NetworkResult.Failure -> {
//                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
//                    binding.progressbar.isVisible = false

                    Logger.log("userNetwork","failed"+it.errorMessage)
                    Toast.makeText(this,"Error occured",Toast.LENGTH_LONG).show()
                }

                is  NetworkResult.Success -> {
//                    movieAdapter.updateMovies(it.data)
//                    binding.progressbar.isVisible = false
                    Logger.log("userNetwork",it.data.responseBody.user.toString())
                    if(it.data.responseCode.equals("200"))
                    {
                        val editor:SharedPreferences.Editor =  sharedPreferences.edit()
                        editor.putString(Constants.UID,it.data.responseBody.user.id.toString())
                        editor.putString(Constants.UNAME,it.data.responseBody.user.name.toString())
                        editor.putString(Constants.UPHONE,it.data.responseBody.user.phone.toString())
                        editor.putString(Constants.DATE_OF_BIRTH,it.data.responseBody.user.dateOfBirth.toString())
                        editor.putString(Constants.TIME_OF_BIRTH,it.data.responseBody.user.timeOfBirth.toString())
                        editor.putString(Constants.PLACE_OF_BIRTH,it.data.responseBody.user.placeOfBirth.toString())
                        editor.putString(Constants.REGESTRATION_DATE,it.data.responseBody.user.registrationDate.toString())
                        editor.putString(Constants.PAYEMENT_STATUS,it.data.responseBody.user.paymentStatus.toString())
                        editor.putString(Constants.PAYMENT_AMT,it.data.responseBody.user.paymentAmt.toString())
                        editor.putString(Constants.PAYMENT_DATE,it.data.responseBody.user.paymentDate.toString())
                        editor.putString(Constants.STAR_ID,it.data.responseBody.user.starId.toString())
                        editor.putString(Constants.SESSION_STATUS,it.data.responseBody.user.sessionStatus.toString())
                        editor.apply()
                        editor.commit()
                        val intent = Intent(this,HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else{
                        Toast.makeText(this,"Error occured",Toast.LENGTH_LONG)
                        Logger.log("userNetwork","Error occurerd")
                    }
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        if(!GenFuns.getNameFromSp(this).equals(""))
        {
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}