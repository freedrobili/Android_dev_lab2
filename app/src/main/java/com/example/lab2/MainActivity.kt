package com.example.lab2

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(context = this)
        }
    }
}

@Composable
fun MyApp(context: Context) {
    val numbers = remember { generateRandomNumbers() }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NumberGrid(numbers = numbers, context = context)
    }
}

@Composable
fun NumberGrid(numbers: List<Int>, context: Context) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        content = {
            itemsIndexed(numbers.chunked(4)) { index, row ->
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    row.forEach { number ->
                        NumberCell(number = number, context = context)
                    }
                }
            }
        }
    )
}

@Composable
fun NumberCell(number: Int, context: Context) {
    val backgroundColor = remember(number) { generateRandomColor() }

    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(80.dp)
            .clickable { showMessage(context, number) },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(color = backgroundColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = number.toString(),
                color = Color.White,
                fontSize = 40.sp
            )
        }
    }
}


fun showMessage(context: Context, number: Int) {
    Toast.makeText(
        context,
        "Clicked on number $number",
        Toast.LENGTH_SHORT
    ).show()
}

fun generateRandomNumbers(): List<Int> {
    return List(32) { Random.nextInt(0, 100) }
}

fun generateRandomColor(): Color {
    return Color(
        Random.nextFloat(),
        Random.nextFloat(),
        Random.nextFloat(),
        1f
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp(context = androidx.compose.ui.platform.LocalContext.current)
}
