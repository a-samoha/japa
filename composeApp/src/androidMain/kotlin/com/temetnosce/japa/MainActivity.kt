package com.temetnosce.japa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.temetnosce.japa.utils.AndroidScreenLockManager

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidScreenLockManager(this).keepScreenOn(true) // Enable screen stay-on behavior

        setContent {
            CommonCompose()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AppAndroidPreview() = CommonCompose()