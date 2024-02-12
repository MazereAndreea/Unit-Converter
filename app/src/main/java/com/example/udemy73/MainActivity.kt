package com.example.udemy73

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.udemy73.ui.theme.Udemy73Theme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Udemy73Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }

    }
}

@Composable
fun UnitConverter(){

    var inputValue by remember { mutableStateOf("")}
    var outputValue by remember { mutableStateOf("")}
    var inputUnit by remember { mutableStateOf("Meters")}
    var outputUnit by remember { mutableStateOf( "Meters")}
    var iExpanded by remember { mutableStateOf(false)} //dropdown stays closed on the input
    var oExpanded by remember { mutableStateOf(false)} //same but for the dropdown output
    val conversionFactor = remember { mutableStateOf(1.00)}
    val oconversionFactor = remember { mutableStateOf(1.00) }

    fun ConvertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oconversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Unit Converter")
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = inputValue,
                onValueChange = {
                    inputValue = it;
                    ConvertUnits()},
                label = {Text("Enter value")}
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row{
                //input box
                Box{
                    //input button
                    Button(onClick = { iExpanded = true }) {
                        Text(text = inputUnit)
                        Icon(Icons.Default.ArrowDropDown,
                            contentDescription = "Arrow Down")
                    }
                    DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                        DropdownMenuItem(
                            text = { Text("Centimeters") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Centimeters"
                                conversionFactor.value = 0.01
                                ConvertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Meters") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Meters"
                                conversionFactor.value = 1.0
                                ConvertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Feet") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Feet"
                                conversionFactor.value = 0.3048
                                ConvertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Milimeters") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Milimeters"
                                conversionFactor.value = 0.001
                                ConvertUnits()
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Box{
                    //output button
                    Button(onClick = { oExpanded = true }) {
                        Text(text = outputUnit)
                        Icon(Icons.Default.ArrowDropDown,
                            contentDescription = "Arrow Down")
                    }
                    DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                        DropdownMenuItem(
                            text = { Text("Centimeters") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "Centimeters"
                                oconversionFactor.value = 0.01
                                ConvertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Meters") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "Meters"
                                oconversionFactor.value = 1.00
                                ConvertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Feet") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "Feet"
                                oconversionFactor.value = 0.3048
                                ConvertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Milimeters") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "Milimeters"
                                oconversionFactor.value = 0.001
                                ConvertUnits()
                            }                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Result: $outputValue $outputUnit")
        }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}