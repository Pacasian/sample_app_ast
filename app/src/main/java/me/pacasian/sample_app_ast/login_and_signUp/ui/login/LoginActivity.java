package me.pacasian.sample_app_ast.login_and_signUp.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.arturogutierrez.Badges;
import com.github.arturogutierrez.BadgesNotSupportedException;

import me.pacasian.sample_app_ast.R;


public class LoginActivity extends AppCompatActivity {
Button btn;

TextView txt_login,txt_register;
LinearLayout layout_login,layout_register;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
btn=findViewById(R.id.r_Button);
btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        try {
            Badges.removeBadge(LoginActivity.this);
            // Alternative way
            Badges.setBadge(LoginActivity.this, 10);
            Toast.makeText(LoginActivity.this, "Done", Toast.LENGTH_SHORT).show();
        } catch (BadgesNotSupportedException badgesNotSupportedException) {
            System.out.println("-------------"+badgesNotSupportedException.getMessage());
            Toast.makeText(LoginActivity.this, "Not Done", Toast.LENGTH_SHORT).show();
        }
    }
});
        txt_login=findViewById(R.id.textView_login);
        txt_register=findViewById(R.id.textView_register);
        layout_login=findViewById(R.id.layout1);
        layout_register=findViewById(R.id.layout2);;
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout_login.setVisibility(View.VISIBLE);
                layout_register.setVisibility(View.INVISIBLE);
                txt_login.setBackgroundResource(R.drawable.curved_bordered_rectangle);
                txt_register.setBackgroundResource(R.drawable.sample_background);
            }
        });
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout_login.setVisibility(View.INVISIBLE);
                layout_register.setVisibility(View.VISIBLE);
                txt_register.setBackgroundResource(R.drawable.curved_bordered_rectangle);
                txt_login.setBackgroundResource(R.drawable.sample_background);
            }
        });
    }
}