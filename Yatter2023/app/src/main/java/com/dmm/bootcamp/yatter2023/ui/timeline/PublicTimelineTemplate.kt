package com.dmm.bootcamp.yatter2023.ui.timeline

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dmm.bootcamp.yatter2023.ui.theme.Yatter2023Theme
import com.dmm.bootcamp.yatter2023.ui.timeline.bindingmodel.StatusBindingModel

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PublicTimelineTemplate(
    statusList: List<StatusBindingModel>,
    onClickPost: () -> Unit,
    isLoading: Boolean,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
) {
    val pullRefreshState = rememberPullRefreshState(isRefreshing, onRefresh)
    var showDialog by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "タイムライン")
                },
                actions = {
                    IconButton(onClick = {
                        showDialog = true

                    }) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "profile"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onClickPost) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "post"
                )
            }
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .pullRefresh(pullRefreshState),
            contentAlignment = Alignment.Center,
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
            ) {
                items(statusList) { item ->
                    StatusRow(statusBindingModel = item)
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                }
            }
            PullRefreshIndicator(
                isRefreshing,
                pullRefreshState,
                Modifier.align(Alignment.TopCenter)
            )
            if (isLoading) {
                CircularProgressIndicator()
            }
        }
    }
    if (showDialog) {
        showDialog = Dialog()
    }
}

@Preview
@Composable
private fun PublicTimelineTemplatePreview() {
    Yatter2023Theme {
        Surface {
            PublicTimelineTemplate(
                statusList = listOf(
                    StatusBindingModel(
                        id = "id",
                        displayName = "display name",
                        username = "username",
                        avatar = null,
                        content = "preview content",
                        attachmentMediaList = listOf()
                    )
                ),
                onClickPost = {},
                isLoading = false,
                isRefreshing = false,
                onRefresh = {},
            )
        }
    }
}

@Composable
fun Dialog(): Boolean {
    var showDialog by remember { mutableStateOf(true) }
    var result by remember { mutableStateOf("Result") }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                result = "Dismiss"
                showDialog = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        result = "OK"
                        showDialog = false
                    }
                ) {
                    Text("閉じる")
                }
            },
            title = {
                Text("アプリの使い方")
            },
            text = {
                Column() {
                    Text("タイムライン画面では他の人のつぶやきを見ることができます。")
                    Text("右下のボタンを押すことで自由につぶやくことができます。")
                }
            },
        )
    }
    return showDialog
}