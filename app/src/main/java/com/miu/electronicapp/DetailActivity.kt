package com.miu.electronicapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.miu.electronicapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // To avoid application crash check intent has data or not
        if (intent.hasExtra("image") && intent.hasExtra("title") && intent.hasExtra("subtitle") && intent.hasExtra("price")) {
            var ig = intent.getIntExtra("image", 0)
            var product = intent.getSerializableExtra("product") as Product

            binding.title.text = product.title.toString()
            binding.subtitle.text = product.subTitle.toString()
            binding.price.text = "$ "+ product.cost.toString()
            binding.image.setImageResource(ig)
        }

        binding.homeBtn.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}