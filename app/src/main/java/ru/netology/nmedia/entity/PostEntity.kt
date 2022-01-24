package ru.netology.nmedia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.dto.Post

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val content: String,
    val video: String? = null,
    val published: String,
    val likedByMe: Boolean = false,
    val likesCount: Int = 999,
    val sharesCount: Int = 10_999
) {
    fun toDto() = Post(id, author, content, video, published, likedByMe, likesCount, sharesCount)

    companion object {
        fun fromDto(post: Post) = PostEntity(
            post.id,
            post.author,
            post.content,
            post.video,
            post.published,
            post.likedByMe,
            post.likesCount,
            post.sharesCount
        )
    }
}