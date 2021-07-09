package com.tatvasoftdemo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

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
            for (i in 2..5) {
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
        rvGrid.layoutManager = GridLayoutManager(this, gridSize)
        val movieListAdapter = GridAdapter()
        rvGrid.adapter = movieListAdapter
        movieListAdapter.setGridList(getDummyData(gridSize))
    }

    private fun getDummyData(gridSize: Int): List<GridModel> {
        var list = ArrayList<GridModel>()
        for (i in 1..(gridSize * gridSize)) {
            list.add(GridModel("w"))
        }

        return list
    }
}