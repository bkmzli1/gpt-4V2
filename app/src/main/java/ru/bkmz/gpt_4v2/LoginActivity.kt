package ru.bkmz.gpt_4v2

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.bkmz.gpt_4.AuthApi
import ru.bkmz.gpt_4.LoginResponse
import ru.bkmz.gpt_4.User
import ru.bkmz.gpt_4.ui.theme.ApiClient

class LoginActivity : AppCompatActivity() {
    private lateinit var authApi: AuthApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        authApi = ApiClient.retrofit.create(AuthApi::class.java)

        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            val user = User(username = username, password = password)
            val call = authApi.login(user)

            call.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        // Авторизация прошла успешно, сохраняем токен доступа и переходим к HomeActivity
                        val loginResponse = response.body()
                        val sharedPreferences = getSharedPreferences("app_pref", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("access_token", loginResponse?.accessToken)
                        editor.apply()

                        // Запуск HomeActivity и завершение LoginActivity
//                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
//                        finish()
                    } else {
                        // Неправильные учетные данные
                        Toast.makeText(this@LoginActivity, "Invalid username or password", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}