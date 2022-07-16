/*
Name: Shubham Dhamane(A00257743) & Shivam Sharma(A00253431)
Project: ToDoList
Description: add button will add the task. long press on text will delete it and normal press will represent its checked by changing color
 */




package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> tasks;
    private ArrayAdapter<String> tasksAdapter;
    private ListView taskList;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taskList = findViewById(R.id.taskList);
        add = findViewById(R.id.AddButton);
        tasks = new ArrayList<>();
        tasksAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,tasks);
        taskList.setAdapter(tasksAdapter);


        taskList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {  //long click will delete it
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
               Context context = getApplicationContext();
                Toast.makeText(context,"Task Removed",Toast.LENGTH_LONG).show();
                tasks.remove(i);
                tasksAdapter.notifyDataSetChanged();
                return true;

            }
        });
        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {   //on click will change the color
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setBackgroundColor(getResources().getColor(R.color.teal_200));
            }
        });


    }


    public void addToList(View view) {        //will add into the list
        EditText task_name = findViewById(R.id.input);
        taskList.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        String input = task_name.getText().toString();
        if (input.isEmpty()){                   //if edittext is empty, will show toast
            Toast.makeText(getApplicationContext(),"Please enter task",Toast.LENGTH_LONG).show();

        }else {
            tasksAdapter.add(input);
            task_name.setText("");
            tasksAdapter.notifyDataSetChanged();
        }
    }
}