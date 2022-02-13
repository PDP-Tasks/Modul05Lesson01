package dev.matyaqubov.modul05lesson01.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import dev.matyaqubov.modul05lesson01.R
import dev.matyaqubov.modul05lesson01.adapter.MyDotsIndicator
import dev.matyaqubov.modul05lesson01.adapter.RecyclerViewAdapter
import dev.matyaqubov.modul05lesson01.model.Intro

class SecondActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var ll:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        initViews()
    }

    private fun initViews() {
        ll=findViewById(R.id.ll)
        recyclerView = findViewById(R.id.rv_pager)
        recyclerView.layoutManager =LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        var snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        var adapter = RecyclerViewAdapter(this, prepareIntros())
        recyclerView.adapter = adapter

        // with DotsIndicator
//       val myDotsIndicator = MyDotsIndicator()
//        myDotsIndicator.setDotColor(-0x22000000, 0x33000000)
//        recyclerView.addItemDecoration(myDotsIndicator)

        //with traditional method
        initBottomDots(adapter.itemCount, recyclerView)

    }

    private fun initBottomDots(itemCount: Int, recyclerView: RecyclerView) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && itemCount > 0) {
            val ll: LinearLayout = getBottomDotsLayout(itemCount)
            recyclerView.setOnScrollChangeListener(object : View.OnScrollChangeListener {
                override fun onScrollChange(p0: View?, p1: Int, p2: Int, p3: Int, p4: Int) {
                   var firstVisible:Int=(recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                   var lastVisible:Int=(recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    transitionDots(ll,lastVisible,lastVisible-firstVisible+1)
                }

            })
        }
    }

    private fun transitionDots(ll: LinearLayout, lastVisible: Int, total: Int) {
        for (i in 0 until ll.childCount){
            if (ll.getChildAt(i) is TextView){
                ll.getChildAt(i).setBackgroundResource(R.drawable.indicator0)
            }
        }
        //set dots visible item
        var lastVisible=lastVisible
        for (i in 0 until total){
            if (lastVisible>=0){
                ll.getChildAt(lastVisible).setBackgroundResource(R.drawable.indicator1)
                lastVisible--
            }
        }
    }

    private fun getBottomDotsLayout(count: Int): LinearLayout {
        val ll = LinearLayout(this)
        ll.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        ll.orientation = LinearLayout.HORIZONTAL
        ll.gravity = Gravity.CENTER

        for (i in 0 until count) {
            val textView = TextView(this)
            val params = LinearLayout.LayoutParams(15, 4)
            params.setMargins(3, 2, 3, 2)
            textView.layoutParams = params
            textView.setBackgroundResource(R.drawable.indicator0)
            ll.addView(textView)
        }
        this.ll.addView(ll)
        return ll
    }

    private fun prepareIntros(): ArrayList<Intro> {
        val introes = ArrayList<Intro>()

        introes.add(
            Intro(
                assets.open("3.json"),
                "Say Hello to",
                "Global Top-Up",
                "Send mobile top-up more than 500 networks",
                "in over 140 countries"
            )
        )
        introes.add(
            Intro(
                assets.open("1.json"),
                "Safe, Trusted$",
                "Fully Secure",
                "Send mobile top-up more than 500 networks",
                "in over 140 countries"
            )
        )
        introes.add(
            Intro(
                assets.open("2.json"),
                "Easy to Use",
                "",
                "Send mobile top-up more than 500 networks",
                "in over 140 countries"
            )
        )

        return introes
    }
}