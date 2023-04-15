package com.example.finalproject

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val diceSides = ArrayList<Int>()

        for (i in 0..99) {
            diceSides.add(i+2)
        }
        // Adding a common adapter to both team1 and team 2 spinner class
        val spinnerSides:Spinner = findViewById(R.id.spinnerSides)
        val adapter = ArrayAdapter<Int>(this, android.R.layout.simple_spinner_dropdown_item, diceSides)
        spinnerSides.adapter = adapter

        //Loading the values from Shared Preferences
        val sharedPreferences = getSharedPreferences("DiceState", Context.MODE_PRIVATE)
        val selectedItem = sharedPreferences.getInt("sides",diceSides[0]);
        spinnerSides.setSelection(selectedItem)

        // Every time user changes spinner item, it gets saved in Shared Preferences
        spinnerSides.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val sharedPreferences = getSharedPreferences("DiceState", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.apply {
                    putInt("sides", diceSides[p2])
                }.commit()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    //On click of Roll Button
    fun RollOnce(view: android.view.View) {
        val spinnerSides:Spinner = findViewById(R.id.spinnerSides)
        val numberOfSides :Int  = spinnerSides.selectedItem as Int
        val output:TextView = findViewById(R.id.txtOutput)
        val dice1 = Die(numberOfSides)
        //  print_dice_status("dice1", "before", dice1.currentSideUp);
        dice1.Roll();
        output.text = print_dice_status("Dice", "after", dice1.currentSideUp);

    }

    //On click of Roll Twice Button
    fun RollTwice(view: android.view.View) {
        val spinnerSides:Spinner = findViewById(R.id.spinnerSides)
        val numberOfSides :Int  = spinnerSides.selectedItem as Int
        val output:TextView = findViewById(R.id.txtOutput)
        val dice1 = Die(numberOfSides)
        //saving the previous output in a variable
        var outputText = print_dice_status("Dice", "First Run", dice1.currentSideUp);
        dice1.Roll();
        Log.d("OutputText: =", outputText)
        output.text = outputText
        dice1.Roll();
        Log.d("OutputText: =", outputText)
        //Conca
        outputText = outputText + "\r\n" + print_dice_status("Dice", "Second Run", dice1.currentSideUp);
        output.text = outputText
    }

    // function to print dice status
    fun print_dice_status(diceName:String, status:String, side:Int) : String {

        return "$diceName :-> $status roll, The Upside was $side";
    }

}


