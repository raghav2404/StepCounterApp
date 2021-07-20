package android.example.stepcounter;

import android.content.Context;
import android.content.Intent;
import android.example.stepcounter.R;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.os.Bundle;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

import kotlin.Unit;

import static androidx.core.content.ContextCompat.getSystemService;

public class FragSteps extends Fragment {


    public TextView tv,tv1;
    SensorManager sensorManager;
    private double magni;
    private Integer stepcount = 0;
    Button reset;
    Button dailySteps;
   // SharedPreferences dailyStep;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_steps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv1=(TextView)getActivity().findViewById(R.id.text);
        reset=(Button)getActivity().findViewById(R.id.reset) ;
        dailySteps=(Button)getActivity().findViewById(R.id.dailySteps);
    tv =(TextView)getActivity().findViewById(R.id.tv);
        sensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
        CircularProgressBar circularProgressBar =getActivity().findViewById(R.id.circularProgressBar);

        circularProgressBar.setProgressDirection(CircularProgressBar.ProgressDirection.TO_RIGHT);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        circularProgressBar.setOnIndeterminateModeChangeListener(isEnable -> {
            // Do something
            return Unit.INSTANCE;
        });

        circularProgressBar.setOnProgressChangeListener(progress -> {
            // Do something
            return Unit.INSTANCE;
        });

        SensorEventListener obj = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent != null) {
                    float x_accelerometer = sensorEvent.values[0];
                    float y_accelerometer = sensorEvent.values[1];
                    float z_accelerometer = sensorEvent.values[2];

                    double magnitute = Math.sqrt(x_accelerometer * x_accelerometer + y_accelerometer * y_accelerometer + z_accelerometer * z_accelerometer);
                    double magniDelta = magnitute - magni;
                    magni = magnitute;
                    if (magniDelta > 6) {
                        stepcount++;
                    }
                    Calendar A= Calendar.getInstance();
                    int a=A.get(Calendar.HOUR_OF_DAY);
                    int b=A.get(Calendar.MINUTE);


                    if(a==0&&b==00) {
                       SharedPreferences dailyStep=getActivity().getSharedPreferences("myPref",Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed1=dailyStep.edit();
                        //Set<String> ss=new HashSet<String>();
                       // ss.add(new Date().toString());
                        //ss.add(stepcount.toString());
                        ed1.putInt("HS",stepcount);
                        ed1.apply();

                        stepcount = 0;
                    }
                    tv.setText(stepcount.toString());
                    circularProgressBar.setProgressWithAnimation(stepcount, (long) 50);
                    circularProgressBar.setProgressMax(5000f);
                    reset.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            stepcount=0;

                        }
                    });

                    dailySteps.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(),ShowDailySteps.class);
                            intent.putExtra("todaysteps",stepcount);
                            startActivity(intent);
                        }
                    });

                }
            }


            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        sensorManager.registerListener(obj, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences =this.getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.clear();
        ed.putInt("X", stepcount);
        ed.apply();
    }
    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences =this.getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.clear();
        ed.putInt("X", stepcount);
        ed.apply();
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences =this.getActivity().getPreferences(Context.MODE_PRIVATE);
        stepcount = sharedPreferences.getInt("X", 0);
    }

}
