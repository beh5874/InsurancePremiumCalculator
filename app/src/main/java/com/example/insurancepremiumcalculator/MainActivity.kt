package com.example.insurancepremiumcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var myData: MyDataModel
    val age = spinnerAge.selectedItemPosition;
    val radMale = radioButtonMale;
    val radFemale = radioButtonFemale;
    val txtViewPremium = textViewPremium;
    val chkSmoker = checkBoxSmoker;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        myData = ViewModelProviders.of(this).get(MyDataModel::class.java)

        val btn_click_me = findViewById(R.id.buttonCalculate) as Button
        val btnReset = findViewById(R.id.buttonReset) as Button

        display();

        btn_click_me.setOnClickListener {
            myData.totalPremium = getPremium()
            display();
        }

        btnReset.setOnClickListener(){
            spinnerAge.setSelection(0);
            radioGroupGender.clearCheck()
            txtViewPremium.setText("")
            chkSmoker.setChecked(false)

        }

    }

    fun display(){
        if(myData.totalPremium != 0.00)
        txtViewPremium.text = myData.totalPremium.toString();
    }



    fun getPremium():Double{

        return when(spinnerAge.selectedItemPosition){
            0 -> 60.00
            1 -> 70.00 +
                    (if(radMale.isChecked) 50.00 else 0.00) +
                    (if(chkSmoker.isChecked) 100.00 else 0.00)
            2 -> 90 +
                    (if(radMale.isChecked) 100.00 else 0.00) +
                    (if(chkSmoker.isChecked) 150.00 else 0.00)
            3 -> 120 +
                    (if(radMale.isChecked) 150.00 else 0.00) +
                    (if(chkSmoker.isChecked) 200.00 else 0.00)
            4 -> 150 +
                    (if(radMale.isChecked) 200.00 else 0.00) +
                    (if(chkSmoker.isChecked) 250.00 else 0.00)
            5 -> 150 +
                    (if(radMale.isChecked) 200.00 else 0.00) +
                    (if(chkSmoker.isChecked) 300.00 else 0.00)
            else -> 150.00
        }
    }
}
