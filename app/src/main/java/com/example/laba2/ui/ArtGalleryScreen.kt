package com.example.laba2.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.laba2.R
import com.example.laba2.data.Artwork
import com.example.laba2.data.ArtworkRepository

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ArtGalleryScreen(
    modifier: Modifier = Modifier,
    artworks: List<Artwork> = ArtworkRepository.artworks
) {
    var currentIndex by rememberSaveable { mutableIntStateOf(0) }
    
    val currentArtwork = artworks[currentIndex]
    val isFirstArtwork = currentIndex == 0
    val isLastArtwork = currentIndex == artworks.lastIndex
    
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val isLandscape = maxWidth > maxHeight
        
        if (isLandscape) {
            LandscapeLayout(
                artwork = currentArtwork,
                isFirstArtwork = isFirstArtwork,
                isLastArtwork = isLastArtwork,
                onPreviousClick = { currentIndex-- },
                onNextClick = { currentIndex++ }
            )
        } else {
            PortraitLayout(
                artwork = currentArtwork,
                isFirstArtwork = isFirstArtwork,
                isLastArtwork = isLastArtwork,
                onPreviousClick = { currentIndex-- },
                onNextClick = { currentIndex++ }
            )
        }
    }
}

@Composable
private fun PortraitLayout(
    artwork: Artwork,
    isFirstArtwork: Boolean,
    isLastArtwork: Boolean,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
) {
    val paddingLarge = dimensionResource(id = R.dimen.padding_large)
    val paddingXlarge = dimensionResource(id = R.dimen.padding_xlarge)
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingLarge),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ArtworkImage(
                artwork = artwork,
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(paddingXlarge))
            
            ArtworkInfo(artwork = artwork)
        }
        
        Spacer(modifier = Modifier.height(paddingXlarge))
        
        NavigationButtons(
            isFirstArtwork = isFirstArtwork,
            isLastArtwork = isLastArtwork,
            onPreviousClick = onPreviousClick,
            onNextClick = onNextClick,
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(paddingLarge))
    }
}

@Composable
private fun LandscapeLayout(
    artwork: Artwork,
    isFirstArtwork: Boolean,
    isLastArtwork: Boolean,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
) {
    val paddingLarge = dimensionResource(id = R.dimen.padding_large)
    val paddingXlarge = dimensionResource(id = R.dimen.padding_xlarge)
    
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingLarge),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(end = paddingLarge),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ArtworkImage(
                artwork = artwork,
                modifier = Modifier.fillMaxWidth()
            )
        }
        
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.weight(1f))
            
            ArtworkInfo(artwork = artwork)
            
            Spacer(modifier = Modifier.weight(1f))
            
            NavigationButtons(
                isFirstArtwork = isFirstArtwork,
                isLastArtwork = isLastArtwork,
                onPreviousClick = onPreviousClick,
                onNextClick = onNextClick,
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(paddingLarge))
        }
    }
}

@Composable
private fun ArtworkImage(
    artwork: Artwork,
    modifier: Modifier = Modifier
) {
    val title = stringResource(id = artwork.titleResId)
    val artist = stringResource(id = artwork.artistResId)
    val description = "$title, $artist"
    val cardElevation = dimensionResource(id = R.dimen.card_elevation)

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = cardElevation)
    ) {
        Image(
            painter = painterResource(id = artwork.imageResId),
            contentDescription = description,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
private fun ArtworkInfo(
    artwork: Artwork,
    modifier: Modifier = Modifier
) {
    val title = stringResource(id = artwork.titleResId)
    val artist = stringResource(id = artwork.artistResId)
    val year = stringResource(id = artwork.yearResId)
    val paddingLarge = dimensionResource(id = R.dimen.padding_large)
    val paddingMedium = dimensionResource(id = R.dimen.padding_medium)
    val paddingSmall = dimensionResource(id = R.dimen.padding_small)
    
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(paddingLarge),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(paddingMedium))
            
            Text(
                text = artist,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(paddingSmall))
            
            Text(
                text = year,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun NavigationButtons(
    isFirstArtwork: Boolean,
    isLastArtwork: Boolean,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val previousButtonHint = stringResource(id = R.string.btn_previous_hint)
    val nextButtonHint = stringResource(id = R.string.btn_next_hint)
    val paddingLarge = dimensionResource(id = R.dimen.padding_large)
    
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = onPreviousClick,
            enabled = !isFirstArtwork,
            modifier = Modifier
                .weight(1f)
                .semantics { contentDescription = previousButtonHint }
        ) {
            Text(text = stringResource(id = R.string.btn_previous))
        }
        
        Spacer(modifier = Modifier.width(paddingLarge))
        
        Button(
            onClick = onNextClick,
            enabled = !isLastArtwork,
            modifier = Modifier
                .weight(1f)
                .semantics { contentDescription = nextButtonHint }
        ) {
            Text(text = stringResource(id = R.string.btn_next))
        }
    }
}

