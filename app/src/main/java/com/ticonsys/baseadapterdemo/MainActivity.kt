package com.ticonsys.baseadapterdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ticonsys.baseadapterdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val foodAdapter by lazy {
        FoodAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        addFoodData()
    }

    private fun addFoodData() {
        foodAdapter.differ.submitList(
            listOf(
                Food(1, "Biriani"),
                Food(2, "Salad"),
                Food(3, "Rice"),
                Food(4, "Sweet")
            )
        )
    }

    private fun setupRecyclerView() {
        binding.rvFood.apply {
            adapter = foodAdapter
        }

        foodAdapter.setOnItemClickListener { view, item ->
            Toast.makeText(this, "${item.name}", Toast.LENGTH_SHORT).show()
        }
    }
}