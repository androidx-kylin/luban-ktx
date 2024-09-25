package androidx.kylin.luban.example

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.kylin.luban.CompressResult
import androidx.kylin.luban.Luban
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.io.File

class MainActivity : AppCompatActivity() {

    private val testFile: File by lazy {
        File(applicationContext.externalCacheDir, "test.jpg").also {
            it.parentFile?.mkdirs()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btn = findViewById<Button>(R.id.btn_compass)
        btn.setOnClickListener {
            val context = it.context
            if (!testFile.exists()) {
                Toast.makeText(context, "请先将测试图片放在这里：${testFile}", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            lifecycleScope.launch {
                val result = Luban(context)
                    .load(testFile)
                    .ignoreSize(100)
                    .quality(60)
                    .keepAlpha(false)
                    .keepSize(false)
                    .single()
                val message = when (result) {
                    is CompressResult.Success -> {
                        val file = result.file!!
                        """
                            压缩成功: ${Luban.kbOf(testFile)}kb > ${Luban.kbOf(file)}kb,
                            (${Luban.sizeOf(testFile)} > ${Luban.sizeOf(file)}),
                            $file
                        """.trimIndent()
                    }

                    is CompressResult.Error -> "压缩失败：${result}"
                    else -> "其他错误"
                }
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                Log.d("luban", message)
            }
        }
    }
}