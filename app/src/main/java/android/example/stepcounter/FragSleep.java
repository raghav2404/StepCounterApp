package android.example.stepcounter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragSleep extends Fragment {
    SQLiteDatabase db1;
    private TextView textView33,textView44,textView55;
    private EditText age2,height2,weight2;
    private String age11,height11,weight11;
    private Button determine1,edit1;
    private TextView dailyIntake2,hours;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_sleep, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView33=(TextView)getActivity().findViewById(R.id.textView3);
        textView44=(TextView)getActivity().findViewById(R.id.textView4);
        textView55=(TextView)getActivity().findViewById(R.id.textView5);
        age2=(EditText) getActivity().findViewById(R.id.ageA);
        weight2=(EditText)getActivity().findViewById(R.id.weightA);
        height2=(EditText)getActivity().findViewById(R.id.heightA);
        determine1=(Button)getActivity().findViewById(R.id.determine2);
        dailyIntake2=getActivity().findViewById(R.id.dailyIntake2);
        hours=getActivity().findViewById(R.id.hours);
        edit1=(Button)getActivity().findViewById(R.id.edit2);
        db1=getActivity().openOrCreateDatabase("Biodata", Context.MODE_PRIVATE,null);
        Cursor cursor1=db1.rawQuery("Select *from form",null);
       while (cursor1.moveToNext()) {
           age11 = cursor1.getString(0);
           height11 = cursor1.getString(1);
           weight11 = cursor1.getString(2);
       }

        age2.setText( "  " +age11.trim());
        height2.setText("  " +height11.trim());
        weight2.setText("  "+weight11.trim());
        age2.setEnabled(false);
        height2.setEnabled(false);
        weight2.setEnabled(false);
        dailyIntake2.setVisibility(View.INVISIBLE);
        hours.setVisibility(View.INVISIBLE);

        edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age2.setEnabled(true);
                height2.setEnabled(true);
                weight2.setEnabled(true);
            }
        });
        determine1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcSleepIntake();
            }
        });









    }
    private void calcSleepIntake()
    {
        age2.setEnabled(false);
        height2.setEnabled(false);
        weight2.setEnabled(false);
        String getAGE=age2.getText().toString().trim();
        int age;
        int sleepIntake = 0;
        age=Integer.parseInt(getAGE);
        if(age<=1)
            sleepIntake=14;
        else if(age>1&&age<=2)
            sleepIntake=13;
        else if(age>2&&age<=3)
            sleepIntake=12;
        else if(age>3&&age<=5)
            sleepIntake=11;
        else if(age>5&&age<=13)
            sleepIntake=10;
        else if(age>13&&age<17)
            sleepIntake=9;
        else if(age>17&&age<=25)
            sleepIntake=8;
        else if(age>25&&age<=64)
            sleepIntake=7;
        else if(age>64)
            sleepIntake=7;



  hours.setText(sleepIntake+" Hrs");
        hours.setVisibility(View.VISIBLE);
  dailyIntake2.setVisibility(View.VISIBLE);

    }

}
