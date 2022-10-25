package com.eru.month6hw2

import android.content.Intent
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.eru.month6hw2.base.BaseActivity
import com.eru.month6hw2.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var launcher: ActivityResultLauncher<Intent>? = null

    companion object {
        const val KEY_FOR_INTENT = "Text"
        const val KEY_FOR_SECOND_INTENT = "Text2"
    }

    override fun inflateViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()

        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val text = result.data?.getStringExtra(KEY_FOR_SECOND_INTENT)
                    binding.etText.setText(text)
                }
            }
    }

    override fun initListener() {
        super.initListener()
        binding.btnEnter.setOnClickListener {

            if (binding.etText.text.toString().isNotEmpty()) {
                val intent = Intent()
                intent.putExtra(KEY_FOR_INTENT, binding.etText.text.toString())
                setResult(2, intent)
                launcher?.launch(Intent(this, SecondActivity::class.java))
            } else {
                Toast.makeText(this, "EditText не может быть пустым", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
