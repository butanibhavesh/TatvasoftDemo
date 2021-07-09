package com.tatvasoftdemo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tatvasoftdemo.GridAdapter.CustomListener
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    lateinit var etNumber: EditText
    lateinit var btnSubmit: Button
    lateinit var rvGrid: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        etNumber = this.findViewById(R.id.etNumber)
        btnSubmit = this.findViewById(R.id.btnSubmit)
        rvGrid = this.findViewById(R.id.rvGrid)

        btnSubmit.setOnClickListener {
            val value = etNumber.text.toString()

            if (value == "") {
                Toast.makeText(this@MainActivity, "Please insert value", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intValue = value.toInt()

            var isValid = false
            var gridSize = 2
            for (i in 2..intValue) {
                if (i * i == intValue) {
                    isValid = true
                    gridSize = i
                    break
                }
            }

            if (isValid)
                createGrid(gridSize)
            else
                Toast.makeText(this@MainActivity, "Invalid value", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createGrid(gridSize: Int) {
        var list = getDummyData(gridSize)

        rvGrid.layoutManager = GridLayoutManager(this, gridSize)
        val movieListAdapter = GridAdapter()
        rvGrid.adapter = movieListAdapter
        movieListAdapter.setGridList(list, object : CustomListener {
            override fun onRedClick(position: Int) {
                if (list[position].selectedColor == "r") {
                    list[position].selectedColor = "b"
                    (rvGrid.adapter as GridAdapter).notifyItemChanged(position)
                    randomChangeColor(gridSize * gridSize - 1, list) // After click
                }
            }
        })

        randomChangeColor(gridSize * gridSize - 1, list) // First time
    }

    private fun randomChangeColor(totalItems: Int, list: List<GridModel>) {
        // val ex : IntArray = intArrayOf()
        var ex = ArrayList<Int>()
        for (i in 0..list.size - 1) {
            if (list[i].selectedColor == "b") {
                ex.add(i)
            }
        }

        if (ex.size == list.size) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Win")
            builder.setMessage("You won the game!")
            builder.setPositiveButton("OK") { dialog, which ->
                dialog.dismiss()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
            //Toast.makeText(this@MainActivity, "You won the Game!", Toast.LENGTH_SHORT).show()
            return
        }

        var rnd = Random()
        var newRedValue = getRandomWithExclusion(rnd, 0, (totalItems), ex)
        list[newRedValue].selectedColor = "r"
        (rvGrid.adapter as GridAdapter).notifyItemChanged(newRedValue)
    }

    private fun getRandomWithExclusion(
        rnd: Random,
        start: Int,
        end: Int,
        exclude: ArrayList<Int>
    ): Int {
        var random = start + rnd.nextInt(end - start + 1 - exclude.size)
        for (ex in exclude) {
            if (random < ex) {
                break
            }
            random++
        }
        return random
    }

    private fun getDummyData(gridSize: Int): List<GridModel> {
        var list = ArrayList<GridModel>()
        for (i in 1..(gridSize * gridSize)) {
            list.add(GridModel("w"))
        }

        return list
    }
}