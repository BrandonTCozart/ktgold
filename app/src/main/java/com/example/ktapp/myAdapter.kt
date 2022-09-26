package com.example.ktapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class myAdapter(private val todoList : ArrayList<todoListClass>) : RecyclerView.Adapter<myAdapter.MyViewHolder>() {

    private lateinit var mListener : onItemClickListener



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_layout, parent, false)

        return MyViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = todoList[position]
        holder.checkBox.text = currentItem.date
    }

    override fun getItemCount(): Int {

        return todoList.size
    }

    interface onItemClickListener{

         fun onItemClick(position : Int)

    }

    fun setOnItemClickListener(listener: onItemClickListener){

        mListener = listener

    }

    class  MyViewHolder (itemView : View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView)
    {
        val checkBox : CheckBox = itemView.findViewById(R.id.checkBox)

        init {

            itemView.setOnClickListener{

                listener.onItemClick(adapterPosition)

            }

        }

    }

}