package com.ckc.kotlin_room.view.View.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ckc.kotlin_room.databinding.ItemBinding
import com.ckc.kotlin_room.view.View.view.MainActivity2
import com.ckc.kotlin_room.view.View.model.User

class adapter(val list: List<User>) :RecyclerView.Adapter<adapter.adapterHolder>() {
    class adapterHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterHolder {
        val recyler : ItemBinding = ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return adapterHolder(recyler)
    }

    override fun onBindViewHolder(holder: adapterHolder, position: Int) {
            holder.binding.textView.text = list[position].name

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MainActivity2::class.java)
            intent.putExtra("key",list[position])
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}