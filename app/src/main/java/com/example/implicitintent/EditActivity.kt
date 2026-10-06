package com.example.implicitintent // Thay đúng package name của dự án bạn

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.implicitintent.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Nhận tên hiện tại gửi từ MainActivity sang
        val currentName = intent.getStringExtra("EXTRA_NAME") ?: ""

        // 2. Điền sẵn tên cũ vào EditText (Nếu không phải giá trị mặc định)
        if (currentName != "Chưa có thông tin") {
            binding.edtName.setText(currentName)
        }

        // 3. Xử lý khi nhấn nút "Lưu & Quay lại"
        binding.btnSave.setOnClickListener {
            val newName = binding.edtName.text.toString().trim()

            if (newName.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập họ và tên!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Đóng gói tên mới vào Intent phản hồi
            val resultIntent = Intent().apply {
                putExtra("EXTRA_NEW_NAME", newName)
            }

            // Gán kết quả RESULT_OK và đóng màn hình
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}