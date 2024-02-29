package com.example.hassanmuawia_task

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.hassanmuawia_task.databinding.ActivityMainBinding
import com.example.hassanmuawia_task.room_mvvm.MyData
import com.example.hassanmuawia_task.room_mvvm.MyDatabase
import com.example.hassanmuawia_task.room_mvvm.MyViewModel
import com.example.hassanmuawia_task.room_mvvm.Repository
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var pagerAdapter: MyPagerAdapter
    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewPager adapter
        pagerAdapter = MyPagerAdapter(this)
        binding.viewPager.adapter = pagerAdapter

        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        binding.insertButton.setOnClickListener {
            val text = binding.editText.text.toString()
            if (text.isNotBlank()) {
                viewModel.addText(MyData(0,text))
                Log.d("MainActivity", "Inserted: $text")
                binding.editText.text.clear()
            } else {
                Log.d("MainActivity", "Text is empty!")
            }
        }

        // Add tab titles to TabLayout
        val tabTitles = arrayOf("Fragment 1", "Fragment 2")
        tabTitles.forEach { title ->
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(title))
        }

        // Set tab selection listener to switch ViewPager pages
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }
}
