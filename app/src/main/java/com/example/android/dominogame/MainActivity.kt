package com.example.android.dominogame

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var scoreA = 0
    var scoreB = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        scoreA = sharedPreferences.getInt("SCRA", 0)
        scoreB = sharedPreferences.getInt("SCRB", 0)

        scoreAText.setText(scoreA.toString())
        scoreBText.setText(scoreB.toString())


        if (scoreA > 151) {
            winA.text = getString(R.string.Win)
        } else
            winA.text = ""
        if (scoreB > 151) {
            winB.text = getString(R.string.Win)
        } else
            winB.text = ""





        addButton.setOnClickListener {
            result()
        }

        resetButton.setOnClickListener {

            reset()
        }
    }


    fun result() {


        val scoreString: String = inputScoreEditText.text.toString()
        val scoreAString: String = scoreAText.text.toString()
        val scoreBString: String = scoreBText.text.toString()

        if (scoreString.isNotEmpty()) {
            var score = scoreString.toInt()
            scoreA = scoreAString.toInt()
            scoreB = scoreBString.toInt()
            when (teamRadioGroup.checkedRadioButtonId) {
                teamARadioButton.id -> {
                    scoreA += score

                }
                teamBRadioButton.id -> {
                    scoreB += score

                }
            }

            scoreAText.text = scoreA.toString()
            scoreBText.text = scoreB.toString()

        }

        win()

    }


    fun reset() {

        scoreA = 0
        scoreB = 0
        scoreAText.text = "0"
        scoreBText.text = "0"
        win()
    }


    fun win() {

        val scoreAString: String = scoreAText.text.toString()
        val scoreBString: String = scoreBText.text.toString()
        scoreA = scoreAString.toInt()
        scoreB = scoreBString.toInt()
        if (scoreA > 151) {
            winA.text = getString(R.string.Win)
        } else
            winA.text = ""
        if (scoreB > 151) {
            winB.text = getString(R.string.Win)
        } else
            winB.text = ""
    }


    override fun onStop() {

        super.onStop()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = sharedPreferences.edit()

        editor.putInt("SCRA", scoreA)
        editor.putInt("SCRB", scoreB)

        editor.apply()
    }
}


// scoreA for math
// scoreAA 4 saving
// scoreAAA 4 reading from storage