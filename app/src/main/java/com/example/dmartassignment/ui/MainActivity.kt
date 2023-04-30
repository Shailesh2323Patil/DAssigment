package com.example.dmartassignment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.dmartassignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var mTag = MainActivity::class.java.simpleName

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnImage.setOnClickListener(this)
        binding.btnList.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            binding.btnImage.id -> {
                openImageActivity()
            }

            binding.btnList.id -> {
                openListActivity()
            }
        }
    }


    private fun openImageActivity() {
        var intent = Intent(this, ImageActivity::class.java)
        startActivity(intent)
    }

    private fun openListActivity() {
        var intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }
}