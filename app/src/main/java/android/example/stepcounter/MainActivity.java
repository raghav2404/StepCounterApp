package android.example.stepcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    EditText name;
    EditText Password;
    Button SignIn;
    Button SignUp;
    SQLiteDatabase db;


String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.editText1);
        Password = findViewById(R.id.editText2);
        SignIn = findViewById(R.id.signin);
        SignUp = findViewById(R.id.signup);

      db=openOrCreateDatabase("Biodata",MODE_PRIVATE,null);
      //create table(create query)
        db.execSQL("create table IF NOT EXISTS form(age varchar(20),height varchar(20),weight varchar(20),gender varchar(20),pno varchar(20),mail varchar(20),pass varchar(20))");
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendTOtabs();
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, registration.class);
                startActivity(intent);
            }


        });
    }

    private void sendTOtabs() {
        username=name.getText().toString().trim();
        password=Password.getText().toString().trim();
        //login query
        Cursor cursor=db.rawQuery("select mail,pass from form where mail='"+username+"' and pass='"+password+"'",null);
        if (cursor.moveToNext())
        {
            Intent obj =new Intent(MainActivity.this,tabs.class);
            startActivity(obj);
        }
        else
            Toast.makeText(getApplicationContext(),"Incorrect username or Password",Toast.LENGTH_LONG).show();
            }
}

