package com.example.orderapp

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.orderapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.bottomNav, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    binding.bottomNav.visibility = View.VISIBLE
                    binding.fab.visibility = View.VISIBLE
                    binding.nav.visibility =View.VISIBLE
                }
                else -> {
                    binding.bottomNav.visibility = View.GONE
                    binding.fab.visibility = View.GONE
                    binding.nav.visibility =View.GONE

                }
            }
        }

        binding.fab.setOnClickListener {
            navController.navigate(R.id.homeToCart)

        }

        setContentView(binding.root)

    }
}
