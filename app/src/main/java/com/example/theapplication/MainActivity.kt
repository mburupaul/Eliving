package com.example.theapplication
//    splash screen     https://m-ify-education.blogspot.com/2022/02/splash-screen-with-motion-layout.html
//                      https://www.youtube.com/watch?v=icmQOZp4p6I&t=18s

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    private var cardOne : CardView ?= null
    var cardTwo : CardView ?= null

    var cardFour : CardView ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardOne = findViewById(R.id.card_a)
        cardTwo = findViewById(R.id.card_b)

        cardFour= findViewById(R.id.card_d)

        cardOne!!.setOnClickListener {

            val goToAdd = Intent(applicationContext, AddActivity::class.java)
            startActivity(goToAdd)

        }

        cardTwo!!.setOnClickListener {

            startActivity(Intent(applicationContext,ViewActivity::class.java))

        }
        cardFour!!.setOnClickListener {

            Toast.makeText(this,"More Actions" +
                    " Activity",Toast.LENGTH_SHORT).show()

        }


    }
}