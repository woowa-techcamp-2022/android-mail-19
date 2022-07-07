package com.example.camp_mail.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.camp_mail.adapter.MailListRecyclerViewAdapter
import com.example.camp_mail.databinding.FragmentMailBinding
import com.example.camp_mail.model.MailViewModel

class MailFragment : Fragment() {
    private lateinit var binding: FragmentMailBinding
    private lateinit var adapter: MailListRecyclerViewAdapter
    private val mailViewModel: MailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        initMailListRecyclerView()
        setObservingMailList()
    }

    private fun setObservingMailList() {
        mailViewModel.mailList.observe(viewLifecycleOwner) { mailList ->
            Log.d("qwf", "qwfqwf")
            if (mailList.isNotEmpty()) {
                adapter.setData(mailList)
            }
        }
    }

    private fun initMailListRecyclerView() {
        adapter = MailListRecyclerViewAdapter()
        binding.rcvMailList.layoutManager = LinearLayoutManager(activity)
        binding.rcvMailList.adapter = adapter
    }
}