package ru.sikuda.mobile.eratosfen

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.*
import androidx.lifecycle.ViewModelProvider
import ru.sikuda.mobile.eratosfen.databinding.ActivityMainBinding
import java.lang.Math.random


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val viewAll = binding.root
        setContentView(viewAll)

        // binding = setContentView(this)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.settext.observe(this) {
            showText(it)
        }

        binding.button.setOnClickListener {
            viewModel.RunCalcSync()
        }

        binding.button2.setOnClickListener {
            viewModel.RunCalc()
        }

        binding.button3.setOnClickListener {
            binding.button3.text = "Clickme-" + random().toString()
        }
    }

    private fun showText(text: String)  = with(binding) {
        //val textView = findViewById<TextView>(R.id.textResult)
        binding.textResult.text = "Time ms:" + text
    }

//    fun onCountClick(view: android.view.View) {
//        //binding.textResult.setText("test1".toCharArray(), 1 , 100)
//
//        val diff = Calculate()
//
//        val textView = findViewById<TextView>(R.id.textResult)
//        textView.text = "Time ms:" + diff.toString()
//
//    }


//    fun Calculate(): Double {
//
//        val n: Int = 50_000_000
//        //val n: Int = 50_000
//        val array: Array<Int> = Array(n+1) { 1 }
//        array[0] = 0
//        array[1] = 0
//
//        println("Start")
//        val timeBegin = System.currentTimeMillis()
//        var i: Int = 2
//        while ( i <= n ) {
//            if (array[i] == 1) {
//                val sq: Long = i.toLong() * i
//                if (sq <= n) {
//                    var m: Int = sq.toInt()
//                    while (m <= n) {
//                        array[m] = 0
//                        m += i.toInt()
//                    }
//                }
//            }
//            i += 1
//        }
//        val timeEnd = System.currentTimeMillis()
//        val diff: Double = (timeEnd.toDouble() - timeBegin) / 1000
//
//        return diff
//        //textView =
//        //binding.textResult.setText("test2")
//    }

}