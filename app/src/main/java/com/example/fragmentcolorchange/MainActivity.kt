package com.example.fragmentcolorchange

import android.content.res.Configuration
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, ColorListFragment())
            .addToBackStack(null)
            .commit()

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            supportActionBar?.hide() // спрятать верхний бар
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.details_container, ColorDetailsFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}
