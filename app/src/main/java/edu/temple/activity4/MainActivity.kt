package edu.temple.activity4

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

        //val textSizeViewHolder = TextSizeAdapter.TextSizeViewHolder(textSizeDisplay)

        //val callback = {textSize: Float -> textSizeDisplay.textSize = textSize}

        //val someFunction : (Int, Int) -> Unit = {x:Int, y:Int -> textSizeDisplay.text = (x+y).toString()}
        //someFunction(4,6)
        //ARandomClass().myLocalFunction("Kevin", 74, someFunction)

        //textSizeSelector = findViewById(R.id.textSizeSelectorRecyclerView)

        // Trying to create array of integers that are multiples of 5
        // Verify correctness by examining array values.
        val textSizes = Array(20){(it + 1) * 5}
        Log.d("Array values", textSizes. contentToString())

        with(findViewById(R.id.textSizeSelectorRecyclerView) as RecyclerView){
            adapter = TextSizeAdapter(textSizes){
                textSizeDisplay.textSize = it
            }
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        //textSizeSelector.adapter = TextSizeAdapter(textSizes)
        //textSizeSelector.layoutManager = LinearLayoutManager(this)

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