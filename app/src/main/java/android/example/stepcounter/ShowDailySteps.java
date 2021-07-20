package android.example.stepcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class ShowDailySteps extends AppCompatActivity {


   ListView listView;
   // TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_daily_steps);
       // tv=findViewById(R.id.tv);
        listView=findViewById(R.id.list);
        Calendar calendar = Calendar.getInstance();
        String currDate = DateFormat.getDateInstance(DateFormat.FULL).format(Calendar.getInstance().getTime());
       // Date date = new Date();
        ArrayList<DateAndStep> ds = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);
        int value = sharedPreferences.getInt("HS",10);
       /* ds.add(new DateAndStep(date.toString(),value));*/
        //Add all entries from ArrayList to a list
        //tv.setText(ds.toString());
        ds.add(new DateAndStep(currDate,value));

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,ds);
        listView.setAdapter(arrayAdapter);










    }
}

class  DateAndStep
{
    String date;
    int step;
    DateAndStep(String date,int step) {
        this.date = date;
        this.step = step;
    }

        public String toString()
        {
            return  date + "        " + step+" Steps ";
        }
    }
