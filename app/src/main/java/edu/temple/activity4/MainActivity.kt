package edu.temple.activity4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var textSizeSelector: RecyclerView
    lateinit var textSizeDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textSizeDisplay = findViewById(R.id. textSizeDisplayTextView)


        val textSizes = Array(20){(it + 1) * 5}
        Log.d("Array values", textSizes. contentToString())


        with(findViewById(R.id.textSizeSelectorRecyclerView) as RecyclerView){




            adapter = TextSizeAdapter(textSizes){
                textSizeDisplay.textSize = it

                intent = Intent (this@MainActivity, MainActivity2::class.java)

                intent.putExtra("textSize",it)

                startActivity(intent)
            }
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

    }
}


/* Convert to RecyclerView.Adapter */
class TextSizeAdapter(_textSizes: Array<Int>, _callback:(Float) -> Unit) : RecyclerView.Adapter<TextSizeAdapter.TextSizeViewHolder>(){

    val textSizes = _textSizes
    val callback = _callback

    inner class TextSizeViewHolder(view:TextView) : RecyclerView.ViewHolder(view){
        val textView = view

        init {
            textView.setOnClickListener {callback(textSizes[adapterPosition].toFloat())}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextSizeViewHolder {
        return TextSizeViewHolder(TextView(parent.context).apply{setPadding(5, 20, 0, 20)})
    }

    override fun onBindViewHolder(holder: TextSizeViewHolder, position: Int) {
        holder.textView.apply{
            text = textSizes[position].toString()
            textSize = textSizes[position].toFloat()

            //setOnClickListener{callback(textSizes[position].toFloat())}
        }

        //holder.textView.text = textSizes[position].toString()
        //holder.textView.textSize = textSizes[position].toFloat()
    }

    override fun getItemCount(): Int {
        return textSizes.size
    }
}