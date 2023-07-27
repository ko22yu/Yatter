package com.dmm.bootcamp.yatter2023.ui.timeline

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dmm.bootcamp.yatter2023.ui.theme.Yatter2023Theme
import com.dmm.bootcamp.yatter2023.ui.timeline.bindingmodel.MediaBindingModel
import com.dmm.bootcamp.yatter2023.ui.timeline.bindingmodel.StatusBindingModel

@Composable
fun StatusRow(
    statusBindingModel: StatusBindingModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            model = statusBindingModel.avatar,
            contentDescription = "アバター画像",
            contentScale = ContentScale.Crop,
        )
        Column(modifier = modifier.padding(horizontal = 16.dp)) {
            Row {
                Text(text = statusBindingModel.displayName, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = modifier.padding(2.dp))
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = "@${statusBindingModel.username}",
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
            Spacer(modifier = Modifier.padding(2.dp))
            Text(text = statusBindingModel.content, fontSize = 16.sp)

            Row(modifier = Modifier.fillMaxWidth()) {
                statusBindingModel.attachmentMediaList.map {
                    AsyncImage(
                        modifier = Modifier.size(96.dp),
                        model = it.url,
                        contentDescription = it.description
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun StatusRowPreview() {
    Yatter2023Theme {
        Surface {
            StatusRow(
                statusBindingModel = StatusBindingModel(
                    id = "id",
                    displayName = "mito",
                    username = "mitohato14",
                    avatar = "https://avatars.githubusercontent.com/u/19385268?v=4",
                    content = "preview content",
                    attachmentMediaList = listOf(
                        MediaBindingModel(
                            id = "id",
                            type = "image",
                            url = "https://avatars.githubusercontent.com/u/39693306?v=4",
                            description = "icon"
                        )
                    )
                )
            )
        }
    }
}