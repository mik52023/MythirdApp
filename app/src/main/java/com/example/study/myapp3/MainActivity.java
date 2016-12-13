package com.example.study.myapp3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    DbHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DbHandler(this);
    }


    public void AddToDB(View view) throws ParseException {

        Person p;
        EditText t;
        String name,comment;
        Calendar calander=Calendar.getInstance();
        Date birthday,nextbirthday;
        SimpleDateFormat d=new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");;
        DatePicker temp;

         t=(EditText)findViewById(R.id.Name);
        name=t.toString();
        t=(EditText)findViewById(R.id.Comments);
        comment=t.toString();
        t=(EditText)findViewById(R.id.Birthday);
        birthday=new Date();
        birthday=d.parse(t.toString());
        nextbirthday=new Date();
        nextbirthday=birthday;
        nextbirthday.setYear(calander.getTime().getYear());
        p=new Person(name,birthday,nextbirthday,comment);

        db.addName(p);
    }
}
