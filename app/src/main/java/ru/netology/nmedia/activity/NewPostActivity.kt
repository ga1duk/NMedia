package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityNewPostBinding

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        4
        binding.etContent.setText(intent?.getStringExtra(Intent.EXTRA_TEXT))
        binding.etContent.requestFocus()
        binding.fabSave.setOnClickListener {
            val text = binding.etContent.text.toString()
            if (text.isBlank()) {
                Toast.makeText(
                    this@NewPostActivity,
                    getString(R.string.content_is_empty_toast),
                    Toast.LENGTH_SHORT
                ).show()
                setResult(RESULT_CANCELED)
            } else {
//                5
                setResult(RESULT_OK, Intent().apply {
                    putExtra(Intent.EXTRA_TEXT, text)
                })
                finish()
            }
        }
    }
}


//  1
class NewPostResultContract : ActivityResultContract<String, String?>() {
    override fun createIntent(context: Context, input: String?): Intent =
        Intent(context, NewPostActivity::class.java).apply {
            putExtra(Intent.EXTRA_TEXT, input)
        }

    override fun parseResult(resultCode: Int, intent: Intent?): String? =
        if (resultCode == Activity.RESULT_OK) {
            intent?.getStringExtra(Intent.EXTRA_TEXT)
        } else {
            null
        }
}