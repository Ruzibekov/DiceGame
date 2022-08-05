package com.ruzibekov.gameofdice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ruzibekov.gameofdice.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMenuPlay.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.btnMenuExit.setOnClickListener { finish() }
    }
}