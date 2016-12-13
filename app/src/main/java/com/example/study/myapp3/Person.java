package com.example.study.myapp3;

import android.widget.DatePicker;

import java.util.Date;

/**
 * Created by study on 04/12/2016.
 */
public class Person {

    private String Name,Comments;
    Date Birthday,CommingBirthday;



    public Person(String Name, Date Birthday, Date CommingBirthday, String Comments)
    {
        this.Name=Name;
        this.Birthday=Birthday;
        this.CommingBirthday=CommingBirthday;
        this.Comments=Comments;
    }

    void setName(String name)
    {
        this.Name=name;

    }
    void setComments(String Comments)
    {
        this.Comments=Comments;

    }
    void setBirthday(Date Birthday)
    {
        this.Birthday=Birthday;
    }
    void setNextBirthday(Date CommingBirthday)
    {
        this.CommingBirthday=CommingBirthday;
    }


    String getName()
    {
        return this.Name;
    }
    String getComments()
    {
        return this.Comments;
    }


    Date getBirthday()
    {
        return this.Birthday;
    }
    Date getNextBirthday()
    {
        return this.CommingBirthday=CommingBirthday;
    }

}
