package com.codecamp.newsx.data.utils

import com.codecamp.newsx.data.local.ArticleEntity
import com.codecamp.newsx.domain.model.Article
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun getTimeAgo(dateTimeString: String): String {
    val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
    val dateTime = LocalDateTime.parse(dateTimeString, formatter)
    val now = LocalDateTime.now()

    val minutesAgo = ChronoUnit.MINUTES.between(dateTime, now)

    return when {
        minutesAgo < 1 -> "Published just now"
        minutesAgo == 1L -> "Published 1 minute ago"
        minutesAgo < 60 -> "Published $minutesAgo minutes ago"
        minutesAgo < 120 -> "Published 1 hour ago"
        minutesAgo < (24 * 60) -> "Published ${minutesAgo / 60} hours ago"
        else -> "Published ${dateTime.format(DateTimeFormatter.ofPattern("MMM d, yyyy"))}"
    }
}


fun ArticleEntity.toObjectTime() : Article {
    return Article(
        id = this.id,
        date = getTimeAgo(this.date),
        slug = this.slug,
        title = this.title,
        image = this.image
    )
}