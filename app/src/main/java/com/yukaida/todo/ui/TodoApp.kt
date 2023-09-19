package com.yukaida.todo.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yukaida.todo.R
import com.yukaida.todo.ui.theme.TodoTheme
import kotlinx.coroutines.launch

private const val TAG = "TodoApp"

@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TodoAppPreview() {
    TodoApp()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoApp() {
    TodoTheme {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        // icons to mimic drawer destinations
        val items = listOf(Icons.Default.Favorite, Icons.Default.Face, Icons.Default.Email)
        val selectedItem = remember { mutableStateOf(items[0]) }

        ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(240.dp)) {
                Spacer(modifier = Modifier.height(12.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item, contentDescription = null) },
                        label = { Text(item.name) },
                        selected = item == selectedItem.value,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem.value = item
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )

                }
            }
        }) {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        navigationIcon = {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .size(24.dp)
                                    .clickable {
                                        //open drawer
                                        scope.launch { drawerState.open() }
                                    }
                            )
                        },
                        title = {
                            Text(
                                text = "Todo",
                                style = TextStyle.Default.copy(
                                    fontWeight = FontWeight.W900,
                                    fontSize = 24.sp, color = MaterialTheme.colorScheme.primary
                                )
                            )
                        },
                        actions = {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .size(24.dp)
                                    .clickable {
                                        //pop add item dialog
                                    }
                            )
                        }

                    )
                },
                bottomBar = {
                    NavigationBar(modifier = Modifier.height(60.dp)) {
                        Text(text = "NavigationBar还没写")
                    }
                },
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        LazyColumn() {
                            repeat(50) {
                                item {
                                    Surface(
                                        modifier = Modifier
                                            .padding(horizontal = 8.dp)
                                            .padding(top = 8.dp),
//                                        color = MaterialTheme.colorScheme.onSurface,
                                        shadowElevation = 8.dp, shape = RoundedCornerShape(8.dp)
                                    ) {
                                        ListItem(
                                            headlineContent = { Text("One line list item with 24x24 icon") },
                                            leadingContent = {
                                                Image(
                                                    painterResource(id = R.drawable.todo),
                                                    modifier = Modifier.size(24.dp),
                                                    contentDescription = "Localized description",
                                                )
                                            }
                                        )
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


