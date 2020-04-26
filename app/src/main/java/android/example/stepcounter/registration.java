package android.example.stepcounter;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.Toolbar;

public class registration extends AppCompatActivity {

    EditText age,height,weight,pno,mail,pass,cnfpass;
    RadioGroup radioGroup;
    RadioButton m,f;
    Button submit;
    SQLiteDatabase db;
    String age1,height1,weight1,mail1,pass1,cnfpass1;
    String pno1;
    String gen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Registration");
        age=findViewById(R.id.age);
        height=findViewById(R.id.height);
        weight=findViewById(R.id.weight);
        mail=findViewById(R.id.mail);
        pno=findViewById(R.id.pno);
        pass=findViewById(R.id.pass);
        cnfpass=findViewById(R.id.confpass);
        radioGroup=findViewById(R.id.gender);
        m=findViewById(R.id.male);
        f=findViewById(R.id.female);
        submit=findViewById(R.id.submit);
        db=openOrCreateDatabase("Biodata",MODE_PRIVATE,null);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignData();
            }
        });


    }

    private void assignData() {
        //get fields from edittexts
         age1=age.getText().toString().trim();
         height1=height.getText().toString().trim();
         weight1=weight.getText().toString().trim();
         mail1=mail.getText().toString().trim();
         pass1=pass.getText().toString().trim();
         cnfpass1=cnfpass.getText().toString().trim();
         pno1=pno.toString().trim();

        if(m.isChecked())
            gen="Male";
        else if(f.isChecked())
            gen="Female";
        if(age1.equals("")|| height1.equals("")||weight1.equals("")||gen.equals("")||pno1.equals("")||mail1.equals("")||pass1.equals(""))
             Toast.makeText(this,"Enter all the details",Toast.LENGTH_LONG).show();
     else   if(!pass1.equals(cnfpass1))
            Toast.makeText(this,"Password does not match",Toast.LENGTH_LONG).show();
        else if(!age1.matches("[0-9]+"))
            Toast.makeText(this,"Enter a valid age",Toast.LENGTH_LONG).show();
        else if (!height1.matches("[0-9]+"))
            Toast.makeText(this,"Enter a valid height",Toast.LENGTH_LONG).show();
      //  else  if(!weight1.matches("[0-9]+.[0-9]+"))
        else  if(!weight1.matches("^\\d+?\\.\\d+?$"))
            Toast.makeText(this,"Enter a valid weight",Toast.LENGTH_LONG).show();





        else
        {
            //insert query
db.execSQL("insert into form values('"+age1+"','"+height1+"','"+weight1+"','"+gen+"','"+pno1+"','"+mail1+"','"+pass1+"')");
Intent intent=new Intent(registration.this,MainActivity.class);
startActivity(intent);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuback,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        backTologinPage();
        return super.onOptionsItemSelected(item);
    }

    private void backTologinPage() {
        Intent intent=new Intent(registration.this,MainActivity.class);
        startActivity(intent);
    }
}
