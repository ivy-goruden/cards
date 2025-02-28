package com.example.cards

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Bundle
import android.transition.Transition.TransitionListener
import android.util.Log
import android.view.View
import android.view.View.LAYER_TYPE_HARDWARE
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionScene
import androidx.constraintlayout.motion.widget.TransitionBuilder
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat.setLayerType
import androidx.viewpager2.widget.ViewPager2
import at.blogc.android.views.ExpandableTextView
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.random.Random.Default.nextInt


class MainActivity : AppCompatActivity() {
    var card_closed = true
    var night = false
    var first_click = false
    var saturday = false
    var card_closed1 = true
    var card_closed2 = true
    val duration_num = 700
    var now_activity = "main"
    var mast_list = arrayListOf("T", "K", "Q", "V", "10", "9", "8", "7", "6", "5", "4", "3")
    var outer_list = arrayListOf("rect", "rect", "circ", "circ", "circ", "plus", "plus", "plus", "tri", "tri", "tri", "rect")
    var inner_list = arrayListOf("plus", "circ", "rect", "tri", "plus", "circ", "rect", "tri", "plus", "circ", "rect", "tri")

    var days_of_the_week = listOf("Суббота", "Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница")
    var value = 0f
    var boool = true
    var main_list = mutableListOf<List<Int>>()
    var main_list2 = mutableListOf<List<Int>>()
    var cover = R.drawable.cover_new
    var valet = R.drawable.valet
    lateinit var btn:Button
    val set = AnimatorSet()
    val enter_set = AnimatorSet()
    lateinit var animation_enter1: ObjectAnimator
    lateinit var animation_enter2: ObjectAnimator
    lateinit var animation_enter3: ObjectAnimator
    lateinit var animation_enter4: ObjectAnimator
    lateinit var animation_submit1: ObjectAnimator
    lateinit var animation_submit2: ObjectAnimator
    var set_submit = AnimatorSet()

    lateinit var text_list1: List<TextView>
    lateinit var text_list2: List<TextView>
    lateinit var result: List<TextView>
    lateinit var scores: List<Int>
    lateinit var main_layout: View
    lateinit var mistake_layout: View
    lateinit var score: View
    lateinit var rules: View
    lateinit var library: View
    lateinit var result_layout: View
    lateinit var card: ImageView
    lateinit var card1: ImageView
    lateinit var card2: ImageView
    lateinit var viewPager: ViewPager2
    lateinit var myAdapter: MyAdapter
    lateinit var animation1:ObjectAnimator
    lateinit var animation2: ObjectAnimator
    lateinit var constraint: ConstraintLayout
    lateinit var submit_btn_result: ImageView
    lateinit var card1_obj: Card
    lateinit var card2_obj: Card
    lateinit var card3_obj: Card
    var card1_img = 0
    var card2_img = 0
    var card3_img = 0
    var rotate = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)


        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // handle logic
                if (!saturday){
                    newView(main_layout)
                }
            }
        })

        var valList = List(6) { List(12) { "0" } }
        viewPager = findViewById(R.id.base_library_view)
        myAdapter = MyAdapter(valList, applicationContext)
        viewPager.adapter = myAdapter
        var dots = findViewById<WormDotsIndicator>(R.id.dots)
        dots.attachTo(viewPager)

        text_list1 = listOf<TextView>(findViewById(R.id.textView26),findViewById(R.id.textView30),findViewById(R.id.textView32), findViewById(R.id.textView3))
        text_list2 = listOf<TextView>(findViewById(R.id.textView36),findViewById(R.id.textView38),findViewById(R.id.textView39), findViewById(R.id.textView34))
        result  = listOf<TextView>(findViewById(R.id.textView5),findViewById(R.id.textView6),findViewById(R.id.textView23), findViewById(R.id.textView24))
        scores = listOf(3, 2, 2, 10)
        library = findViewById(R.id.library)
        card = findViewById(R.id.UserCard)
        btn = findViewById(R.id.Play)
        btn.isEnabled = true
        main_layout = findViewById<LinearLayout>(R.id.main_layout)
        score = findViewById<LinearLayout>(R.id.score)
        rules = findViewById<LinearLayout>(R.id.rules)
        result_layout = findViewById<LinearLayout>(R.id.result)
        mistake_layout = findViewById(R.id.mistake_layout)
        card1 = findViewById(R.id.Card1)
        card2 = findViewById(R.id.Card2)
        constraint = findViewById(R.id.constraint)
        submit_btn_result = findViewById(R.id.to_result_btn)
        animation_submit1 = ObjectAnimator.ofFloat(submit_btn_result, "translationX", 0F,-10F)
        animation_submit2 = ObjectAnimator.ofFloat(submit_btn_result, "alpha", 0F, 1F)



        val list0 = ArrayList<Int>()
        val list1 = ArrayList<Int>()
        val list2 = ArrayList<Int>()
        val list3 = ArrayList<Int>()
        val list4 = ArrayList<Int>()
        val list5 = ArrayList<Int>()

        for (i in 0..5) {
            for (j in 0..11) {
                // Получаем ID картинки
                val resId = resources.getIdentifier(
                    "drawable/k${i}_$j",
                    "drawable",
                    application.packageName
                )

                // Добавляем ID в соответствующий список
                when (i) {
                    0 -> list0.add(resId)
                    1 -> list1.add(resId)
                    2 -> list2.add(resId)
                    3 -> list3.add(resId)
                    4 -> list4.add(resId)
                    5 -> list5.add(resId)
                }
            }
        }
        main_list.add(list0.toMutableList())
        main_list.add(list1.toMutableList())
        main_list.add(list2.toMutableList())
        main_list.add(list3.toMutableList())
        main_list.add(list4.toMutableList())
        main_list.add(list5.toMutableList())

        val list6 = ArrayList<Int>()
        val list7 = ArrayList<Int>()
        val list8 = ArrayList<Int>()
        val list9 = ArrayList<Int>()
        val list10 = ArrayList<Int>()
        val list11 = ArrayList<Int>()

        for (i in 0..5) {
            for (j in 0..11) {
                // Получаем ID картинки
                val resId = resources.getIdentifier(
                    "drawable/k${i}_$j" + 'r',
                    "drawable",
                    application.packageName
                )

                // Добавляем ID в соответствующий список
                when (i) {
                    0 -> list6.add(resId)
                    1 -> list7.add(resId)
                    2 -> list8.add(resId)
                    3 -> list9.add(resId)
                    4 -> list10.add(resId)
                    5 -> list11.add(resId)
                }
            }
        }

        main_list2.add(list6.toMutableList())
        main_list2.add(list7.toMutableList())
        main_list2.add(list8.toMutableList())
        main_list2.add(list9.toMutableList())
        main_list2.add(list10.toMutableList())
        main_list2.add(list11.toMutableList())
        var stats = findViewById<ImageView>(R.id.stats)
        var rules_btn = findViewById<ImageView>(R.id.rules_btn)
        stats.setOnClickListener { newView(library) }
        rules_btn.setOnClickListener { newView(rules) }
        var result_back = findViewById<Button>(R.id.result_back)
        var rules_back = findViewById<ImageView>(R.id.rules_back)
        result_back.setOnClickListener { newView(main_layout) }
        rules_back.setOnClickListener { newView(main_layout) }
        var back = findViewById<ImageView>(R.id.submit_btn_result)
        back.setOnClickListener {
            reset_progress()
        }
        var library_btn = findViewById<ImageView>(R.id.library_button)
        library_btn.setOnClickListener {
            newView(main_layout)
        }

        submit_btn_result.setOnClickListener {
            newView(result_layout)
        }
        var back_to_cards_btn = findViewById<ImageView>(R.id.again_btn2)
        back_to_cards_btn.setOnClickListener {
            newView(main_layout)
        }

        var night_mode = findViewById<Switch>(R.id.night_mode)
        night_mode.setOnClickListener {
            if (night_mode.isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        var more_btn = findViewById<ImageView>(R.id.view_more_btn)
        var more_text = findViewById<ExpandableTextView>(R.id.comments)
        var more_anim1 = ObjectAnimator.ofFloat(more_btn, "rotation", 0f, 180f)
        more_btn.setOnClickListener {
            set.duration = 750
            if (more_text.isExpanded){
                more_text.collapse()
                set.reverse()
            }
            else{
                more_text.expand()
                set.play(more_anim1)
                set.start()
            }

        }
        btn.setOnClickListener {
            start_animation()
            main_func()
            constraint.setOnClickListener{
                first_click = !first_click
                if (first_click){
                    parent_card_animation(card1, card1_img, boool)
                    parent_card_animation(card2, card2_img, boool)

                }
                else{
                    to_result_btn_animation()
                    submit_btn_result.isClickable = true
                    constraint.isClickable = false
                    card_animation(card, card3_img, cover)
                    score_counter(card1_obj, card2_obj, card3_obj, rotate)
                }
            }
        }

    }

    fun card_animation(card: ImageView, img_open:Int, img_closed:Int, duration_given:Int = 0){
        value+=180f
        var resourse = img_closed
        if (card_closed){
            resourse = img_open
            value = 0f
        }

        set.play(animation1)
        if (duration_given == 0){
            set.duration = duration_num.toLong()
        }
        else{
            set.duration = duration_given.toLong()
        }
        set.interpolator = DecelerateInterpolator()
        set.start()
        card.setImageResource(resourse)
        set.play(animation2)
        set.start()
        card_closed = !card_closed
        value = 0f
    }


    fun parent_card_animation(card: ImageView, img:Int, closed:Boolean){
        value+=180f
        var resourse = cover
        if (closed){
            value = 0f
            resourse = img
        }
        animation1 = ObjectAnimator.ofFloat(card, "rotationY", value, value+90f)
        animation2 = ObjectAnimator.ofFloat(card, "rotationY", value+90f, value+180f)
        set.play(animation1)
        set.duration = duration_num.toLong()
        set.interpolator = DecelerateInterpolator()
        set.start()
        Log.d("IMG", resourse.toString())
        card.setImageResource(resourse)
        set.play(animation2)
        set.start()
        value = 0f
    }


    fun reset_progress(){
        submit_btn_result.alpha = 0F
        submit_btn_result.isClickable = false
//        rules_text = ""
//        for (i in text_list1){
//            i.setTextColor(getColor(R.color.first_text_colour))
//            i.setText("0")
//        }
//        for (i in text_list2){
//            i.setTextColor(getColor(R.color.second_text_colour))
//            i.setText("0")
//        }
//        for (i in result){
//            i.setTextColor(getColor(R.color.third_text_colour))
//            i.setText("0")
//        }
        newView(main_layout)
        parent_card_animation(card1, 0, false)
        parent_card_animation(card2, 0, false)
        card_animation(card, 1, cover)
        constraint.isClickable = true
        main_func()


    }

    fun main_func(){
        GlobalScope.launch(Dispatchers.IO) {
            val calendar = Calendar.getInstance()
            var day = calendar.get(Calendar.DAY_OF_WEEK)
            var day_name = ""
            var hour = calendar.get(Calendar.HOUR_OF_DAY)
            var minuet = calendar.get(Calendar.MINUTE)
            var minuet_txt = minuet.toString()
            var hour_txt = hour.toString()
            if (minuet<10){
                minuet_txt = "0"+minuet.toString()
            }
            if (hour<10){
                hour_txt = "0"+hour.toString()
            }
            var date_text = findViewById<TextView>(R.id.date_text)
            rotate = -1
//        var color = 0
            var mainList = main_list2
            if (hour>=12){
                hour = hour-12
                rotate = 1
                mainList = main_list
            }
            var minuet_mast = minuet/5
            var minuet_color = minuet%5
            var minuet_list = arrayListOf(0, 1, 2, 3, 4, 5)
            minuet_list.remove(day-1)
            if (day!=7){
            card1_obj = Card(rotate, day-1, mast_list[hour], outer_list[hour], inner_list[hour])
            card1_img = mainList[day-1][hour]
            card2_obj = Card(1, minuet_list[minuet_color], mast_list[minuet_mast],outer_list[minuet_mast], inner_list[minuet_mast])
            card2_img = main_list[minuet_list[minuet_color]][minuet_mast]
            var second = nextInt(2)
            var second2 = nextInt(6)
            var second3 = nextInt(12)

            card3_obj = Card(1, listOf(0, 1, 2, 3, 4, 5)[second2], mast_list[second3], outer_list[second3], inner_list[second3])
            card3_img = main_list[second2][second3]
            while (card3_img == card2_img || card3_img == card1_img){
                card3_obj = Card(1, listOf(0, 1, 2, 3, 4, 5)[second2], mast_list[second3], outer_list[second3], inner_list[second3])
                card3_img = main_list[second2][second3]
            }}
            withContext(Dispatchers.Main){
                if (day == 7){
                    saturday = true
                    newView(mistake_layout)
                }
                else{
                    day_name = days_of_the_week[day]
                    date_text.text = "$day_name, $hour_txt:$minuet_txt"
                }
            }

        }



//
//        constraint.setOnClickListener {
//            parent_card_animation(card1, card1_img, boool)
//            parent_card_animation(card2, card2_img, boool)
//
//            constraint.setOnClickListener {
//                to_result_btn_animation()
////                constraint.isClickable = false
//                constraint.setOnClickListener(null)
//                var second = nextInt(2)
//                var second2 = nextInt(6)
//                var second3 = nextInt(12)
//
//                var card3_obj = Card(1, listOf(0, 1, 2, 3, 4, 5)[second2], mast_list[second3], outer_list[second3], inner_list[second3])
//                var card3_img = main_list[second2][second3]
//                while (card3_img == card2_img || card3_img == card1_img){
//                    card3_obj = Card(1, listOf(0, 1, 2, 3, 4, 5)[second2], mast_list[second3], outer_list[second3], inner_list[second3])
//                    card3_img = main_list[second2][second3]
//                }
//
//                card_animation(card, card3_img, cover)
//                fun Boolean.toInt() = if (this) 1 else -1
//                submit_btn_result.setOnClickListener {
//                    score_counter(card1_obj, card2_obj, card3_obj, rotate)
//                    newView(result_layout)
//                        submit_btn_result.setOnClickListener {
//                        newView(result_layout)
//                    }
//                }
//            }
//
//
//
//        }


    }

    fun score_counter(card1_obj:Card, card2_obj:Card, card3_obj:Card, rotate:Int){
        GlobalScope.launch(Dispatchers.IO) {
            var para1 = false
            var para2 = false
            var para3 = false
            var para4 = false
            var no_matches = true
            var jackpot = false
            var rules_text = ""
//        var new1 = true
//        var new2 = true
            var rotate_card = true
            var color_bool1 = false
            var color_bool2 = false
            val table_text_list1 = MutableList(text_list1.size) { "" }
            val table_text_list2 = MutableList(text_list2.size-1) { "" }
            val table_result = MutableList(result.size) { "0" }

            if (card1_obj.rotate != card3_obj.rotate){
                rotate_card = false
            }

            if (card1_obj.color == card3_obj.color){
//            text_list1[0].setText("✔")
                table_text_list1[0] = "✔"
//            result[0].setText(Integer.toString(result[0].text.toString().toInt()+scores[0]*(rotate.toInt())))
                table_result[0] = Integer.toString(result[0].text.toString().toInt()+scores[0]*rotate)
                no_matches = false
                color_bool1 = true
            }
//        else{
//            text_list1[0].setText("")
//        }

            if (card2_obj.color == card3_obj.color){
//            text_list2[0].setText("✔")
//            result[0].setText((result[0].text.toString().toInt()+scores[0]*rotate.toInt()*-1).toString())
                table_result[0] = (result[0].text.toString().toInt()+scores[0]*rotate*-1).toString()
                table_text_list2[0] = "✔"
                no_matches = false
                color_bool2 = true
            }
//        else{
//            text_list2[0].setText("")
//        }

            if (color_bool1 and color_bool2){
                rules_text+="Цвет совпадает на 3 картах, суммарное кол-во очков 6\n"
            }
            else if(color_bool1){
                rules_text+="Цвет совпадает на первой карте и карте игрока, суммарное кол-во очков 3\n"
            }
            else if(color_bool2){
                rules_text+="Цвет совпадает на второй карте и карте игрока, суммарное кол-во очков 3\n"
            }
            else{
                rules_text+="Цвет на всех трех картах разный, суммарно 0 очков\n"
            }


            if (card1_obj.inner == card3_obj.inner){
                table_text_list1[2] = "✔"
//            text_list1[2].setText("✔")
                para3 = true
                no_matches = false
            }
//        else{
//            text_list1[2].setText("")
//        }
            if (card2_obj.inner == card3_obj.inner){
//            text_list2[2].setText("✔")
                table_text_list2[2] = "✔"
                para4 = true
                no_matches = false
            }
//        else{
//            text_list2[2].setText("")
//        }

            if (card1_obj.outer == card3_obj.outer){
//            text_list1[1].setText("✔")
                table_text_list1[1] = "✔"
                para1 = true
                no_matches = false
            }
//        else{
//            text_list1[1].setText("")
//        }
            if (card2_obj.outer == card3_obj.outer){
//            text_list2[1].setText("✔")
                table_text_list2[1] = "✔"
                para2 = true
                no_matches = false
            }
//        else{
//            text_list2[1].setText("")
//        }
            if (para1 and para2 and para3 and para4){
                table_result[1] = "36"
                table_result[2] = "36"
//            result[1].setText("36")
//            result[2].setText("36")
                jackpot = true
                rules_text+="Номинал совпадает на трех картах, суммарно 72 очка\n"
            }
            else if((para1 and para2)){
//            result[1].setText("8")
                table_result[1] = "8"
                if (para3){
//                result[2].setText((scores[2].toString().toInt()*rotate).toString())
                    table_result[2] = (scores[2]*rotate).toString()
                    rules_text+="Внутренняя фигура совпадает на 3х картах, суммарно 8 очков\n"
                    rules_text+="Внешняя фигура на первой карте совпадает с внешней фигурой на карте игрока, суммарно 2 очка\n"
                }
                else if (para4){
                    table_result[2] = (scores[2]*rotate*-1).toString()
//                result[2].setText((scores[2].toString().toInt()*rotate.toInt()*-1).toString())
                    rules_text+="Внутренняя фигура совпадает на 3х картах, суммарно 8 очков\n"
                    rules_text+="Внешняя фигура на второй карте совпадает с внешней фигурой на карте игрока, суммарно 2 очка\n"
                }
                else{
                    rules_text+="Внутренняя фигура совпадает на 3х картах, суммарно 8 очков\n"
                }
            }
            else if(para3 and para4){
                table_result[2] = "8"
//            result[2].setText("8")
                if (para1){
                    table_result[1] = (scores[1]*rotate).toString()
//                result[1].setText((scores[1].toString().toInt()*rotate.toInt()).toString())
                    rules_text+="Внешняя фигура совпадает на 3х картах, суммарно 8 очков\n"
                    rules_text+="Внутренняя фигура на первой карте совпадает с внешней фигурой на карте игрока, суммарно 2 очка\n"
                }
                else if (para2){
                    table_result[1] =(scores[1]*rotate*-1).toString()
//                result[1].setText((scores[1].toString().toInt()*rotate.toInt()*-1).toString())
                    rules_text+="Внешняя фигура совпадает на 3х картах, суммарно 8 очков\n"
                    rules_text+="Внутренняя фигура на второй карте совпадает с внешней фигурой на карте игрока, суммарно 2 очка\n"
                }
                else{
                    rules_text+="Внешняя фигура совпадает на 3х картах, суммарно 8 очков\n"
                }
            }
            else{
                if (para1){
                    table_result[1] = (scores[1]*rotate).toString()
//                result[1].setText((scores[1].toString().toInt()*rotate.toInt()).toString())
                    rules_text+="Внутренняя фигура на первой карте совпадает с внешней фигурой на карте игрока, суммарно 2 очка\n"
                }
                if (para2){
                    table_result[1] = (scores[1]*rotate*-1).toString()
//                result[1].setText((scores[1].toString().toInt()*rotate.toInt()*-1).toString())
                    rules_text+="Внутренняя фигура на второй карте совпадает с внешней фигурой на карте игрока, суммарно 2 очка\n"
                }
                if (para3){
                    table_result[2] = (scores[2]*rotate).toString()
//                result[2].setText((scores[2].toString().toInt()*rotate.toInt()).toString())
                    rules_text+="Внешняя фигура на первой карте совпадает с внешней фигурой на карте игрока, суммарно 2 очка\n"
                }
                if (para4){
                    table_result[2] = (scores[2]*rotate*-1).toString()
//                result[2].setText((scores[2]*(rotate.toInt())*-1).toString())
                    rules_text+="Внешняя фигура на второй карте совпадает с внешней фигурой на карте игрока, суммарно 2 очка\n"
                }

            }




            withContext(Dispatchers.Main){
                if (no_matches){
                    rules_text+="Совпадений между картами нет, суммарно 10 баллов"
//            for (i in result){
//                i.setText("0")
//            }
//            result[3].setText("10")
                    table_result[3] = "10"
                    table_text_list1[3] = "✔"
                    result[3].setTextColor(getColor(R.color.green))
                }
                if (rotate_card){
                    for (i in text_list2.subList(0, 3)){
                        i.setTextColor(resources.getColor(R.color.red,theme))
                    }
                    rules_text="Поворот первой карты совпадает с картой игрока. Любое совпадение с первой картой будет увеличивать очки, совпадение же со второй уменьшать.\n"+rules_text
                }
                else{
                    for (i in text_list1.subList(0, 3)){
                        i.setTextColor(resources.getColor(R.color.red, theme))
                    }
                    rules_text="Поворот первой карты не совпадает с картой игрока. Любое совпадение с первой картой будет уменьшать очки, совпадение же со второй увеличивать.\n"+rules_text
                }
                if (jackpot){
                    text_list1[1].setTextColor(getColor(R.color.green))
                    text_list1[2].setTextColor(getColor(R.color.green))
                    text_list2[1].setTextColor(getColor(R.color.green))
                    text_list2[2].setTextColor(getColor(R.color.green))
                }
                var comments = findViewById<TextView>(R.id.comments)
                comments.text = rules_text
                for (i in table_text_list1.indices){
                    text_list1[i].text = table_text_list1[i]
                }
                for (i in table_text_list2.indices){
                    text_list2[i].text = table_text_list2[i]
                }
                for (i in table_result.indices){
                    result[i].text = table_result[i]
                }
                text_list2[3].text = "0"
                for (i in result){
                    text_list2[3].text = (text_list2[3].text.toString().toInt()+i.text.toString().toInt()).toString()
                }
            }
        }

    }

    fun to_result_btn_animation(){
        set_submit.duration = 1000
        set_submit.playTogether(animation_submit1, animation_submit2)
        set_submit.start()
    }

    fun newView(view: View){
        if (view==main_layout){
            now_activity = "main"
        }
        else{
            when(view){
                rules -> now_activity = "rules"
                result_layout -> now_activity = "result"
                score -> now_activity = "score"
                library -> now_activity = "library"
            }
        }
        main_layout.visibility = View.GONE
        score.visibility  =View.GONE
        rules.visibility  =View.GONE
        result_layout.visibility  =View.GONE
        library.visibility = View.GONE
        mistake_layout.visibility = View.GONE
        view.visibility = View.VISIBLE
    }
    fun start_animation(){
        btn.animate().translationY(200F).alpha(0F).setDuration(500).withStartAction {
            card2.animate().translationX(0F).setDuration(500).withEndAction {
                card1.animate().translationX(0F).setDuration(500).withEndAction {
                    card.animate().translationX(0F).setDuration(500)
                }
            }
        }.start()
    }


}



