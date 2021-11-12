package com.zinoview.tzmovieapp.presentation.nav

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.zinoview.tzmovieapp.R
import com.zinoview.tzmovieapp.presentation.core.BaseFragment

interface Navigator {

    fun <T : Activity> navigateTo(clazz: Class<T>)

    fun navigateTo(fragment: BaseFragment)

    class Base(
        private val activity: AppCompatActivity
    ) : Navigator {


        override fun <T : Activity> navigateTo(clazz: Class<T>) {
            val activityIntent = Intent(activity,clazz)
            activity.startActivity(activityIntent)
        }

        override fun navigateTo(fragment: BaseFragment) {
            activity
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .commit()
        }
    }
}