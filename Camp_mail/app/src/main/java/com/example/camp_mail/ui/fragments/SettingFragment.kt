package com.example.camp_mail.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.camp_mail.databinding.FragmentSettingBinding
import com.example.camp_mail.model.UserInfoViewModel

class SettingFragment : Fragment() {
    private val userInfoViewModel : UserInfoViewModel by activityViewModels()
    private lateinit var binding : FragmentSettingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding =  FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.userInfo = userInfoViewModel
    }
}