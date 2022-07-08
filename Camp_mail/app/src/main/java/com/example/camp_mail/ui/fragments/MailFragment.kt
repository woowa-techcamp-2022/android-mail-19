package com.example.camp_mail.ui.fragments

import android.os.Bundle
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
        setObservingMailList() // MailListViewModel 의 메일리스트를 관찰하여 리사이클러뷰 갱신
    }

    private fun setObservingMailList() {
        mailViewModel.mailList.observe(viewLifecycleOwner) { mailList ->
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