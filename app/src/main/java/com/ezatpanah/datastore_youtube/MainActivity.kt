package com.ezatpanah.datastore_youtube

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.*
import com.ezatpanah.datastore_youtube.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dataStoreManager: DataStoreManager
    private lateinit var viewModel: MainViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this@MainActivity)[MainViewModel::class.java]
        dataStoreManager = DataStoreManager(this)

        checkThemeMode()

        binding.apply {
            modeSwitch.setOnCheckedChangeListener { _, isChecked ->
                lifecycleScope.launch {
                    when (isChecked) {
                        true -> viewModel.setTheme(true)
                        false -> viewModel.setTheme(false)
                    }
                }
            }
        }
    }

    private fun checkThemeMode() {
        binding.apply {
            viewModel.getTheme.observe(this@MainActivity) { isDarkMode ->
                when (isDarkMode) {
                    true -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        modeSwitch.isChecked = true
                        modeSwitch.text="Dark Mode"
                        imgStatus.setAnimation(R.raw.night)
                    }
                    false -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        modeSwitch.isChecked = false
                        modeSwitch.text="Light Mode"
                        imgStatus.setAnimation(R.raw.day)
                    }
                }
            }
        }
    }
}
