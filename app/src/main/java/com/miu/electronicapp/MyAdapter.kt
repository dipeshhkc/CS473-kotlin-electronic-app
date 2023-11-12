package com.miu.electronicapp
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
// Without data class data passed as single field

class MyAdapter  (var context: Context,  var products:ArrayList<Product>, var imgs:IntArray, var icons:IntArray,) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.card_layout, parent, false)
        return MyViewHolder(v);
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        // Values assigned to the respective View components in the card_layout.xml
        holder.title.text = products[position].title
        holder.subTitle.text = products[position].subTitle
        holder.price.text = "$ " + products[position].cost.toString()
        holder.img.setImageResource(imgs[position])
        holder.icon.setImageResource(icons[position])
        // Implement the click Listener in the using the layout.
        holder.addBtn.setOnClickListener{
            var res = products[position].title
            Toast.makeText(context," $res added", Toast.LENGTH_SHORT).show()
            CartSingleton.title.add(products[position].title)
        }

        holder.rlayout.setOnClickListener{
            // User clicks the list Item to open the DetailActivity
            val intent = Intent(context, DetailActivity::class.java)
            var res = products[position].title
            Toast.makeText(context," $res clicked", Toast.LENGTH_LONG).show()
            // Pass data to the Detail Activity
            intent.putExtra("image", imgs[position])
            intent.putExtra("product", products[position])

            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return products.size
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        // Instance fields
        var rlayout: ConstraintLayout
        var addBtn: Button
        var title:TextView
        var subTitle:TextView
        var price:TextView
        var img:ImageView
        var icon:ImageView
        // Initialization of Instance fields using init block
        init{
            rlayout = itemView.findViewById(R.id.cLayout) as ConstraintLayout
            addBtn = itemView.findViewById(R.id.addBtn) as Button
            title = itemView.findViewById(R.id.title) as TextView
            subTitle = itemView.findViewById(R.id.subtitle) as TextView
            price = itemView.findViewById(R.id.price) as TextView
            img = itemView.findViewById(R.id.image) as ImageView
            icon = itemView.findViewById(R.id.icon) as ImageView
        }
    }
}