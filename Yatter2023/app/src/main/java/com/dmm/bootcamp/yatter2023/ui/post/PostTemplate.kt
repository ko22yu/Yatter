package com.dmm.bootcamp.yatter2023.ui.post

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dmm.bootcamp.yatter2023.ui.theme.Yatter2023Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostTemplate(
    postBindingModel: PostBindingModel,
    isLoading: Boolean,
    canPost: Boolean,
    onStatusTextChanged: (String) -> Unit,
    onClickPost: () -> Unit,
    onClickNavIcon: () -> Unit,
) {
    Scaffold(
        topBar = {
            Row() {
                TextButton(
                    onClick = onClickNavIcon,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(text = "キャンセル")
                }
                Spacer(Modifier.weight(1f))
                Button(
                    onClick = onClickPost,
                    modifier = Modifier.padding(16.dp),
                    enabled = canPost,
                ) {
                    Text(text = "ツイート", modifier = Modifier.align(Alignment.CenterVertically))
                }
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Spacer(modifier = Modifier.padding(8.dp))
                AsyncImage(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape),
                    model = postBindingModel.avatarUrl,
                    contentDescription = "アバター画像",
                    contentScale = ContentScale.Crop
                )

                Column(
                    horizontalAlignment = Alignment.End,
                ) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth() // 横幅最大サイズ確保
                            .weight(1f), // 他のコンポーザブルのサイズを確保した上で最大サイズを取る
                        value = postBindingModel.statusText,
                        onValueChange = onStatusTextChanged,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                        ), // TextFieldの枠を透明にするための設定
                        placeholder = {
                            Text(text = "今何してる？", fontSize = 16.sp)
                        },
                    )
                }
            }

            if (isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview
@Composable
fun PostTemplatePreview() {
    Yatter2023Theme {
        Surface() {
            PostTemplate(
                postBindingModel = PostBindingModel(
                    avatarUrl = "",
                    statusText = "Hello world!",
                ),
                isLoading = false,
                canPost = true,
                onStatusTextChanged = {},
                onClickPost = {},
                onClickNavIcon = {},
            )
        }
    }
}