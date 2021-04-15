package com.khalid.projectaandroid.AddSubject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.khalid.projectaandroid.R;

import java.io.File;

public class addsubject extends AppCompatActivity {

    TextView txtsubject;
    Button btnsubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_subject);
        txtsubject = findViewById(R.id.Textsubject);
        btnsubject = findViewById(R.id.buttselectSu);


        btnsubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent file=new Intent(Intent.ACTION_GET_CONTENT);
                file.setType("application/*");
                startActivityForResult(file,10);




            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 10:
                if (resultCode == RESULT_OK) {

                    String path = data.getData().getLastPathSegment();

                    File flt = new File(path);

                    String nomFile= flt.getName();
                    txtsubject.setText(nomFile);
                }

                break;
        }

    }


}
