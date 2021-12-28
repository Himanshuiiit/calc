package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastNumeric = false
    var lastDot = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onDigit(view: View){
        tvInput.append((view as Button).text)
        lastNumeric = true
    }
    fun onClear(view : View){
        tvInput.text = ""
        lastNumeric = false
        lastDot = false
    }
    fun onDot(view : View){
        if(lastNumeric && !lastDot){
            tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }
    fun onOperator(view : View){
        if(lastNumeric && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }
    private fun isOperatorAdded(value:String): Boolean{
        return if(value.startsWith("-")) false
        else value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
    }
    fun onEqual(view : View){
        if(lastNumeric){
            var tvValue = tvInput.text.toString()
            var prefix = ""
            try{
                if(tvValue.startsWith("-")){
                    tvValue = tvValue.substring(1)
                    prefix = "-"
                }
                if(tvValue.contains("-")){
                    val split = tvValue.split("-")

                    var one = split[0]
                    var two = split[1]

                    if(!prefix.isEmpty()) one = prefix + one

                    tvInput.text = (one.toDouble() - two.toDouble()).toString()
                }else if(tvValue.contains("+")){
                    val split = tvValue.split("+")

                    var one = split[0]
                    var two = split[1]

                    if(!prefix.isEmpty()) one = prefix + one

                    tvInput.text = (one.toDouble() + two.toDouble()).toString()
                }else if(tvValue.contains("/")){
                    val split = tvValue.split("/")

                    var one = split[0]
                    var two = split[1]

                    if(!prefix.isEmpty()) one = prefix + one

                    tvInput.text = (one.toDouble() / two.toDouble()).toString()
                }else if(tvValue.contains("*")){
                    val split = tvValue.split("*")

                    var one = split[0]
                    var two = split[1]

                    if(!prefix.isEmpty()) one = prefix + one

                    tvInput.text = (one.toDouble() * two.toDouble()).toString()
                }
            }catch (e: ArithmeticException){

            }
        }
    }
}