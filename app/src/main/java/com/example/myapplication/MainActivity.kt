package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onSolveClick(view: View) {
        if (expectedANumber.text.isEmpty() ||
            expectedBNumber.text.isEmpty() ||
            expectedCNumber.text.isEmpty() ||
            expectedDNumber.text.isEmpty())
            return

        val checkClassInput: (String) -> Boolean = {
            !(it != "0" && it != "1")
        }

        if (!(checkClassInput(expectedANumber.text.toString()) &&
              checkClassInput(expectedBNumber.text.toString()) &&
              checkClassInput(expectedCNumber.text.toString()) &&
              checkClassInput(expectedDNumber.text.toString())))
            return

        if (iterationsNumber.text.isEmpty() || iterationsNumber.text.toString().toInt() <= 0)
            return

        if (thresholdNumber.text.isEmpty() || rateNumber.text.isEmpty())
            return

        val bool: (String) -> Boolean = {
            it != "0"
        }

        val data = listOf(
            Pair(listOf(0.0, 6.0), bool(expectedANumber.text.toString())),
            Pair(listOf(1.0, 5.0), bool(expectedBNumber.text.toString())),
            Pair(listOf(3.0, 3.0), bool(expectedCNumber.text.toString())),
            Pair(listOf(2.0, 4.0), bool(expectedDNumber.text.toString()))
        )
        val iterations = iterationsNumber.text.toString().toInt()
        val rate = rateNumber.text.toString().toDouble()
        val threshold = thresholdNumber.text.toString().toDouble()
        val answer = solve(data, rate, iterations, threshold)

        actualAView.text = answer.first[0].toString()
        actualBView.text = answer.first[1].toString()
        actualCView.text = answer.first[2].toString()
        actualDView.text = answer.first[3].toString()

        weightsView.text = answer.second.toString()
    }
}