package com.example.app_container_apps.dog_age_calculator_app

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.app_container_apps.dog_age_calculator_app.ui.theme.Dog_Age_Calculator_AppTheme
import com.example.libreria_de_aplicaciones.R
import com.example.navigate.components.MainButton
import com.example.navigate.components.MainIconButton
import com.example.navigate.components.Space
import com.example.navigate.components.TitleBar
import com.example.navigate.components.TitleView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Dog_Age_Calculator_AppTheme {
                DogAgeCalculatorScreen(navController = null) // Asegúrate de pasar el NavController cuando lo uses
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogAgeCalculatorScreen(navController: NavController?) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { TitleBar(name = "Calculadora de Edad de Perro") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Blue
                ),
                navigationIcon = {
                    MainIconButton(icon = Icons.Default.ArrowBack) {
                        navController?.popBackStack()
                    }
                }
            )
        }
    ) {
        DogAgeCalculatorContent(navController)
    }
}

@Composable
fun DogAgeCalculatorContent(navController: NavController?) {
    var humanAge by remember { mutableStateOf("") }
    var dogAge by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        GreetingImage(imagen = R.drawable.perrito)
        Spacer(modifier = Modifier.height(40.dp))
        TitleView(name = "My Dog Years")
        Space()

        OutLineTextFieldSample(
            labelText = "Enter your age",
            value = humanAge,
            onValueChange = { humanAge = it },
            readOnly = false
        )

        Space()

        MainButton(name = "Calculate", backColor = Color.Green, color = Color.White) {
            try {
                val age = humanAge.toInt()
                dogAge = (age * 7).toString()
            } catch (e: NumberFormatException) {
                Toast.makeText(context, "Please enter a valid number", Toast.LENGTH_SHORT).show()
            }
        }

        Space()

        OutLineTextFieldSample(
            labelText = "Dog Age",
            value = dogAge,
            onValueChange = {},
            readOnly = true
        )

        Space()

        MainButton(name = "Erase", backColor = Color.Red, color = Color.White) {
            humanAge = ""
            dogAge = ""
        }
    }
}

@Composable
fun OutLineTextFieldSample(
    labelText: String,
    value: String,
    onValueChange: (String) -> Unit,
    readOnly: Boolean
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = value,
            readOnly = readOnly,
            label = { Text(text = labelText) },
            onValueChange = onValueChange,
            modifier = Modifier
                .padding(
                    top = 0.dp,
                    bottom = 16.dp
                )
                .width(350.dp)
        )
    }
}

@Composable
fun GreetingImage(
    imagen: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = imagen),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(500.dp, 200.dp)
            .fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Dog_Age_Calculator_AppTheme {
        DogAgeCalculatorScreen(navController = null)
    }
}