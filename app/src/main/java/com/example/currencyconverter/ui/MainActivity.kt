package com.example.currencyconverter.ui

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import com.example.currencyconverter.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        initSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //create splashScreen above android12
    fun initSplashScreen() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.S) {
            initSplashScreen()
            //add callBack that`s called when the splashScreen in anim to the app content
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                //create custom anim
                val slidUp = ObjectAnimator.ofFloat(
                    splashScreenView, View.TRANSLATION_Y, 0f, -splashScreenView.height.toFloat()
                )
                slidUp.interpolator = AnticipateInterpolator()
                slidUp.duration = 3000L

                //remove splashScreen at the end
                slidUp.doOnEnd { splashScreenView.remove() }

                //run your splash
                slidUp.start()
            }
        } else {
            setTheme(R.style.Theme_CurrencyConverter)
        }
    }


}