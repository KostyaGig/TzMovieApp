package com.zinoview.tzmovieapp.presentation.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.zinoview.tzmovieapp.R
import com.zinoview.tzmovieapp.presentation.fragment.MoviesFragment
import com.zinoview.tzmovieapp.presentation.nav.Navigator


//todo remove later
fun Any.log(message: String) {
    Log.d("zinoviewk",message)
}

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigator = Navigator.Base(this)
        navigator.navigateTo(MoviesFragment())
    }
}