package com.joelson.contactapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joelson.contactapp.R
import com.joelson.contactapp.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textView2.text = intent.getStringExtra("name")
        binding.textView3.text = intent.getStringExtra("number")
        //binding.imageView.setImageResource(intent.)

    }
}