package com.zinoview.tzmovieapp.presentation.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.zinoview.tzmovieapp.R
import com.zinoview.tzmovieapp.presentation.nav.Navigator

class MovieSplashScreen : AppCompatActivity() {

    private companion object {
        private const val DELAY = 2000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_splash_screen)

        val navigator = Navigator.Base(this)

        Handler().postDelayed({
               navigator.navigateTo(MainActivity::class.java)
                finish()
        },DELAY.toLong())

    }

}