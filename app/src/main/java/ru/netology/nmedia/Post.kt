package ru.netology.nmedia

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likedByMe: Boolean = false,
    var likesCount: Int = 999,
    var sharesCount: Int = 10_999
)
