package com.example.sodv3203_artspaceapp_irenechan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sodv3203_artspaceapp_irenechan.ui.theme.SODV3203_ArtSpaceApp_IreneChanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SODV3203_ArtSpaceApp_IreneChanTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    var currArtworkId by remember { mutableStateOf(0) }
    var artworkResource = R.drawable.img_cat_1
    var artworkResourceText = stringResource(R.string.cat_image_1)
    var artworkDescription = stringResource(R.string.bubu_sitting_on_the_sofa)
    var artworkAuthor = stringResource(R.string.irene_chan)
    var artworkYear = stringResource(R.string.year_2015)

    when (currArtworkId) {
        0 -> {
            artworkResource = R.drawable.img_cat_1
            artworkResourceText = stringResource(R.string.cat_image_1)
            artworkDescription = stringResource(R.string.bubu_sitting_on_the_sofa)
            artworkAuthor = stringResource(R.string.irene_chan)
            artworkYear = stringResource(R.string.year_2015)
        }
        1 -> {
            artworkResource = R.drawable.img_cat_2
            artworkResourceText = stringResource(R.string.cat_image_2)
            artworkDescription = stringResource(R.string.dudu_sitting_on_the_sofa)
            artworkAuthor = stringResource(R.string.edan_ho)
            artworkYear = stringResource(R.string.year_2020)
        }
        2 -> {
            artworkResource = R.drawable.img_cat_3
            artworkResourceText = stringResource(R.string.cat_image_3)
            artworkDescription = stringResource(R.string.bubu_and_dudu)
            artworkAuthor = stringResource(R.string.irene_chan)
            artworkYear = stringResource(R.string.year_2020)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            //.verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.weight(1f))
        ArtworkWall(
            artworkResource,
            artworkResourceText,
            modifier = Modifier.weight(6f)
        )
        ArtworkDescriptor(
            artworkDescription,
            artworkAuthor,
            artworkYear,
            modifier = Modifier.weight(2f)
        )
        DisplayController(
            onPreviousClick = {
                when (currArtworkId) {
                    1 -> currArtworkId = 3
                    else -> currArtworkId--
                }
            },
            onNextClick = {
                when (currArtworkId) {
                    3 -> currArtworkId = 1
                    else -> currArtworkId++
                }
            },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ArtworkWall(
    imgResourceId: Int,
    imgDescription: String,
    modifier: Modifier = Modifier
) {
    Surface (
        modifier = modifier
            .fillMaxWidth(1f)
            .padding(bottom = 20.dp)
    ) {
        ImageWithBorderAndShadow(imgResourceId, imgDescription)
    }
}

@Composable
fun ImageWithBorderAndShadow(imageResId: Int, imgDescription: String) {
    Surface(
        modifier = Modifier
            .padding(16.dp)
            .shadow(10.dp, shape = RoundedCornerShape(5.dp))
            .padding(8.dp),
        color = Color.White
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = imgDescription,
            modifier = Modifier.padding(30.dp)
        )
    }
}

@Composable
fun ArtworkDescriptor(
    artwork_1_description: String,
    artwork_1_author: String,
    artwork_1_year: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(1f)
            .padding(20.dp),
        color = Color.LightGray
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.padding(15.dp)
        ) {
            Text(
                text = artwork_1_description,
                fontSize = 24.sp,
                fontWeight = FontWeight.Light
            )
            Row {
                Text(
                    text = "$artwork_1_author ",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "($artwork_1_year)",
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}

@Composable
fun DisplayController(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth(1f)
    ) {
        Button(
            onClick = onPreviousClick,
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
            modifier = modifier.size(width = 120.dp, height = 40.dp)
        ) {
            Text(
                text = "Previous"
            )
        }
        Button(
            onClick = onNextClick,
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
            modifier = modifier.size(width = 120.dp, height = 40.dp)
        ) {
            Text(
                text = "Next"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    SODV3203_ArtSpaceApp_IreneChanTheme {
        ArtSpaceApp()
    }
}