package com.eru.month6hw2

import android.content.Intent
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.eru.month6hw2.base.BaseActivity
import com.eru.month6hw2.databinding.ActivitySecondBinding

class SecondActivity : BaseActivity<ActivitySecondBinding>() {

    private var launcher: ActivityResultLauncher<Intent>? = null

    override fun inflateViewBinding(): ActivitySecondBinding {
        return ActivitySecondBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == 2){
                val text = result.data?.getStringExtra(MainActivity.KEY_FOR_INTENT)
                binding.etText.setText(text)
            }
        }
    }

    override fun initListener() {
        super.initListener()

        binding.btnEnter.setOnClickListener {
            if (binding.etText.text.toString().isNotEmpty()){
                val intent = Intent()
                intent.putExtra(MainActivity.KEY_FOR_SECOND_INTENT, binding.etText.text.toString())
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "EditText не может быть пустым", Toast.LENGTH_SHORT).show()
            }
        }
    }
}