package com.example.mvpproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mvpproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {
    private var viewBinding: ActivityMainBinding? = null
    val presenter = MainPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding?.root)

        viewBinding?.button1?.setOnClickListener { presenter.counterClick(0) }
        viewBinding?.button2?.setOnClickListener { presenter.counterClick(1) }
        viewBinding?.button3?.setOnClickListener { presenter.counterClick(2) }
    }

    override fun setButtonText(index: Int, text: String) {
        when (index) {
            0 -> viewBinding?.button1?.text = text
            1 -> viewBinding?.button2?.text = text
            2 -> viewBinding?.button3?.text = text
        }
    }
}