package dev.matyaqubov.modul05lesson01.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.matyaqubov.modul05lesson01.R


class FirstFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =inflater.inflate(R.layout.fragment_first, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {

    }


}