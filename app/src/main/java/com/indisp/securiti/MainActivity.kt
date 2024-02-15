package com.indisp.securiti

import UserProfileScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import com.indisp.securiti.data.repository.UserRepositoryImpl
import com.indisp.securiti.design_system.theme.SecuritiTheme
import com.indisp.securiti.domain.usecase.RetrieveSavedUserProfileUseCase
import com.indisp.securiti.domain.usecase.SaveUserProfileUseCase
import com.indisp.securiti.storage.Storage
import com.indisp.securiti.ui.UserProfileViewModel
import kotlinx.coroutines.CoroutineScope

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecuritiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserProfileScreen()
                }
            }
        }
    }
}