package com.ruzibekov.gameofdice

import android.app.Dialog
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.ruzibekov.gameofdice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isNotRunned = true
    private var gameBall = 0
    private lateinit var successSound: MediaPlayer
    private lateinit var failedSound: MediaPlayer
    private lateinit var gameOverSound: MediaPlayer
    private lateinit var startGameSound: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        successSound = MediaPlayer.create(this, R.raw.success_sound)
        failedSound = MediaPlayer.create(this, R.raw.failed_sound)
        gameOverSound = MediaPlayer.create(this, R.raw.game_over_sound)
        startGameSound = MediaPlayer.create(this, R.raw.start_game_sound)
        startGame()

        binding.ivMainSelectedDice.setOnClickListener {
            if (binding.ivMainExampleDice.tag == binding.ivMainSelectedDice.tag) {
                gameBall += 10
                successSound.start()
                binding.ivMainExampleDice.apply {
                    val newTag = getRandomDiceImage()
                    setImageResource(newTag)
                    tag = newTag
                }
            } else {
                gameBall -= 10
                failedSound.start()
            }
            if (gameBall < 0)
                stopGame()
            else
                binding.tvMainBall.text = "Ball: ${gameBall}"
        }

        binding.layoutMainStop.setOnClickListener { stopGame() }
    }

    private fun stopGame() {
        gameOverSound.start()
        runGame.cancel()
        gameBall = 0
        binding.tvMainBall.text = "Ball: ${gameBall}"
        isNotRunned = true
        finish()
    }


    private val runGame = object : CountDownTimer(700, 10) {
        override fun onTick(millisUntilFinished: Long) {}
        override fun onFinish() {
            val newTag = getRandomDiceImage()
            if (newTag == binding.ivMainSelectedDice.tag) {
                onFinish()
            } else {
                binding.ivMainSelectedDice.apply {
                    setImageResource(newTag)
                    tag = newTag
                }
                start()
            }
        }
    }

    private fun getRandomDiceImage(): Int {
        return when ((1..6).random()) {
            1 -> R.drawable.ic_dice_1
            2 -> R.drawable.ic_dice_2
            3 -> R.drawable.ic_dice_3
            4 -> R.drawable.ic_dice_4
            5 -> R.drawable.ic_dice_5
            else -> R.drawable.ic_dice_6
        }
    }

    private fun startGame() {
        if (isNotRunned) {
            startGameSound.start()
            isNotRunned = false
            val newTag = getRandomDiceImage()
            binding.ivMainExampleDice.apply {
                setImageResource(newTag)
                tag = newTag
            }
            runGame.start()
        }
    }

    override fun onBackPressed() {}
}