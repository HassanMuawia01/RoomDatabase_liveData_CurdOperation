package com.example.hassanmuawia_task.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hassanmuawia_task.adapter.Fragment2Adapter
import com.example.hassanmuawia_task.databinding.Fragment2Binding
import com.example.hassanmuawia_task.room_mvvm.MyData
import com.example.hassanmuawia_task.room_mvvm.MyViewModel

class Fragment2 : Fragment() {

    private lateinit var binding: Fragment2Binding
    private lateinit var viewModel: MyViewModel
    private lateinit var adapter: Fragment2Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        adapter = Fragment2Adapter() { data ->
            showDeleteConfirmationDialog(data)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.allTextData.observe(viewLifecycleOwner, Observer { dataList ->
            adapter.submitList(dataList)
        })
    }

    private fun showDeleteConfirmationDialog(data: MyData) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Confirmation")
            .setMessage("Are you sure you want to delete this item?")
            .setPositiveButton("Delete") { dialog, which ->
                deleteItem(data)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun deleteItem(data: MyData) {
        viewModel.deleteText(data.id)
        Toast.makeText(requireContext(), "Item deleted", Toast.LENGTH_SHORT).show()
    }
}

