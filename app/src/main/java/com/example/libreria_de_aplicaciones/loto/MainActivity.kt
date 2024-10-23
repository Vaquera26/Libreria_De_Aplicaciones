package com.example.libreria_de_aplicaciones.loto

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.app_container_apps.loto.viewModels.LoteriaViewModel
import com.example.app_container_apps.loto.views.Loteriaview
import com.example.navigate.components.MainIconButton
import com.example.navigate.components.TitleBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoteriaScreen(navController: NavController) {
    val viewModel: LoteriaViewModel = viewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { TitleBar(name = "Lotería") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Blue
                ),
                navigationIcon = {
                    MainIconButton(icon = Icons.Default.ArrowBack) {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) { paddingValues ->
        LoteriaContent(viewModel, paddingValues)
    }
}


@Composable
fun LoteriaContent(viewModel: LoteriaViewModel, paddingValues: PaddingValues) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Loteriaview(viewModels = viewModel)
    }
}