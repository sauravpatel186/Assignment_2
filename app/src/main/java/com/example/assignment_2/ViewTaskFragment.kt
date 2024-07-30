package com.example.assignment_2

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

class ViewTaskFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_view_task, container, false)
        var layout = view.findViewById<LinearLayout>(R.id.view_task_layout)


        for (task in AddTaskFragment.taskList) {
            val taskView = LayoutInflater.from(context).inflate(R.layout.task_layout, layout, false)
            val id = task.id
            val taskName: TextView = taskView.findViewById(R.id.textview_task_name)
            val taskDescription: TextView = taskView.findViewById(R.id.textview_task_description)
            val taskPriority: TextView = taskView.findViewById(R.id.textview_task_priority)
            val deleteText:TextView = taskView.findViewById(R.id.deleteText)
            taskName.text = task.name
            taskDescription.text = task.description
            taskPriority.text = "Priority: ${task.priority}"
            if(task.priority== "High"){
                taskView.setBackgroundColor(Color.parseColor("#ff6b6c"))
            }
            else if(task.priority == "Medium"){
                taskView.setBackgroundColor(Color.parseColor("#FFC145"))
            }
            else{
                taskView.setBackgroundColor(Color.parseColor("#B8B8D1 "))
            }

            layout.addView(taskView)
            deleteText.setOnClickListener{
                removeData(task.id)
            }
        }
        return view
    }
    fun removeData(taskid:String){
        AddTaskFragment.taskList.removeAll{it.id == taskid}
        val fragmentManager = fragmentManager?.beginTransaction()
        fragmentManager?.replace(R.id.frame_layout,ViewTaskFragment())?.commit()

    }

}