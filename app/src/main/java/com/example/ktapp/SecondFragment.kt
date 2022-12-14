package com.example.ktapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ktapp.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private lateinit var adapter: myAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var todoArrayList : ArrayList<todoListClass>
    private lateinit var todolistAction: String

    private lateinit var sql : DataBaseHelper

    lateinit var todoText : Array<String>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sql = DataBaseHelper(requireContext())

        dataInitialize()


        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recyclerviewTODO)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = myAdapter(todoArrayList)
        recyclerView.adapter = adapter


        //val args = this.arguments
        //var argsString = args?.get("text")


        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        adapter.setOnItemClickListener(object : myAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                todolistAction = todoArrayList[position].text
                Toast.makeText(context, " click $position", Toast.LENGTH_SHORT).show()
            }
        })

        binding.buttonDelete.setOnClickListener {

            sql.deleteCol(todolistAction)

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun dataInitialize(){

        //todoArrayList = //database  /* This method will be called and will initialize the todoArryList that will then
    // be used to initialize the adapter above to the myadapter that requires the parameter of a List array*/

        /*
        val random = todoListClass("random text", "today")
        val random1 = todoListClass("random text 2", "today")

         */

        todoArrayList = arrayListOf<todoListClass>()

        /*
        todoArrayList.add(random)
        todoArrayList.add(random1)
         */


        val stdList: ArrayList<todoListClass> = sql.getAllTODO()
        for(n in 0..stdList.size-1){
            todoArrayList.add(stdList[n])
        }



    }
}