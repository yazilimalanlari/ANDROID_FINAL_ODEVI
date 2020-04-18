package com.example.dictionary

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.dictionary.database.DatabaseAccess
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var items = ArrayList<String>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val databaseAccess = DatabaseAccess.getInstance(applicationContext)
        databaseAccess.open()



        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items)

        listView.setAdapter(adapter)



        inputFind.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int){
                val data = databaseAccess.getWords(s.toString()) as ArrayList<String>
                items.clear()
                for(i in 0..data.size-1){
                    items.add(data[i])
                }
            }
        })

    }



}
