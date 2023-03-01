package com.eypancakir.kotlincatchthesabri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.eypancakir.kotlincatchthesabri.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var score = 0
    var imageArray = ArrayList<ImageView>()
    var handler = Handler()
    var runnable = Runnable {  }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)

        hideImages()

        binding.imageView.visibility = View.INVISIBLE

        //CountDown Timer
        object : CountDownTimer(15500, 1000) {
            override fun onFinish() {

                handler.removeCallbacks(runnable)
                for(image in imageArray){
                    image.visibility = View.INVISIBLE
                }

                binding.timeText.text = "Time: 0"
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Restart the game")
                alert.setPositiveButton("YES") { dialog, which ->
                    //Restart
                    val intent = intent
                    finish()
                    startActivity(intent)
                }

                alert.setNegativeButton("NO") { dialog, which ->
                    Toast.makeText(this@MainActivity, "Game Over", Toast.LENGTH_SHORT).show()
                }
                alert.show()
            }
                override fun onTick(millisUntilFinished: Long) {
                    binding.timeText.text = "Time:" + millisUntilFinished / 1000
                }
        }.start()

        }

    fun hideImages(){

        runnable = object : Runnable{
            override fun run() {
                for(image in imageArray){
                    image.visibility = View.INVISIBLE
                }
                val random = Random
                val randomIndex = random.nextInt(9)
                println(randomIndex)
                imageArray[randomIndex].visibility = View.VISIBLE

                handler.postDelayed(runnable, 500)
            }
        }
        handler.post(runnable)


    }
    fun increaseScore(view : View){
        score++
        binding.scoreText.text ="Score: $score"
    }

}