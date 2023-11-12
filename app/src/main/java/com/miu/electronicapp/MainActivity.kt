package com.miu.electronicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.miu.electronicapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var productList: ArrayList<Product> = ArrayList()
    var icons = intArrayOf(
        R.drawable.mac,
        R.drawable.acer,
        R.drawable.asus,
        R.drawable.dell,
        R.drawable.mac
    )
    var images = intArrayOf(
        R.drawable.laptop,
        R.drawable.tablet,
        R.drawable.keyboard,
        R.drawable.monitor,
        R.drawable.mouse,
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Retrieve the array resources of data
        var  titles = resources.getStringArray(R.array.title);
        var  subTitles = resources.getStringArray(R.array.subTitle);
        var  prices = resources.getIntArray(R.array.price)


        for ((index,title) in titles.withIndex()){
            productList.add(Product(title,subTitles[index],prices[index].toDouble()))
        }

        binding.rv.layoutManager = LinearLayoutManager(this)
        // Create an object for the MyAdapter
        val adapter = MyAdapter(this , productList ,images,icons )
        // Set adapter to your RecyclerView
        binding.rv.adapter = adapter

        binding.viewBtn.setOnClickListener{
            var cartListString= ""
            cartListString = if (CartSingleton.title.size==0){
                "No Item in the cart"
            }else{
                "Viewing Cart:\n ${CartSingleton.title.joinToString("\n") }"
            }
//            Toast.makeText(this,cartListString,Toast.LENGTH_LONG).show()
            Snackbar.make(binding.root, cartListString, Snackbar.LENGTH_LONG).show()
        }
    }
}