package com.example.basicstatecodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicstatecodelab.ui.theme.BasicStateCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicStateCodelabTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    WellnessScreen()
                }
            }
        }
    }
}

@Composable
fun WellnessScreen(modifier: Modifier = Modifier){
    StatefulCounter(modifier)
}

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        var count by remember { mutableStateOf(0) }
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = { count++ }, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

@Composable
fun WellnessTaskItem(
    taskName: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
){
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            modifier = modifier.weight(1f).padding(start = 16.dp),
            text = taskName
        )
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        IconButton(
            onClick = onClose
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Close"
            )
        }
    }
}

@Composable
fun WellnessTaskItem(taskName: String, modifier: Modifier = Modifier) {
    var checkedState by remember { mutableStateOf(false) }

    WellnessTaskItem(
        taskName = taskName,
        checked = checkedState,
        onCheckedChange = { newValue -> checkedState = newValue },
        onClose = {},
        modifier = modifier,
    )
}


@Composable
fun StatelessCounter(
    count: Int,
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        modifier = modifier.padding(16.dp)
    ){
        if(count > 0){
            Text(text = "You've had $count glasses.")
        }
        Button(
            onClick = onIncrement,
            modifier= modifier.padding(top = 8.dp),
            enabled = count < 10
        ) {
            Text(text = "Add one")
        }
    }
}

@Composable
fun StatefulCounter(modifier: Modifier = Modifier){
    var count by rememberSaveable { mutableStateOf(0) }
    StatelessCounter(count, {count++}, modifier)
}

@Preview(showBackground = true)
@Composable
fun WellnessScreenPreview() {
    BasicStateCodelabTheme {
        WellnessScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun WaterCounterPreview() {
    BasicStateCodelabTheme {
        WaterCounter()
    }
}

@Preview(showBackground = true)
@Composable
fun WellnessTaskItemPreview() {
    BasicStateCodelabTheme {
    }
}