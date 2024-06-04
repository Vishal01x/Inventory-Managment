package com.exa.android.inventorymanagement

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.exa.android.inventorymanagement.Fragments.AlertFragment
import com.exa.android.inventorymanagement.Fragments.HistoryFragment
import com.exa.android.inventorymanagement.Fragments.HomeFragment
import com.exa.android.inventorymanagement.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Load the default fragment
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        binding.btmNaviView.setOnItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.home -> HomeFragment()
                R.id.alert -> AlertFragment()
                R.id.history -> HistoryFragment()
                else -> HomeFragment()
            }

            loadFragment(fragment)
            true
        }

    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}
