package ru.sikuda.mobile.eratosfen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import ru.sikuda.mobile.eratosfen.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        setContentView(R.layout.activity_main)
    }

    fun onCountClick(view: android.view.View) {
        //binding.textResult.setText("test1".toCharArray(), 1 , 100)

        val diff = Calculate()

        val textView = findViewById<TextView>(R.id.textResult)
        textView.text = "Time ms:" + diff.toString()

    }


    fun Calculate(): Double {

        val n: Int = 50_000_000
        //val n: Int = 50_000
        val array: Array<Int> = Array(n+1) { 1 }
        array[0] = 0
        array[1] = 0

        println("Start")
        val timeBegin = System.currentTimeMillis()
        var i: Int = 2
        while ( i <= n ) {
            if (array[i] == 1) {
                val sq: Long = i.toLong() * i
                if (sq <= n) {
                    var m: Int = sq.toInt()
                    while (m <= n) {
                        array[m] = 0
                        m += i.toInt()
                    }
                }
            }
            i += 1
        }
        val timeEnd = System.currentTimeMillis()
        val diff: Double = (timeEnd.toDouble() - timeBegin) / 1000

        return diff
        //textView =
        //binding.textResult.setText("test2")
    }

}