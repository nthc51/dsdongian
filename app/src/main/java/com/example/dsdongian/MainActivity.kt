package com.example.dsdongian

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Liên kết với các thành phần giao diện
        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        val radioEven = findViewById<RadioButton>(R.id.radioEven)
        val radioOdd = findViewById<RadioButton>(R.id.radioOdd)
        val radioSquare = findViewById<RadioButton>(R.id.radioSquare)
        val buttonShow = findViewById<Button>(R.id.buttonShow)
        val listViewResult = findViewById<ListView>(R.id.listViewResult)
        val textViewError = findViewById<TextView>(R.id.textViewError)

        buttonShow.setOnClickListener {
            // Lấy dữ liệu từ EditText và kiểm tra
            val inputText = editTextNumber.text.toString()
            if (inputText.isEmpty()) {
                textViewError.text = "Vui lòng nhập một số nguyên dương!"
                return@setOnClickListener
            }

            val n = inputText.toIntOrNull()
            if (n == null || n <= 0) {
                textViewError.text = "Số nhập vào phải là số nguyên dương!"
                return@setOnClickListener
            }

            textViewError.text = "" // Xóa thông báo lỗi nếu có
            val results = when {
                radioEven.isChecked -> getEvenNumbers(n)
                radioOdd.isChecked -> getOddNumbers(n)
                radioSquare.isChecked -> getSquareNumbers(n)
                else -> listOf("Vui lòng chọn một loại số!")
            }

            // Hiển thị kết quả trong ListView
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, results)
            listViewResult.adapter = adapter
        }
    }

    private fun getEvenNumbers(n: Int): List<String> {
        return (0..n).filter { it % 2 == 0 }.map { it.toString() }
    }

    private fun getOddNumbers(n: Int): List<String> {
        return (1..n).filter { it % 2 != 0 }.map { it.toString() }
    }

    private fun getSquareNumbers(n: Int): List<String> {
        val results = mutableListOf<String>()
        var i = 0
        while (i * i <= n) {
            results.add((i * i).toString())
            i++
        }
        return results
    }
}
