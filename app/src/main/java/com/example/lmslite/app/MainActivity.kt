package com.example.lmslite.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.lmslite.app.di.StudentScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint //Để Hilt có thể inject ViewModel vào StudentScreen
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            //Theme của app
            MaterialTheme{
                Surface( // là nền của ứng dụng
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StudentScreen()
                }
            }
        }
    }
}