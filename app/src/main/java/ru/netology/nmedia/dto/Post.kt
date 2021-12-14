package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val video: String? = null,
    val published: String,
    val likedByMe: Boolean = false,
    val likesCount: Int = 999,
    val sharesCount: Int = 10_999
)
