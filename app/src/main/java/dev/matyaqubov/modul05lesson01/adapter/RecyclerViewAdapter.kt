package dev.matyaqubov.modul05lesson01.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import dev.matyaqubov.modul05lesson01.R
import dev.matyaqubov.modul05lesson01.model.Intro

class RecyclerViewAdapter(val context: Context,var intros:ArrayList<Intro>) :RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return IntroViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_first,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val intro = intros[position]

        if (holder is IntroViewHolder){
            holder.apply {
                animation.setAnimation(intro.animation,position.toString())
                text1.text=intro.text1
                text2.text=intro.text2
                text3.text=intro.text3
                text4.text=intro.text4
            }
        }
    }

    override fun getItemCount(): Int {
        return intros.size
    }

    class IntroViewHolder(val view: View):RecyclerView.ViewHolder(view){
        var animation=view.findViewById<LottieAnimationView>(R.id.lottie)
        var text1=view.findViewById<TextView>(R.id.tv_1)
        var text2=view.findViewById<TextView>(R.id.tv_2)
        var text3=view.findViewById<TextView>(R.id.tv_3)
        var text4=view.findViewById<TextView>(R.id.tv_4)
    }
}