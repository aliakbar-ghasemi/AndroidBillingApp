package com.aghasemi.billing.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.aghasemi.billing.R
import com.aghasemi.billing.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        val drawer = binding.drawerLayout
        val navDrawer: NavigationView = binding.navDrawer
        val bottomNavigationView: BottomNavigationView =
            binding.appBarMain.contentMain.bottomNavView

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val mainPageDestinationList = listOf(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
        )

        appBarConfiguration = AppBarConfiguration.Builder(
            *mainPageDestinationList.toIntArray()
        ).setOpenableLayout(drawer).build()


        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)
        navDrawer.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            val id = destination.id
            val isMainPageDestination = mainPageDestinationList.indexOf(id) < 0
            bottomNavigationView.visibility = if (isMainPageDestination) View.GONE else View.VISIBLE
        }

        //update locale
        val configuration = resources.configuration
        configuration.setLayoutDirection(Locale("fa"))
        resources.updateConfiguration(configuration, resources.displayMetrics)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(this, R.id.nav_host_fragment_content_main)
        return (navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp())
    }
}