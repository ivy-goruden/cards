package com.example.cards

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.util.stream.IntStream.range


class MyAdapter: RecyclerView.Adapter<MyAdapter.ViewHolder>{ // ERROR here "ViewHolder"
    // Atributes of Adapter: list of users.
    private  var  valList: List<List<String>>
    private var context: Context



    // Constructor
    constructor(valList: List<List<String>>, context: Context){
        this.valList = valList
        this.context = context
    }

    /* Implement the three methods from interface. */
    /* Inflates view and returns HeaderViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {// ERROR here "ViewHolder"
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.library_base, parent,false)

        return ViewHolder(view, this.context)
    }

    /* To bind the data from the Main Activity or Data class inside the RecyclerView*/
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var color:Int = position
        var values: List<String> = valList[position]
        var color_name: Int = position



        // Now, to send this data (above) to the holder
        holder.setData(color, values, color_name)
    }

    override fun getItemCount(): Int {
        return valList.size
    }

    /* ViewHolder for displaying list. */
    // #1 #custonRecyclerView -> create ViewHolder
    class ViewHolder(val itemView: View, val context: Context): RecyclerView.ViewHolder(itemView) {
        var color_list = arrayListOf(R.color.grey, R.color.orange,R.color.blue,R.color.green,R.color.yellow,R.color.purple)
        var days_of_the_week = listOf("Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница")
        var backgroundList = listOf(R.drawable.library_text_back_grey, R.drawable.library_text_back_pink,R.drawable.library_text_back_blue,R.drawable.library_text_back_green,R.drawable.library_text_back_yellow,R.drawable.library_text_back_purple)
        fun setData(color:Int, values:List<String>, color_name: Int) {
            findViews(itemView.findViewById(R.id.library_table))
            for (i in range(0, big_img.size)){
                DrawableCompat.setTint(big_img[i].drawable, getColor(context, color_list[color_name]))
                DrawableCompat.setTint(small_img[i].drawable, getColor(context, R.color.white))
                img_text[i].setText(values[i])
            }
            main_paragraph.setText(days_of_the_week[color_name])
            for(i in textViewList){
                i.setBackgroundResource(backgroundList[color_name])
            }

        }
        private var textViewList = emptyList<TextView>()
        private var big_img: List<ImageView>  = listOf(itemView.findViewById(R.id.imageView30),itemView.findViewById(R.id.imageView33),itemView.findViewById(R.id.imageView36),itemView.findViewById(R.id.imageView39),itemView.findViewById(R.id.imageView42),itemView.findViewById(R.id.imageView45),itemView.findViewById(R.id.imageView48),itemView.findViewById(R.id.imageView51),itemView.findViewById(R.id.imageView54),itemView.findViewById(R.id.imageView57),itemView.findViewById(R.id.imageView60),itemView.findViewById(R.id.imageView2))
        private var small_img: List<ImageView> = listOf(itemView.findViewById(R.id.imageView31),itemView.findViewById(R.id.imageView34),itemView.findViewById(R.id.imageView37),itemView.findViewById(R.id.imageView40),itemView.findViewById(R.id.imageView43),itemView.findViewById(R.id.imageView46),itemView.findViewById(R.id.imageView49),itemView.findViewById(R.id.imageView52),itemView.findViewById(R.id.imageView55),itemView.findViewById(R.id.imageView58),itemView.findViewById(R.id.imageView61),itemView.findViewById(R.id.imageView))
        private var img_text: List<TextView> = listOf(itemView.findViewById(R.id.textView29),itemView.findViewById(R.id.textView32),itemView.findViewById(R.id.textView35),itemView.findViewById(R.id.textView38),itemView.findViewById(R.id.textView41),itemView.findViewById(R.id.textView44),itemView.findViewById(R.id.textView47),itemView.findViewById(R.id.textView50),itemView.findViewById(R.id.textView53),itemView.findViewById(R.id.textView56),itemView.findViewById(R.id.textView59),itemView.findViewById(R.id.textView28))
        private var main_paragraph: TextView   = itemView.findViewById(R.id.paragraph)

        fun findViews(v: View?) {
            try {
                if (v is ViewGroup) {
                    val vg = v
                    for (i in 0 until vg.childCount) {
                        val child = vg.getChildAt(i)
                        // recursively call this method
                        findViews(child)
                    }
                } else if (v is TextView) {
                    textViewList+=v
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    }

}