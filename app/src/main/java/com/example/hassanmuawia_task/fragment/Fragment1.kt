package com.example.hassanmuawia_task.fragment
import Fragemnt1Adapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hassanmuawia_task.MainActivity
import com.example.hassanmuawia_task.databinding.Fragment1Binding
import com.example.hassanmuawia_task.room_mvvm.MyData
import com.example.hassanmuawia_task.room_mvvm.MyViewModel

class Fragment1 : Fragment() {

    private var _binding: Fragment1Binding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: Fragemnt1Adapter
    private val viewModel: MyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Fragment1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView and adapter
        adapter = Fragemnt1Adapter(mutableListOf()) { data ->
            // Handle item click here
            (requireActivity() as MainActivity).apply {
                binding.editText.setText(data.text)
                binding.insertButton.text = "Update"

                binding.insertButton.setOnClickListener {
                    var newText = binding.editText.text.toString()
                    if (newText.isNotBlank()) {
                        viewModel.updateText(MyData(data.id, newText))
                        binding.editText.text.clear().toString()
                        binding.insertButton.text = "Insert"
                    }
                }
            }
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observe text entries from ViewModel
        viewModel.allTextData.observe(viewLifecycleOwner, Observer { entries ->
            entries?.let {
                adapter.setData(entries)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
