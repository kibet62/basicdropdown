package com.example.dropdownmenu

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropdownmenu.ui.theme.DropdownMenuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DropdownMenuTheme {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ){
                    items(
                        listOf(
                            "About",
                            "Terms&conditions",
                            "FQAS",
                            "Rate",
                            "Logout"
                        )
                    ){
                        name ->
                        personitem(
                            personName = name,
                            dropDownItems = listOf(
                                DropDownItem("item 1"),
                                DropDownItem("item 2"),
                                DropDownItem("item 3"),
                            ),
                            onItemClick = {
                                Toast.makeText(
                                    applicationContext,
                                    it.text,
                                    Toast.LENGTH_LONG
                                ).show()
                            })
                    }
                }

            }
        }
    }
}

