package ru.netology.nmedia.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.R
import ru.netology.nmedia.activity.NewPostFragment.Companion.textArg
import ru.netology.nmedia.convertNumber
import ru.netology.nmedia.databinding.FragmentPostBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class PostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPostBinding.inflate(inflater, container, false)

        val viewModel: PostViewModel by viewModels(::requireParentFragment)

        val id = arguments?.textArg?.toLong()
        with(binding.post) {
            viewModel.data.observe(viewLifecycleOwner) { posts ->
                posts.find { post -> post.id == id }?.let { post ->
                    tvAuthor.text = post.author
                    tvPublished.text = post.published
                    tvContent.text = post.content
                    btnLike.isChecked = post.likedByMe
                    btnLike.text = convertNumber(post.likesCount)
                    btnShare.text = convertNumber(post.sharesCount)
                    groupVideo.isVisible = !post.video.isNullOrBlank()

                    btnLike.setOnClickListener {
                        viewModel.likeById(post.id)
                    }

                    btnShare.setOnClickListener {
                        val intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, post.content)
                            type = "text/plain"
                        }

                        val shareIntent =
                            Intent.createChooser(intent, getString(R.string.chooser_share_post))
                        startActivity(shareIntent)

                        viewModel.shareById(post.id)
                    }

                    btnMenu.setOnClickListener { view ->
                        PopupMenu(view.context, view).apply {
                            inflate(R.menu.options_post)
                            setOnMenuItemClickListener { item ->
                                when (item.itemId) {
                                    R.id.remove -> {
                                        findNavController().navigateUp()
                                        viewModel.removeById(post.id)
                                        true
                                    }
                                    R.id.edit -> {
                                        val content = tvContent.text.toString()
                                        findNavController().navigate(
                                            R.id.action_postFragment_to_newPostFragment,
                                            Bundle().apply {
                                                textArg = content
                                            }
                                        )
                                        viewModel.edit(post)
                                        true
                                    }
                                    else -> false
                                }
                            }
                        }.show()
                    }
                }
            }
        }

        return binding.root
    }
}