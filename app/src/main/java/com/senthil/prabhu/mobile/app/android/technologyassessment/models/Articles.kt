package com.senthil.prabhu.mobile.app.android.technologyassessment.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Articles(
    var status: String,
    var num_results: Int,
    var results: List<Results>
) : Serializable

data class Results(
    var url: String,
    val adx_keywords: String,
    val column: String,
    val section: String,
    val byline: String,
    val type: String,
    val title: String,
    val abstract: String,
    val published_date: String,
    val source: String,
    val id: String,
    val asset_id: String,
    val views: String,
    val media: List<Media>
) : Serializable

data class Media(
    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetaData>,
    val caption: String,
    val copyright: String,
) : Serializable

data class MediaMetaData(
    val url: String,
    val format: String,
    val height: String,
    val width: String
) : Serializable

