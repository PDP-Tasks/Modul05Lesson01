package dev.matyaqubov.modul05lesson01.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import dev.matyaqubov.modul05lesson01.R
import dev.matyaqubov.modul05lesson01.adapter.ViewPagerAdapter
import dev.matyaqubov.modul05lesson01.fragment.FirstFragment
import dev.matyaqubov.modul05lesson01.fragment.SecondFragment
import dev.matyaqubov.modul05lesson01.fragment.ThirdFragment

class FirstActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {
    private lateinit var thirdFragment: ThirdFragment
    private lateinit var tv_skip:TextView
    private lateinit var button:Button
    private lateinit var viewpagerAdapter: ViewPagerAdapter
    private lateinit var viewpager: ViewPager
    private lateinit var dots: WormDotsIndicator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        initViews()
    }

    private fun initViews() {
        viewpager = findViewById(R.id.view_pager)
        dots = findViewById(R.id.dots)
        tv_skip = findViewById(R.id.tv_skip)
        button = findViewById(R.id.btn_start)
        viewpagerAdapter = ViewPagerAdapter(supportFragmentManager)

        setupViewPager()

    }

    private fun setupViewPager() {
        addFragment()
        viewpager.adapter = viewpagerAdapter
        viewpager.setOnPageChangeListener(this)
        dots.setViewPager(viewpager)
    }

    private fun addFragment() {
        thirdFragment = ThirdFragment()
        viewpagerAdapter.add(FirstFragment())
        viewpagerAdapter.add(SecondFragment())
        viewpagerAdapter.add(thirdFragment)

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        Log.d("onPageScrolled", "onPageScrolled: $position")
    }

    override fun onPageSelected(position: Int) {

        Log.d("position", "onPageSelected: $position")
        if (position == 2) {
            tv_skip.visibility=View.GONE
            button.visibility=View.VISIBLE
        } else {
            tv_skip.visibility=View.VISIBLE
            button.visibility=View.GONE
        }
    }

    override fun onPageScrollStateChanged(state: Int) {

        Log.d("onPageScrollState", "onPageScrollStateChanged: $state")
    }


}