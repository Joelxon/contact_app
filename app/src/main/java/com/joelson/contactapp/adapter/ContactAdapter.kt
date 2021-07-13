package com.joelson.contactapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joelson.contactapp.databinding.ContactItemBinding
import com.joelson.contactapp.model.ContactModel

class ContactAdapter(val contactItems: List<ContactModel>,
                     val clickerFnx: (ContactModel) -> Unit):
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(val binding: ContactItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(contactItem: ContactModel){
            binding.ciName.text = contactItem.name
            binding.ciNumber.text = contactItem.phoneNumber
            //binding.shapeableImageView.setImageResource(contactItem.image)
            binding.root.setOnClickListener {
                clickerFnx(contactItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding: ContactItemBinding =
            ContactItemBinding.inflate(LayoutInflater.from(parent.context))

        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contactItems.get(position))

    }

    override fun getItemCount() = contactItems.size
}