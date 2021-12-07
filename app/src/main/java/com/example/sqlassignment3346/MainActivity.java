package com.example.sqlassignment3346;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name,roll_no;
    Button update, view, delete, insert;
    TextView show;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button update= (Button) findViewById(R.id.btnupdate) ;
        Button insert= (Button) findViewById(R.id.btninsert) ;
        Button view= (Button) findViewById(R.id.btnview) ;
        Button delete= (Button) findViewById(R.id.btndelete) ;

        EditText name= (EditText) findViewById(R.id.etname);
        EditText roll_no= (EditText) findViewById(R.id.etroll);

        TextView show= (TextView) findViewById(R.id.textView);

        db= new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s_name= name.getText().toString();
                String s_roll= roll_no.getText().toString();

                boolean checkinsert= db.insertdata(s_roll, s_name);
                if(checkinsert)
                    Toast.makeText(MainActivity.this, "Insertion successful!", Toast.LENGTH_LONG);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s_name= name.getText().toString();
                String s_roll= roll_no.getText().toString();

                boolean checkdel= db.deletedata(s_name);
                if(checkdel)
                    Toast.makeText(MainActivity.this, "Deletion of"+name+ "successful!", Toast.LENGTH_LONG);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s_name= name.getText().toString();
                String s_roll= roll_no.getText().toString();

                boolean checkupdate= db.updatedata(s_roll,s_name);

                if(checkupdate)
                    Toast.makeText(MainActivity.this, "Updation of"+roll_no+"to"+name+"successful!", Toast.LENGTH_LONG);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s_name= name.getText().toString();
                String s_roll= roll_no.getText().toString();

                Cursor res= db.getdata();

                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No data in Table", Toast.LENGTH_LONG);
                    return;
                }
                StringBuffer sb= new StringBuffer();

                while(res.moveToNext())
                {
                    sb.append("Roll no: "+res.getString(0)+"\t");
                    sb.append("Name   : "+res.getString(1)+"\t");
                    sb.append("\n");
                }

                show.setText(sb.toString());

            }
        });
    }
}