package com.example.ktapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ktapp.databinding.FragmentFirstBinding
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private lateinit var sql : DataBaseHelper

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        sql = DataBaseHelper(requireContext())

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            val toDoText = binding.editTextTask.text.toString() // captures the string from the edit text

            if(toDoText.isBlank()){
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }else{
                val randomToDo = todoListClass(toDoText, Calendar.DATE.toString())

                sql.insertTODO(randomToDo)

                val bundle = Bundle() // passing the data in a bundle //
                bundle.putString("text", toDoText) // passing the data in a bundle //
                val fragment = SecondFragment() // passing the data in a bundle //
                fragment.arguments = bundle // passing the data in a bundle //
                parentFragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment_content_main, fragment)?.commit() // passing the data in a bundle //
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}