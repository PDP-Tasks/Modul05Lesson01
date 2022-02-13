package dev.matyaqubov.modul05lesson01.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import dev.matyaqubov.modul05lesson01.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        val task1=findViewById<Button>(R.id.btn_task1)
        task1.setOnClickListener {
            val intent=Intent(this, FirstActivity::class.java)
            startActivity(intent)
        }

        val task2=findViewById<Button>(R.id.btn_task2)
        task2.setOnClickListener {
            val intent=Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        val buttonRealCase=findViewById<Button>(R.id.btn_realcase)
        buttonRealCase.setOnClickListener {
            val intent=Intent(this, RealCaseActivity::class.java)
            startActivity(intent)
        }
    }
}