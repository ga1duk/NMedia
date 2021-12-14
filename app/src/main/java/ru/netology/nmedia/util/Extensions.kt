package ru.netology.nmedia.util

import android.view.View
import androidx.constraintlayout.widget.Group

object Extensions {
    fun Group.setGroupOnClickListener(listener: View.OnClickListener?) {
        referencedIds.forEach { id ->
            rootView.findViewById<View>(id).setOnClickListener(listener)
        }
    }
}