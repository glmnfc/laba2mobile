package com.example.laba2.data

import com.example.laba2.R

object ArtworkRepository {
    val artworks: List<Artwork> = listOf(
        Artwork(
            imageResId = R.drawable.picture1,
            titleResId = R.string.artwork1_title,
            artistResId = R.string.artwork1_artist,
            yearResId = R.string.artwork1_year,
        ),
        Artwork(
            imageResId = R.drawable.picture2,
            titleResId = R.string.artwork2_title,
            artistResId = R.string.artwork2_artist,
            yearResId = R.string.artwork2_year,
        ),
        Artwork(
            imageResId = R.drawable.picture3,
            titleResId = R.string.artwork3_title,
            artistResId = R.string.artwork3_artist,
            yearResId = R.string.artwork3_year,
        ),
        Artwork(
            imageResId = R.drawable.picture4,
            titleResId = R.string.artwork4_title,
            artistResId = R.string.artwork4_artist,
            yearResId = R.string.artwork4_year,
        )
    )
}
