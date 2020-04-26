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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragWater extends Fragment {
    private SQLiteDatabase db;
    private TextView textView3,textView4,textView5;
    private EditText age,height,weight;
    private String age1,height1,weight1;
    private Button determine,edit;
    private TextView litres,dailyIntake;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_water, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView3 = (TextView) getActivity().findViewById(R.id.a);
        textView4 = (TextView) getActivity().findViewById(R.id.w);
        textView5 = (TextView) getActivity().findViewById(R.id.h);
        litres= (TextView)getActivity().findViewById(R.id.litres);
        dailyIntake=(TextView)getActivity().findViewById(R.id.dailyIntake);

        age = (EditText) getActivity().findViewById(R.id.ageB);
        weight = (EditText) getActivity().findViewById(R.id.weightB);
        height = (EditText) getActivity().findViewById(R.id.heightB);
        determine = (Button) getActivity().findViewById(R.id.determine);
        edit = (Button) getActivity().findViewById(R.id.edit);
        db = getActivity().openOrCreateDatabase("Biodata", Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("Select *from form", null);
        while (cursor.moveToNext()) {
            age1 = cursor.getString(0);
            height1 = cursor.getString(1);
            weight1 = cursor.getString(2);
        }

        cursor.close();
        age.setText("  " + age1.trim());
        height.setText("  " + height1.trim());
        weight.setText("  " + weight1.trim());
        age.setEnabled(false);
        height.setEnabled(false);
        weight.setEnabled(false);
        litres.setVisibility(View.INVISIBLE);
        dailyIntake.setVisibility(View.INVISIBLE);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age.setEnabled(true);
                height.setEnabled(true);
                weight.setEnabled(true);
            }
        });
        determine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcWaterIntake();
            }
        });


    }

    private void calcWaterIntake()
    {
        age.setEnabled(false);
        height.setEnabled(false);
        weight.setEnabled(false);
String acw=weight.getText().toString().trim();
Float acwV=Float.parseFloat(acw);
double waterIntake_W=0;
if(acwV<=30)
    waterIntake_W=1.0;
else if(acwV>30&&acwV<=45)
    waterIntake_W=1.5;
else if(acwV>45&&acwV<=49.9)
    waterIntake_W=1.98;
else if(acwV>49.9&&acwV<=54.9)
    waterIntake_W=2.1;
else if (acwV>54.9&&acwV<=58.9)
waterIntake_W=2.5;
else if (acwV>58.9&&acwV<=63.5)
    waterIntake_W=2.7;
else if(acwV>63.5&&acwV<=68.9)
    waterIntake_W=2.9;
else if(acwV>68.9&&acwV<=72.57)
    waterIntake_W=3.1;
else if (acwV>72.57&&acwV<=77)
    waterIntake_W=3.3;
else if(acwV>77&&acwV<=81.6)
    waterIntake_W=3.5;
else if(acwV>81.6&&acwV<=86)
    waterIntake_W=3.75;
else if(acwV>86&&acwV<=90.7)
    waterIntake_W=3.9;
else if(acwV>90.7&&acwV<=95.25)
    waterIntake_W=4.1;
else if(acwV>95.25&&acwV<=99.79)
    waterIntake_W=4.3;
else if(acwV>99.79&&acwV<=104.3)
    waterIntake_W=4.55;
else if(acwV>104.3&&acwV<=108.86)
    waterIntake_W=4.76;
else if (acwV>108.86&&acwV<=115)
    waterIntake_W=4.96;
else if (acwV>115&&acwV<=150)
    waterIntake_W=5;
else if(acwV>150)
    waterIntake_W=6;
else
    Toast.makeText(getActivity(),"Enter Proper Details",Toast.LENGTH_SHORT).show();
litres.setText(waterIntake_W+" Litres");
litres.setVisibility(View.VISIBLE);
dailyIntake.setVisibility(View.VISIBLE);



    }
}