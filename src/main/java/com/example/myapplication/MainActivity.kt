package com.example.myapplication

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding
import java.text.DecimalFormat
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding : ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        //var toShow=""

        var _input:Long=0

        var input:Int=0

        lateinit var timer: CountDownTimer

        binding.start.setOnClickListener{
            input = binding.editTextNumber.text.toString().toInt()
            _input = input.toLong()*1000

            binding.start.visibility=View.INVISIBLE

            binding.editTextNumber.visibility= View.INVISIBLE

            timer = object : CountDownTimer(_input,1000){
                override fun onTick(millisUntilFinished: Long) {
                    val f: NumberFormat = DecimalFormat("00")
                    val min = millisUntilFinished / 60000 % 60
                    val sec = millisUntilFinished / 1000 % 60

                    println(f.format(min) + ":" + f.format(sec))

                    input--

                    binding.time.setText((f.format(min) + ":" + f.format(sec)))

                }

                override fun onFinish() {
                    Toast.makeText(this@MainActivity,"DONE",Toast.LENGTH_SHORT).show()
                    binding.editTextNumber.visibility= View.VISIBLE
                    binding.start.visibility=View.VISIBLE

                }
            }
            timer.start()
        }

        binding.stop.setOnClickListener{
            binding.editTextNumber.visibility=View.VISIBLE
            binding.editTextNumber.setText("$input")
            binding.start.visibility=View.VISIBLE
            timer.cancel()
        }
    }
}