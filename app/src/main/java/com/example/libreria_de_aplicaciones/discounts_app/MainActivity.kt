package com.example.libreria_de_aplicaciones.discounts_app

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.navigate.components.MainButton
import com.example.navigate.components.MainIconButton
import com.example.navigate.components.Space
import com.example.navigate.components.TitleBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscountsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { TitleBar(name = "Calculadora de Descuentos") },
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
        DiscountsContent(navController, paddingValues)
    }
}

@Composable
fun DiscountsContent(navController: NavController?, paddingValues: PaddingValues) {
    var precio by remember { mutableStateOf("") }
    var descuento by remember { mutableStateOf("") }
    var descuentoAplicado by remember { mutableStateOf("") }
    var total by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Space()

        OutLineTextFieldSample(
            labelText = "Precio",
            value = precio,
            onValueChange = { precio = it },
            readOnly = false
        )
        Space()

        OutLineTextFieldSample(
            labelText = "Descuento",
            value = descuento,
            onValueChange = { descuento = it },
            readOnly = false
        )

        Space()

        MainButton(name = "Calcular", backColor = Color.Green, color = Color.White) {
            try {
                val precioValor = precio.toDoubleOrNull()
                val descuentoValor = descuento.toDoubleOrNull()

                if (precioValor != null && descuentoValor != null) {
                    val cantidadDescuento = precioValor * (descuentoValor / 100)
                    val valorTotal = precioValor - cantidadDescuento

                    descuentoAplicado = String.format("%.2f", cantidadDescuento)
                    total = String.format("%.2f", valorTotal)
                } else {
                    Toast.makeText(context, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
                }
            } catch (e: NumberFormatException) {
                Toast.makeText(context, "Please enter a valid number", Toast.LENGTH_SHORT).show()
            }
        }

        Space()

        OutLineTextFieldSample(
            labelText = "Descuento Aplicado",
            value = descuentoAplicado,
            onValueChange = {},
            readOnly = true
        )

        Space()

        OutLineTextFieldSample(
            labelText = "Total",
            value = total,
            onValueChange = {},
            readOnly = true
        )

        Space()

        MainButton(name = "Borrar", backColor = Color.Red, color = Color.White) {
            precio = ""
            descuento = ""
            descuentoAplicado = ""
            total = ""
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
    OutlinedTextField(
        value = value,
        readOnly = readOnly,
        label = { Text(text = labelText) },
        onValueChange = onValueChange,
        modifier = Modifier
            .padding(top = 0.dp, bottom = 16.dp)
            .width(350.dp)
    )
}