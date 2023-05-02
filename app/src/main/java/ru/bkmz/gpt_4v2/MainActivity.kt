package ru.bkmz.gpt_4v2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.bkmz.gpt_4v2.ui.theme.Gpt4V2Theme

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("app_pref", Context.MODE_PRIVATE)
        val accessToken = sharedPreferences.getString("access_token", null)

        if (accessToken != null) {
            // Токен доступа существует, переход на HomeActivity
//            val intent = Intent(this, HomeActivity::class.java)
//            startActivity(intent)
//            finish()
        } else {
            // Токен доступа отсутствует, переход на LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}