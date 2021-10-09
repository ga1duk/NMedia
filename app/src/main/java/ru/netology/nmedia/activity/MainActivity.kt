package ru.netology.nmedia.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.convertNumber
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
//        Подписываемся на обновление data во viewModel
        viewModel.data.observe(this) { post ->
            with(binding) {
                tvAuthor.text = post.author
                tvPublished.text = post.published
                tvContent.text = post.content
                tvLikes.text = convertNumber(post.likesCount)
                tvShares.text = convertNumber(post.sharesCount)
                ivLikes.setImageResource(
                    if (post.likedByMe) R.drawable.ic_baseline_favorite_24
                    else R.drawable.ic_baseline_favorite_border_24
                )
            }
        }

        binding.ivLikes.setOnClickListener {
            viewModel.like()
        }

        binding.ivShares.setOnClickListener {
            viewModel.share()
        }
    }
}

