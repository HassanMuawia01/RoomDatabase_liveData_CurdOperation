package com.example.hassanmuawia_task

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hassanmuawia_task.fragment.Fragment1
import com.example.hassanmuawia_task.fragment.Fragment2

class MyPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    val fragments: List<Fragment> = listOf(Fragment1(), Fragment2())

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}

