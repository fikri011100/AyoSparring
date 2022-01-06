package bncc.net.ayosparring

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import bncc.net.ayosparring.ui.home.fragments.HistoryFragment
import bncc.net.ayosparring.ui.home.fragments.HomeFragment
import bncc.net.ayosparring.ui.home.fragments.ProfileFragment
import bncc.net.ayosparring.ui.home.fragments.RoomFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView.setupWithNavController(nav_host_fragment.findNavController())
    }
}