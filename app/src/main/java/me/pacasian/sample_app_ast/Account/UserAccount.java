package me.pacasian.sample_app_ast.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import me.pacasian.sample_app_ast.R;

public class UserAccount extends AppCompatActivity {
Button btn;
TextView txt;
ImageView img;
int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        btn=findViewById(R.id.button3);
        img=findViewById(R.id.button2);
        txt=findViewById(R.id.textView);
        String st=txt.getText().toString();
        //Toast.makeText(this, st+"", Toast.LENGTH_SHORT).show();
        if (st.equals("0")||st.equals("")){
            txt.setVisibility(View.INVISIBLE);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=i+1;
                if(i>0) {
                    txt.setText(i+"");
                    txt.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}