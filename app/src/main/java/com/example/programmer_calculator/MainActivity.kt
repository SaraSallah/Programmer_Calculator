package com.example.programmer_calculator

import android.annotation.SuppressLint
import android.app.appsearch.AppSearchSchema.StringPropertyConfig
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import java.security.cert.Extension

class MainActivity : AppCompatActivity() {
    private lateinit var value: String
    private lateinit var valuebin: String

    private lateinit var input_descimal: EditText
    private lateinit var input_binary: EditText
    private lateinit var input_octal: EditText
    private lateinit var input_hex: EditText
    private var onFocusChangeListener: View.OnFocusChangeListener? = null
    private var focusViewId = 0
    private var textWatcher: TextWatcher? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         input_descimal = findViewById<EditText>(R.id.input_Decimal)
         input_binary = findViewById<EditText>(R.id.input_Binary)
         input_octal = findViewById<EditText>(R.id.input_Octal)
        input_hex = findViewById<EditText>(R.id.input_Hex)
         var bt_clear = findViewById<Button>(R.id.clear)
        bt_clear.setOnClickListener {
            clearNumbers()
        }
        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                value = (findViewById<View>(focusViewId) as EditText).text.toString().trim { it <= ' ' }
                if (value.isNotEmpty()) {
                    convertNumber()
                }
            }

            override fun afterTextChanged(s: Editable) {}
        }
        onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                //Toast.makeText(getApplicationContext(),"Focus IN" , Toast.LENGTH_LONG).show();
                focusViewId = v.id
                (findViewById<View>(focusViewId) as EditText).addTextChangedListener(textWatcher)
            }
            else {
                (findViewById<View>(focusViewId) as EditText).removeTextChangedListener(textWatcher)}
//
        }
        input_descimal.onFocusChangeListener = onFocusChangeListener
        input_binary.onFocusChangeListener = onFocusChangeListener
        input_octal.onFocusChangeListener = onFocusChangeListener
        input_hex.onFocusChangeListener = onFocusChangeListener
    }

    private fun clearNumbers() {
        input_binary.setText("")
        input_descimal.setText("")
        input_octal.setText("")
        input_hex.setText("")
    }
    private fun convertNumber() {
        try {
            var num: Long = 0
            when (focusViewId) {
                R.id.input_Decimal -> {
                    num = value.toLong()
                    input_binary.setText(java.lang.Long.toBinaryString(num).toString())
                    input_octal.setText(java.lang.Long.toOctalString(num).toString())
                    input_hex.setText(java.lang.Long.toHexString(num).toString())
                }
                R.id.input_Binary -> {
                    num = value.toLong(2)
                    input_descimal.setText(num.toString())
                    input_octal.setText(java.lang.Long.toOctalString(num).toString())
                    input_hex.setText(java.lang.Long.toHexString(num).toString())
                }
                R.id.input_Octal -> {
                    num = value.toLong(8)
                    input_descimal.setText(num.toString())
                    input_binary.setText(java.lang.Long.toBinaryString(num).toString())
                    input_hex.setText(java.lang.Long.toHexString(num).toString())
                }
                R.id.input_Hex -> {
                    num = value.toLong(16)
                    input_descimal.setText(num.toString())
                    input_octal.setText(java.lang.Long.toOctalString(num).toString())
                    input_binary.setText(java.lang.Long.toBinaryString(num).toString())
                }
            }
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}