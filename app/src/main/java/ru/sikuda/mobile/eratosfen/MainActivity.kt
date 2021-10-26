package ru.sikuda.mobile.eratosfen

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
            showTextResult(it)
        }

        binding.button.setOnClickListener {

            val listener = DialogInterface.OnClickListener { _, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> viewModel.RunCalcSync()
                    DialogInterface.BUTTON_NEGATIVE,
                    DialogInterface.BUTTON_NEUTRAL -> {
                        Toast.makeText(this, "May be next time", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            val dialog = AlertDialog.Builder(this)
                .setCancelable(true)
                .setIcon(R.mipmap.ic_launcher_round)
                .setTitle(R.string.result_title)
                .setMessage(R.string.result_message)
                //.setNegativeButton(R.string.action_no, listener)
                .setPositiveButton(R.string.action_yes, listener)
                .setNeutralButton(R.string.action_ignore, listener)
                .setOnDismissListener {
                    Log.d(TAG, "Dialog dismissed")
                }
                .create()

            dialog.show()

        }

        binding.button2.setOnClickListener {
            viewModel.RunCalc()
        }

        binding.button3.setOnClickListener {
            binding.button3.text = "Clickme-" + random().toString()
        }
    }

    fun showTextResult(text: String)  = with(binding) {
        //val textView = findViewById<TextView>(R.id.textResult)
        binding.textResult.text = "Time ms:" + text
    }

}

