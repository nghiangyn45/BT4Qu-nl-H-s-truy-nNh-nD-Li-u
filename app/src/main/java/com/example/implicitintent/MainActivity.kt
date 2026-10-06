package com.example.implicitintent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.implicitintent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentName: String = "Chưa có thông tin"

    // 1. Đăng ký Launcher nhận kết quả trả về từ EditActivity
    private val editProfileLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val newName = result.data?.getStringExtra("EXTRA_NEW_NAME")

                // isNullOrBlank() giúp Kotlin tự động Smart Cast từ String? sang String chuẩn
                if (!newName.isNullOrBlank()) {
                    currentName = newName
                    binding.tvName.text = currentName
                    Toast.makeText(this, "Cập nhật họ tên thành công!", Toast.LENGTH_SHORT).show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hiển thị giá trị họ tên ban đầu
        binding.tvName.text = currentName

        // 2. Sự kiện khi nhấn nút "Chỉnh sửa thông tin"
        binding.btnEdit.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java).apply {
                putExtra("EXTRA_NAME", currentName)
            }
            editProfileLauncher.launch(intent)
        }
    }
}