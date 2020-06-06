package c.bikiprasad13.firstkotlinapp

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var itemlist = arrayListOf<String>()
        var adapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_multiple_choice
            , itemlist)

        add.setOnClickListener {
            val input = editText?.text.toString().trim()
            if (input.isNullOrBlank()) {
                //Your code for blank edittext
                android.widget.Toast.makeText(this, "Empty Text Field", android.widget.Toast.LENGTH_SHORT).show()
            }
            else{
                itemlist.add(editText.text.toString())
                listView.adapter =  adapter
                adapter.notifyDataSetChanged()

                editText.text.clear()

            }

        }

        clear.setOnClickListener {

            itemlist.clear()
            android.widget.Toast.makeText(this, "Cleared All Task", android.widget.Toast.LENGTH_SHORT).show()
            adapter.notifyDataSetChanged()
        }

        listView.setOnItemClickListener { adapterView, view, i, l ->
            android.widget.Toast.makeText(this, "Item selected: "+itemlist.get(i), android.widget.Toast.LENGTH_SHORT).show()
            editText.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }

        delete.setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item))
                {
                    adapter.remove(itemlist.get(item))
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }
    }
    }

