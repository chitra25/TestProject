package com.hfad.testproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        button=(Button)findViewById(R.id.button);
        TextView textview5=(TextView)findViewById(R.id.textview5);
        TextView textview6=(TextView)findViewById(R.id.textview6);
        ImageView imageview2=(ImageView)findViewById(R.id.imageview3);
        editText=(EditText)findViewById(R.id.edit_text);
        DataDetails dd=(DataDetails)(getIntent().getSerializableExtra("details"));
        textview5.setText(dd.data_text1);
        textview6.setText(dd.data_text2);
        imageview2.setImageResource(dd.data_image);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                String text=editText.getText().toString();
                intent.putExtra("textvalue", text);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }
}
