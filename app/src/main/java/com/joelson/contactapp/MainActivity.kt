package com.joelson.contactapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.joelson.contactapp.activities.ProfileActivity
import com.joelson.contactapp.adapter.ContactAdapter
import com.joelson.contactapp.database.ContactDatabase
import com.joelson.contactapp.databinding.ActivityMainBinding
import com.joelson.contactapp.model.ContactModel
import com.joelson.contactapp.model.ContactViewModel

const val NAME_KEY = "NAME_KEY"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var myContactAdapter: ContactAdapter
    private lateinit var myContactList: MutableList<ContactModel>
    private lateinit var viewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ContactViewModel::class.java]

        myContactList = mutableListOf()

        myContactAdapter = ContactAdapter(myContactList){
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("name", it.name)
            intent.putExtra("number",it.phoneNumber)
            startActivity(intent)
        }
        binding.recyclerView.adapter = myContactAdapter

        val db = Room.databaseBuilder(
            applicationContext,
            ContactDatabase::class.java, "contact-database"
        ).allowMainThreadQueries().build()


        viewModel.getAllContactItems(db).observe(this, {
            myContactAdapter = ContactAdapter(it){
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("name", it.name)
                intent.putExtra("number",it.phoneNumber)

                startActivity(intent)
            }
            binding.recyclerView.adapter = myContactAdapter
            myContactAdapter.notifyDataSetChanged()
        })



        binding.caButton.setOnClickListener {
            val name: String = binding.caName.text.toString()
            val phoneNumber: String = binding.caNumber.text.toString()
            //val email: String = binding.caEmail.text.toString()



            val contactItem = ContactModel(name, phoneNumber)
            viewModel.addContactItem(contactItem, db)

            myContactAdapter.notifyDataSetChanged()
        }

    }
}