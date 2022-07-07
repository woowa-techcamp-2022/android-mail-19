package com.example.camp_mail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.camp_mail.R
import com.example.camp_mail.data.Mail
import com.example.camp_mail.databinding.ItemMailListBinding

class MailListRecyclerViewAdapter() : RecyclerView.Adapter<MailListRecyclerViewAdapter.ViewHolder>() {
    private var dataSet = mutableListOf<Mail>()
    class ViewHolder(private val binding : ItemMailListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Mail){
            with(binding){
                mail = item
                if(item.sender[0] in 'a'..'z' || item.sender[0] in 'A'..'Z') {
                    setTextProfileIcon(item.sender[0].toString(), item.color!!)
                }
                else{
                    setDefaultProfileIcon()
                }
                executePendingBindings() // 강제 결합시키기
            }
        }

        private fun setTextProfileIcon(char : String, color : Int) {
            binding.tvMailIcon.setBackgroundResource(color)
            binding.tvMailIcon.visibility = View.VISIBLE
            binding.tvMailIcon.text = char
        }


        private fun setDefaultProfileIcon() {
            binding.tvMailIcon.visibility = View.GONE
            binding.ivMailIcon.visibility = View.VISIBLE
            binding.tvMailIcon.setBackgroundResource(R.drawable.ic_mail_default_profile)
        }

    }
    fun setData(data : MutableList<Mail>){
        dataSet = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMailListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}