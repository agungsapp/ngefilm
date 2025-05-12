package com.mobile.ngefilm.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mobile.ngefilm.R

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        view.findViewById<TextView>(R.id.profile_text).text = "User Profile\nName: A Reza Maulana\nNim:  221011403378"
        return view
    }
}