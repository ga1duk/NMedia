package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.databinding.FragmentNewPostBinding
import ru.netology.nmedia.util.StringArg
import ru.netology.nmedia.viewmodel.PostViewModel

class NewPostFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNewPostBinding.inflate(inflater, container, false)
        val viewModel: PostViewModel by viewModels(::requireParentFragment)

        arguments?.textArg?.let { binding.etContent.setText(it) }
//        аналогичная запись
//        arguments?.textArg?.let(binding.etContent::setText)

        binding.etContent.requestFocus()
        binding.fabSave.setOnClickListener {
            val content = binding.etContent.text.toString()
            viewModel.changeContent(content)
            viewModel.save()
            findNavController().navigateUp()

//            val intent = Intent()
//            if (content.isEmpty()) {
//                activity?.setResult(Activity.RESULT_CANCELED, intent)
//            } else {
//                intent.putExtra(Intent.EXTRA_TEXT, content)
//                activity?.setResult(Activity.RESULT_OK, intent)
//            }
        }

        return binding.root
    }

    companion object {
        var Bundle.textArg by StringArg
    }
}


//        binding.etContent.setText(intent?.getStringExtra(Intent.EXTRA_TEXT))
//        binding.etContent.requestFocus()
//        binding.fabSave.setOnClickListener {
//            val text = binding.etContent.text.toString()
//            if (text.isBlank()) {
//                Toast.makeText(
//                    this@NewPostActivity,
//                    getString(R.string.content_is_empty_toast),
//                    Toast.LENGTH_SHORT
//                ).show()
//                setResult(RESULT_CANCELED)
//            } else {
//                setResult(RESULT_OK, Intent().apply {
//                    putExtra(Intent.EXTRA_TEXT, text)
//                })
//                finish()
//            }
//        }
//    }


//class NewPostResultContract : ActivityResultContract<String, String?>() {
//    override fun createIntent(context: Context, input: String?): Intent =
//        Intent(context, NewPostActivity::class.java).apply {
//            putExtra(Intent.EXTRA_TEXT, input)
//        }
//
//    override fun parseResult(resultCode: Int, intent: Intent?): String? =
//        if (resultCode == Activity.RESULT_OK) {
//            intent?.getStringExtra(Intent.EXTRA_TEXT)
//        } else {
//            null
//        }
//}