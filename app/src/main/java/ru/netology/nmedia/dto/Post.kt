package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likedByMe: Boolean = false,
    var likesCount: Int = 999,
    var sharesCount: Int = 10_999
)
