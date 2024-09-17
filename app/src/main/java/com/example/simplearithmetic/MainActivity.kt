package com.example.simplearithmetic

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArithmeticCalculator()
        }
    }
}

@Composable
fun ArithmeticCalculator() {
    var operand1 by remember { mutableStateOf("") }
    var operand2 by remember { mutableStateOf("") }
    var selectedOperation by remember { mutableStateOf("Addition") }
    var result by remember { mutableStateOf<String?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = operand1,
            onValueChange = { operand1 = it },
            label = { Text("Operand 1") },
            modifier = Modifier.padding(bottom = 16.dp),
            singleLine = true
        )

        TextField(
            value = operand2,
            onValueChange = { operand2 = it },
            label = { Text("Operand 2") },
            modifier = Modifier.padding(bottom = 16.dp),
            singleLine = true
        )

        Row(
            modifier = Modifier.padding(bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedOperation == "Addition",
                onClick = { selectedOperation = "Addition" }
            )
            Text("Addition", modifier = Modifier.padding(start = 4.dp))

            RadioButton(
                selected = selectedOperation == "Subtraction",
                onClick = { selectedOperation = "Subtraction" }
            )
            Text("Subtraction", modifier = Modifier.padding(start = 4.dp))

            RadioButton(
                selected = selectedOperation == "Multiplication",
                onClick = { selectedOperation = "Multiplication" }
            )
            Text("Multiplication", modifier = Modifier.padding(start = 4.dp))
            }
        Row(
            modifier = Modifier.padding(bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedOperation == "Division",
                onClick = { selectedOperation = "Division" }
            )
            Text("Division", modifier = Modifier.padding(start = 4.dp))

            RadioButton(
                selected = selectedOperation == "Modulus",
                onClick = { selectedOperation = "Modulus" }
            )
            Text("Modulus", modifier = Modifier.padding(start = 4.dp))
        }

        Button(
            onClick = {
                val num1 = operand1.toFloatOrNull()
                val num2 = operand2.toFloatOrNull()

                if (num1 == null || num2 == null) {
                    errorMessage = "Invalid Operand"
                    result = null
                } else {
                    errorMessage = null
                    result = when (selectedOperation) {
                        "Addition" -> (num1 + num2).toString()
                        "Subtraction" -> (num1 - num2).toString()
                        "Multiplication" -> (num1 * num2).toString()
                        "Division" -> if (num1 == 0f && num2 == 0f) "Imagine that you have zero cookies and you split them evenly among zero friends. How many cookies does each person get? See? It doesn’t make sense. And Cookie Monster is sad that there are no cookies, and you are sad that you have no friends." else if (num2 == 0f) "You can't divide by zero!" else (num1 / num2).toString()
                        "Modulus" -> if (num1 == 0f && num2 == 0f) "Imagine that you have zero cookies and you split them evenly among zero friends. How many cookies does each person get? See? It doesn’t make sense. And Cookie Monster is sad that there are no cookies, and you are sad that you have no friends." else if (num2 == 0f) "You can't divide by zero!" else (num1 % num2).toString()
                        else -> null
                    }
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Calculate")
        }

        if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 16.dp)

            )
        } else if (result != null) {
            Text(
                text = "Result: $result",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArithmeticCalculatorPreview() {
    ArithmeticCalculator()
}
