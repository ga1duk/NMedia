package ru.netology.nmedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36"
        )

        binding.tvAuthor.text = post.author
        binding.tvPublished.text = post.published
        binding.tvContent.text = post.content
        binding.tvLikes.text = convertNumber(post.likesCount)
        binding.tvShares.text = convertNumber(post.sharesCount)
        if (post.likedByMe) {
            binding.ivLikes.setImageResource(R.drawable.ic_baseline_favorite_24)
        }

        binding.ivLikes.setOnClickListener {
            if (!post.likedByMe) {
                binding.ivLikes.setImageResource(R.drawable.ic_baseline_favorite_24)
                post.likesCount++
            } else {
                binding.ivLikes.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                post.likesCount--
            }
            binding.tvLikes.text = convertNumber(post.likesCount)
            post.likedByMe = !post.likedByMe
        }

        binding.ivShares.setOnClickListener {
            post.sharesCount ++
            binding.tvShares.text = convertNumber(post.sharesCount)
        }
    }

    private fun convertNumber(number: Int): String? {
        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.DOWN
        when {
            number < 1000 -> return number.toString()
            number in 1000..9999 -> return "${df.format(number.toDouble() / 1000)}K"
            number in 10_000..999_999 -> return "${number / 1000}K"
            number >= 1_000_000 -> return "${df.format(number.toDouble() / 1_000_000)}M"
        }
        return null
    }
}

