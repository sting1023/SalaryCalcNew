package com.salarycalc

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val records = mutableListOf<SalaryRecord>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val etName = findViewById<EditText>(R.id.et_name)
        val etBase = findViewById<EditText>(R.id.et_base_salary)
        val etBonus = findViewById<EditText>(R.id.et_bonus)
        val etDays = findViewById<EditText>(R.id.et_work_days)
        val btnAdd = findViewById<Button>(R.id.btn_add)
        val tvResult = findViewById<TextView>(R.id.tv_result)
        val btnCalc = findViewById<Button>(R.id.btn_calc)
        
        btnAdd.setOnClickListener {
            val name = etName.text.toString()
            val base = etBase.text.toString().toDoubleOrNull() ?: 0.0
            val bonus = etBonus.text.toString().toDoubleOrNull() ?: 0.0
            val days = etDays.text.toString().toIntOrNull() ?: 22
            records.add(SalaryRecord(name, base, bonus, days))
            Toast.makeText(this, "已添加: $name", Toast.LENGTH_SHORT).show()
            etName.text.clear()
            etBase.text.clear()
            etBonus.text.clear()
        }
        
        btnCalc.setOnClickListener {
            if (records.isEmpty()) {
                tvResult.text = "请先添加工资记录"
                return@setOnClickListener
            }
            val total = records.sumOf { it.base + it.bonus }
            val avg = total / records.size
            val info = buildString {
                appendLine("共 ${records.size} 条记录")
                appendLine("总工资: ¥${"%.2f".format(total)}")
                appendLine("平均: ¥${"%.2f".format(avg)}")
                records.forEach { appendLine("${it.name}: ¥${"%.2f".format(it.base + it.bonus)}") }
            }
            tvResult.text = info
        }
    }
}

data class SalaryRecord(
    val name: String,
    val base: Double,
    val bonus: Double,
    val workDays: Int
)
