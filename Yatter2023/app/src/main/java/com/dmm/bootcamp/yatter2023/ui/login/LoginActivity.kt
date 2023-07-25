package com.dmm.bootcamp.yatter2023.ui.login

import android.content.Context
import android.content.Intent
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
import com.dmm.bootcamp.yatter2023.ui.login.ui.theme.Yatter2023Theme
import com.dmm.bootcamp.yatter2023.ui.timeline.PublicTimelineActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : ComponentActivity() {
    companion object {
        fun newIntent(context: Context): Intent = Intent(
            context,
            LoginActivity::class.java,
        )
    }

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Yatter2023Theme {
                Surface {
                    LoginPage(viewModel = viewModel)
                }
            }
        }

        viewModel.navigateToPublicTimeline.observe(this) {
            startActivity(PublicTimelineActivity.newIntent(this))
            finish()
        }

        viewModel.navigateToRegister.observe(this) {
            //TODO: 会員登録画面への遷移
        }
    }
}
