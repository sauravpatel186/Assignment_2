package com.example.assignment_2

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.util.UUID
import com.example.assignment_2.Task

class AddTaskFragment : Fragment() {
    private  lateinit var taskName:EditText
    private lateinit var taskDescription: EditText
    private lateinit var taskPriority:Spinner
    private  lateinit var taskAddButton: Button
    companion object {
        val taskList = mutableListOf<Task>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_task, container, false)
        taskName = view.findViewById(R.id.task_name)
        taskDescription = view.findViewById(R.id.task_description)
        taskAddButton = view.findViewById(R.id.add_task_button)
        taskPriority = view.findViewById(R.id.task_priority)
        val priority = arrayOf("Select task priority","Low","Medium","High")
        val priorityAdapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1,priority)
        taskPriority.adapter = priorityAdapter
        var proritySelected = ""
        taskPriority.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                proritySelected = priority.get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        taskAddButton.setOnClickListener{
            val name = taskName.text.toString()
            val description = taskDescription.text.toString()
            if(proritySelected.isNotEmpty() && name.isNotEmpty() && description.isNotEmpty()){
                if (proritySelected == priority[0]){
                    Toast.makeText(requireContext(),"Please select a priority",Toast.LENGTH_LONG).show()
                }
                else{

                    val uniqueKey = UUID.randomUUID().toString()
                    taskList.add(Task(uniqueKey,name,description,proritySelected))
//                    findNavController().navigate(R.id.action_addTaskFragmet_to_viewTaskFragmet)
                        changeFragment(ViewTaskFragment())
                }
            }
        }
        return  view
    }
    private fun changeFragment(fragment: Fragment){
        val fragmentManager = fragmentManager?.beginTransaction()
        fragmentManager?.replace(R.id.frame_layout,fragment)?.commit()
    }
}