package dev.matyaqubov.modul05lesson01.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import dev.matyaqubov.modul05lesson01.R
import dev.matyaqubov.modul05lesson01.model.Theme

class ThemeAdapter(var context: Context,var themes:ArrayList<Theme>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var selected_index=0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_theme,parent,false)
        return ThemeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val theme=themes[position]
        if (holder is ThemeViewHolder){
            holder.image.setImageResource(theme.image)
            holder.item.isSelected = theme.isSelected

            if (theme.isSelected) selected_index=position

            holder.item.setOnClickListener {
                selected(position)
            }
        }
    }

    private fun selected(position: Int) {
        themes[position].isSelected=true
        themes[selected_index].isSelected=false
        notifyItemChanged(position)
        notifyItemChanged(selected_index)
        selected_index=position

    }

    override fun getItemCount(): Int {
        return themes.size
    }

    class ThemeViewHolder(val view: View):RecyclerView.ViewHolder(view){
        var item=view.findViewById<LinearLayout>(R.id.item)
        var image=view.findViewById<ImageView>(R.id.iv_theme)
    }
}