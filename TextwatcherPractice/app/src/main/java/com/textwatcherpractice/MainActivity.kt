package com.textwatcherpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {
    lateinit var adapter: ArrayAdapter<String>
    val countries = mutableListOf<String>(
        "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra",
        "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina",
        "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan",
        "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initLayout()
    }

    private fun initLayout() {
        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)

        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,countries)
        autoCompleteTextView.setAdapter(adapter)
        autoCompleteTextView.setOnItemClickListener{adapterView,view,i,l->
            val item = adapterView.getItemAtPosition(i).toString()
            Toast.makeText(this,item,Toast.LENGTH_SHORT).show()
        }

        val multiAutoView = findViewById<MultiAutoCompleteTextView>(R.id.multiAutoCompleteTextView)
        multiAutoView.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
        multiAutoView.setAdapter(adapter)

        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)

        editText.addTextChangedListener {
            val str = it.toString()
            button.isEnabled = str.isNotEmpty()
        }

      /*  editText.addTextChangedListener(
            afterTextChanged = {
                val str = it.toString()
                button.isEnabled = str.isNotEmpty()
            }
        )*/

       /* editText.addTextChangedListener(object:TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(p0: Editable?) {
                // TODO("Not yet implemented")
                val str = p0.toString()
                button.isEnabled = str.isNotEmpty()
            }
        })*/

        button.setOnClickListener{
            adapter.add(editText.text.toString())
            adapter.notifyDataSetChanged()
            editText.text.clear()
        }
    }
}