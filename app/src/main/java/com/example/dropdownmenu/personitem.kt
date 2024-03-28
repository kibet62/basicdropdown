package com.example.dropdownmenu

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
fun personitem(
    personName: String,
    dropDownItems: List<DropDownItem>,
    modifier: Modifier = Modifier,
    onItemClick: (DropDownItem) -> Unit

) {
    var isContextMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var pressOffset by remember {
        mutableStateOf(DpOffset.Zero)
    }
    var itemHeight by remember {
        mutableStateOf(0.dp)
    }
    val density = LocalDensity.current

    val interactionSource = remember {
        MutableInteractionSource()
    }
    Card(

        modifier = Modifier
            .onSizeChanged {
                itemHeight = with(density) { it.height.toDp()}

            }

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .indication(interactionSource, LocalIndication.current)
                .pointerInput(true) {
                    detectTapGestures(onLongPress = {
                        isContextMenuVisible = true
                        pressOffset = DpOffset(it.x.toDp(), it.y.toDp())
                    },
                        onPress = {
                            val press = PressInteraction.Press(it)
                            interactionSource.emit(press)
                            tryAwaitRelease()
                            interactionSource.emit(press)
                        }
                    )

                }
                .padding(15.dp)
        ) {
            Text(text = personName)

        }
        DropdownMenu(
            expanded = isContextMenuVisible,
            onDismissRequest = { isContextMenuVisible = false },
            offset = pressOffset.copy(
                y = pressOffset.y - itemHeight
            )
        ) {
            dropDownItems.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        onItemClick(item)
                        isContextMenuVisible = false
                    }){
                    Text(text = item.text)
                }

            }
        }

    }


}