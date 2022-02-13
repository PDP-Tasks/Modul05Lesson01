package dev.matyaqubov.modul05lesson01.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.matyaqubov.modul05lesson01.R
import dev.matyaqubov.modul05lesson01.adapter.ThemeAdapter
import dev.matyaqubov.modul05lesson01.model.Theme

class RealCaseActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_real_case)
        initViews()
    }

    private fun initViews() {
        recyclerView=findViewById(R.id.rv_main)
        recyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        refreshAdapter(getAllThemes())
    }

    private fun refreshAdapter(themes: java.util.ArrayList<Theme>) {
        val adapter=ThemeAdapter(this,themes)
        recyclerView.adapter=adapter
    }

    private fun getAllThemes(): ArrayList<Theme> {
        val themes=ArrayList<Theme>()
        themes.add(Theme(R.drawable.first_theme))
        themes.add(Theme(R.drawable.second_theme))
        themes.add(Theme(R.drawable.third_theme))
        themes.add(Theme(R.drawable.fourth_theme))

        themes.add(Theme(R.drawable.first_theme))
        themes.add(Theme(R.drawable.second_theme,true))
        themes.add(Theme(R.drawable.third_theme))
        themes.add(Theme(R.drawable.fourth_theme))

        themes.add(Theme(R.drawable.first_theme))
        themes.add(Theme(R.drawable.second_theme))
        themes.add(Theme(R.drawable.third_theme))
        themes.add(Theme(R.drawable.fourth_theme))
        return themes
    }
}