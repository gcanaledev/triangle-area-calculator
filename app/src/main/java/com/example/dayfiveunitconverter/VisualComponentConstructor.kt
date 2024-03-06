package com.example.dayfiveunitconverter

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class VisualComponentConstructor {

    private val _mainColumnPadding = 10.dp
    private val _rowPadding = 25.dp
    private var _chosenType by mutableStateOf(String())
    private var _triangleTypeTitle by mutableStateOf(String())
    private var _expandMenuBar by mutableStateOf(false)
    private val _sideTextFieldTextAlign = TextAlign.Center
    private val _textFieldFillMaxSizeModifier = Modifier.fillMaxSize()

    private var sideAValue: Double? = null
    private var sideBValue: Double? = null
    private var sideCValue: Double? = null

    @Composable
    fun ShowTriangleCalculatorMainView() {

        val context = LocalContext.current

        Column (modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween){

            Column (horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)){
                Text(text = "triangle area calculator! choose your triangle type.",
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center)
            }

            Column(
                Modifier
                    .padding(_mainColumnPadding)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {

                Text(text = _triangleTypeTitle)
                Text(text = "do not know what is triangle's type?")

                fun showUserResponseMessage(message: String){
                    Toast.makeText(context, message,Toast.LENGTH_LONG).show()
                }

                fun onRequestTriangleSideButtonClicked(sideA: Double?, sideB: Double?, sideC: Double?){
                    val responseMessage = getUserTriangleResponseMessage(getTriangleTypeBySideValues(sideA, sideB, sideC))
                    showUserResponseMessage(responseMessage)
                }

                var sideAValueInserted by remember { mutableStateOf(String()) }
                var sideBValueInserted by remember { mutableStateOf(String()) }
                var sideCValueInserted by remember { mutableStateOf(String()) }

                var baseInsertedValue by remember { mutableStateOf(String()) }
                var heightInsertedValue by remember { mutableStateOf(String()) }

                var areaCalculationTextResult by remember { mutableStateOf("waiting for inserting triangle's data...") }


                Button(onClick = { onRequestTriangleSideButtonClicked(sideAValueInserted.toDoubleOrNull(),
                    sideBValueInserted.toDoubleOrNull(),
                    sideCValueInserted.toDoubleOrNull()) },
                    modifier = Modifier
                        .padding(24.dp)
                        .height(30.dp)) {
                    Text(text = "know triangle's type by sides values", fontSize = 12.sp)
                }

                Row (modifier = Modifier.fillMaxWidth(0.90f),
                    horizontalArrangement = Arrangement.SpaceBetween){

                    val sideTextFieldWidth = 100.dp
                    val sideTextFieldHeigth = 60.dp
                    val sideTextFieldFontSize = 10.sp



                    OutlinedTextField(
                        value = sideAValueInserted,
                        onValueChange = {sideAValueInserted = it},
                        Modifier.size(sideTextFieldWidth, sideTextFieldHeigth),
                        label = { Text(text = "enter ${TriangleSides.A} value",
                            fontSize = sideTextFieldFontSize,
                            textAlign = _sideTextFieldTextAlign,
                            modifier = _textFieldFillMaxSizeModifier)
                        }
                    )

                    OutlinedTextField(
                        value = sideBValueInserted,
                        onValueChange = {sideBValueInserted = it},
                        Modifier.size(sideTextFieldWidth, sideTextFieldHeigth),
                        label = { Text(text = "enter ${TriangleSides.B} value",
                            fontSize = sideTextFieldFontSize,
                            textAlign = _sideTextFieldTextAlign,
                            modifier = _textFieldFillMaxSizeModifier)
                        }
                    )


                    OutlinedTextField(
                        value = sideCValueInserted,
                        onValueChange = {sideCValueInserted = it},
                        Modifier.size(sideTextFieldWidth, sideTextFieldHeigth),
                        label = { Text(text = "enter ${TriangleSides.C} value",
                            fontSize = sideTextFieldFontSize,
                            textAlign = _sideTextFieldTextAlign,
                            modifier = _textFieldFillMaxSizeModifier)
                        }
                    )
                }

                Spacer(modifier = Modifier.height(70.dp))

                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
                    .fillMaxWidth()) {
                    Box (Modifier.size(140.dp, 35.dp)){

                        Button(onClick = {_expandMenuBar = true}) {
                            Text(text = "triangle type")
                            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "dropMenuArrowIcon")
                        }

                        DropdownMenu(expanded = _expandMenuBar, onDismissRequest = {_expandMenuBar = false}) {
                            TriangleTypes.entries.forEach { triangleType ->
                                DropdownMenuItem(text =  { Text(text = "$triangleType") }, onClick = {changeUserInterfaceByTypeChosen(triangleType)})
                            }
                        }
                    }
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(_rowPadding),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom){

                    val calculationDataTextViewWidth = 100.dp
                    val calculationDataTextViewHeight = 60.dp

                    OutlinedTextField(
                        value = baseInsertedValue,
                        onValueChange = {baseInsertedValue = it},
                        Modifier.size(calculationDataTextViewWidth, calculationDataTextViewHeight),
                        label = { Text(text = "base value",
                            fontSize = 11.sp,
                            textAlign = TextAlign.Center,
                            modifier = _textFieldFillMaxSizeModifier)}
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    OutlinedTextField(
                        value = heightInsertedValue,
                        onValueChange = {heightInsertedValue = it},
                        Modifier.size(calculationDataTextViewWidth, calculationDataTextViewHeight),
                        label = { Text(text = "high value",
                            fontSize = 11.sp,
                            textAlign = TextAlign.Center,
                            modifier = _textFieldFillMaxSizeModifier)}
                    )

                }

                fun onCalculateAreaButtonClicked()
                {
                    val baseValue = baseInsertedValue.toDoubleOrNull()
                    val heightValue = heightInsertedValue.toDoubleOrNull()

                    val areaCalculationResult = getTriangleArea(baseValue, heightValue)

                    if (areaCalculationResult == null){
                        showUserResponseMessage("warning! you may enter correct values to calculate triangle's area!")
                        return
                    }

                    areaCalculationTextResult = "triangle's area is -> $areaCalculationResult"
                }



                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = { onCalculateAreaButtonClicked() }, modifier = Modifier.size(70.dp, 30.dp)) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = null)
                }

                Spacer(modifier = Modifier.height(75.dp))

                Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center){
                    Text(text = areaCalculationTextResult,
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center)
                }
            }
        }
    }

    private fun getTriangleArea(base: Double?, high: Double?): Double?{
        if (base == null || high  == null) return null

        return ((base * high) / 2)
    }



    private fun getUserTriangleResponseMessage(triangleType: TriangleTypes?): String{
        if (triangleType == null) return "warning! it is necessary to enter all triangle's side's values!"

        return "good! you have a $triangleType triangle!"
    }

    private fun getTriangleTypeBySideValues(sideA: Double?, sideB: Double?, sideC: Double?): TriangleTypes?{
        sideAValue = sideA
        sideBValue = sideB
        sideCValue = sideC

        if (sideAValue == null || sideBValue == null || sideCValue == null) return null

        if (isTriangleEquilateral(sideAValue!!, sideBValue!!, sideCValue!!)) return TriangleTypes.Equilateral

        if (isTriangleIsosceles(sideAValue!!, sideBValue!!, sideCValue!!)) return TriangleTypes.Isosceles

        if (isTriangleScalene(sideAValue!!, sideBValue!!, sideCValue!!)) return TriangleTypes.Scalene

        return TriangleTypes.Nonexistent
    }

    private fun isTriangleEquilateral(sideA: Double, sideB: Double, sideC: Double): Boolean{
        return (sideA == sideB && sideA == sideC)
    }

    private fun isTriangleIsosceles(sideA: Double, sideB: Double, sideC: Double): Boolean{
        return (sideA == sideB || sideA == sideC  || sideB == sideC)
    }

    private fun isTriangleScalene(sideA: Double, sideB: Double, sideC: Double): Boolean{
        return  (sideA != sideB && sideA != sideC)
    }

    private fun changeUserInterfaceByTypeChosen(chosenType: TriangleTypes){
        _expandMenuBar = false

        if (_chosenType != chosenType.name) {
            _chosenType = chosenType.name
            _triangleTypeTitle = "chosen type: $_chosenType"
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ShowPreview(){
        ShowTriangleCalculatorMainView()
    }

    enum class TriangleSides{
       A,
       B,
       C
    }

    enum class TriangleTypes{
        Isosceles,
        Scalene,
        Equilateral,
        Nonexistent
    }
}