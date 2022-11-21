package com.example.tictactoe

import android.content.DialogInterface
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var b1 :  Button
    lateinit var b2 :  Button
    lateinit var b3 :  Button
    lateinit var b4 :  Button
    lateinit var b5 :  Button
    lateinit var b6 :  Button
    lateinit var b7 :  Button
    lateinit var b8 :  Button
    lateinit var b9:   Button
    lateinit var pirveli:TextView
    lateinit var meore:TextView

    lateinit var tv : TextView

    var erti=0
    var ori=0


    var player1 = 0
    var player2 = 1
    var activePlayer = player1
    lateinit var filledPos : IntArray

    var gameActive = true



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        filledPos= intArrayOf(0,-1,-1,-1,-1,-1,-1,-1,-1,-1)




        tv = findViewById(R.id.player)
        b1 = findViewById(R.id.b1)
        b2 = findViewById(R.id.b2)
        b3 = findViewById(R.id.b3)
        b4 = findViewById(R.id.b4)
        b5 = findViewById(R.id.b5)
        b6 = findViewById(R.id.b6)
        b7 = findViewById(R.id.b7)
        b8 = findViewById(R.id.b8)
        b9 = findViewById(R.id.b9)


        pirveli=findViewById(R.id.pirveli)
        meore=findViewById(R.id.meore)

        b1.setOnClickListener(this)
        b2.setOnClickListener(this)
        b3.setOnClickListener(this)
        b4.setOnClickListener(this)
        b5.setOnClickListener(this)
        b6.setOnClickListener(this)
        b7.setOnClickListener(this)
        b8.setOnClickListener(this)
        b9.setOnClickListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClick(v: View?) {

        if(! gameActive)
            return
        var btnClicked = findViewById<Button>(v!!.id)
        var btnClickedTag = Integer.parseInt(btnClicked.tag.toString())


        if(filledPos[btnClickedTag]!=-1)
            return


        filledPos[btnClickedTag]=activePlayer
        if(activePlayer == player1){
            btnClicked.setText("0")
            activePlayer = player2
            tv.setText("Player-2 Turn")
            btnClicked.setTextColor(Color.BLACK)
            btnClicked.backgroundTintList = getColorStateList(R.color.pink)
        }else{
            btnClicked.setText("X")
            activePlayer = player1
            tv.setText("Player-1 Turn")
            btnClicked.setTextColor(Color.BLACK)
            btnClicked.backgroundTintList = getColorStateList(R.color.red)
        }

        checkForWin()







    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkForWin() {
        var winPos = arrayOf(intArrayOf(1,2,3),intArrayOf(4,5,6),intArrayOf(7,8,9),intArrayOf(1,4,7,),
            intArrayOf(2,5,8),intArrayOf(3,6,9),intArrayOf(1,5,9),intArrayOf(3,5,7))


        for(i in 0 until winPos.size){
            var val0 = winPos[i][0]
            var val1 = winPos[i][1]
            var val2 = winPos[i][2]

            if(filledPos[val0] == filledPos[val1] && filledPos[val1] == filledPos[val2]){
                if(filledPos[val0]!= -1){
                    gameActive = false
                    if(filledPos[val0] == player1){
                        erti++
                        showMessage("Player-1 is winner")
                    }else{
                        ori++
                        showMessage("Player-2 is winner")
                    }
                    return
                }


            }
        }
        // To check for draw
        var count=0
        for(i in 0 until filledPos.size){
            if(filledPos[i]==-1){
                count++
            }
        }
        if(count==0){
            showMessage("It's Draw")
            return
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun showMessage(s: String) {
        AlertDialog.Builder(this)
            .setMessage(s)
            .setTitle("Tic Tac Toe")
            .setPositiveButton("good", DialogInterface.OnClickListener { dialog, which ->

            })
            .show()
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun reset(view: View) {
        filledPos = intArrayOf(0,-1,-1,-1,-1,-1,-1,-1,-1,-1)
        activePlayer = player1
        gameActive = true
        tv.setText("Player-1 Turn")
        b1.setText("")
        b2.setText("")
        b3.setText("")
        b4.setText("")
        b5.setText("")
        b6.setText("")
        b7.setText("")
        b8.setText("")
        b9.setText("")
        b1.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        b2.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        b3.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        b4.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        b5.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        b6.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        b7.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        b8.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        b9.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        pirveli.text=erti.toString()
        meore.text=ori.toString()
    }
}