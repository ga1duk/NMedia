package ru.netology.nmedia.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.convertNumber
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter {
            viewModel.likeById(it.id)
        }

        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.list = posts
        }
//        Подписываемся на обновление data во viewModel
//        viewModel.data.observe(this) { posts ->
//            binding.container.removeAllViews()
//            posts.map { post ->
//                CardPostBinding.inflate(layoutInflater, binding.container, true).apply {
//                    tvAuthor.text = post.author
//                    tvPublished.text = post.published
//                    tvContent.text = post.content
//                    tvLikes.text = convertNumber(post.likesCount)
////                    tvShares.text = convertNumber(post.sharesCount)
//                    ivLikes.setImageResource(
//                        if (post.likedByMe) R.drawable.ic_baseline_favorite_24
//                        else R.drawable.ic_baseline_favorite_border_24
//                    )
//                    ivLikes.setOnClickListener {
//                        viewModel.likeById(post.id)
//                    }
////                    ivShares.setOnClickListener {
////                        viewModel.share()
////                    }
//                }.root
//            }
//        }
    }
}

