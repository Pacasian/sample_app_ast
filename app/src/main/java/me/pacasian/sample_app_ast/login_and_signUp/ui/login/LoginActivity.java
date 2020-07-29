package me.pacasian.sample_app_ast.login_and_signUp.ui.login;

import android.annotation.SuppressLint;
import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.readystatesoftware.viewbadger.BadgeView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

//import me.pacasian.sample_app_ast.BackgroundWorker;
import me.pacasian.sample_app_ast.DatabaseConnection;
import me.pacasian.sample_app_ast.Home.Home;
import me.pacasian.sample_app_ast.R;
import me.pacasian.sample_app_ast.SharedPreference;

import static android.widget.Toast.LENGTH_LONG;


public class LoginActivity extends AppCompatActivity {
Button R_btn,L_btn;
TextInputEditText L_username,L_password, R_username,R_password,R_email,R_con_pass;
TextInputLayout til1,til2,til3,til4,til5,til6;
TextView txt_login,txt_register;
LinearLayout layout_login,layout_register;
Connection con;
private SharedPreference sharedPref;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txt_login=findViewById(R.id.textView_login);
        txt_register=findViewById(R.id.textView_register);
        layout_login=findViewById(R.id.layout1);
        layout_register=findViewById(R.id.layout2);
        L_username=findViewById(R.id.l_username);
        L_password=findViewById(R.id.l_password);
        R_username=findViewById(R.id.r_username);
        R_password=findViewById(R.id.r_password);
        R_email=findViewById(R.id.r_email);
        R_con_pass=findViewById(R.id.r_c_password);
        R_btn=findViewById(R.id.r_Button);
        L_btn=findViewById(R.id.l_button);
        con=new DatabaseConnection().ConnectDB();
        sharedPref=SharedPreference.getInstance(getApplicationContext());

        til1=findViewById(R.id.til1);
        til2=findViewById(R.id.til2);
        til3=findViewById(R.id.til3);
        til4=findViewById(R.id.til4);
        til5=findViewById(R.id.til5);
        til6=findViewById(R.id.til6);
        til1.setError(null);
        til2.setError(null);
        til3.setError(null);
        til4.setError(null);
        til5.setError(null);
        til6.setError(null);
        if (con != null) {
            Toast.makeText(LoginActivity.this, "Connection valid", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(LoginActivity.this, "Connection invalid", Toast.LENGTH_SHORT).show();
        }
        L_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stPass= Objects.requireNonNull(L_password.getText()).toString();
                String stUser= Objects.requireNonNull(L_username.getText()).toString();
                //Toast.makeText(LoginActivity.this, stPass+"__"+stUser, Toast.LENGTH_SHORT).show();
                if (stUser.equals("")||stPass.equals("")){
                    til1.setError("Empty String");
                    til2.setError("Empty String");
                    til1_and_til2_changeText();
                }
                else{
                    //startActivity(new Intent(LoginActivity.this, Home.class));
                    String type = "login";
                    Login login=new Login();
                    login.execute("");

                }
            }
        });
        R_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stEmail= Objects.requireNonNull(R_email.getText()).toString();
                String stUser= Objects.requireNonNull(R_username.getText()).toString();
                String stPass= Objects.requireNonNull(R_password.getText()).toString();
                String stCPass= Objects.requireNonNull(R_con_pass.getText()).toString();

                //Toast.makeText(LoginActivity.this, stPass+"__"+stUser, Toast.LENGTH_SHORT).show();
                if (stUser.equals("")||stPass.equals("")||stEmail.equals("")||stCPass.equals("")){
                    til3.setError("null");
                    til4.setError("null");
                    til5.setError("null");
                    til6.setError("null");
                    til3_to_til6_changeText();
                }
                else{
                    if(stPass.equals(stCPass)) {
                        String type = "register";
                        RegisterUser registerUser=new RegisterUser();
                        registerUser.execute("");
                    }else{
                        til5.setError("not matching..");
                        til6.setError("not matching..");
                    }
                }
            }

        });
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout_login.setVisibility(View.VISIBLE);
                layout_register.setVisibility(View.INVISIBLE);
                txt_login.setBackgroundResource(R.drawable.curved_bordered_rectangle);
                txt_register.setBackgroundResource(R.drawable.sample_background);
                R_username.setText(null);
                R_password.setText(null);
                R_email.setText(null);
                R_con_pass.setText(null);
                til3.setError(null);
                til4.setError(null);
                til5.setError(null);
                til6.setError(null);

            }
        });

        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout_login.setVisibility(View.INVISIBLE);
                layout_register.setVisibility(View.VISIBLE);
                txt_register.setBackgroundResource(R.drawable.curved_bordered_rectangle);
                txt_login.setBackgroundResource(R.drawable.sample_background);
                L_username.setText(null);
                L_password.setText(null);
                til1.setError(null);
                til2.setError(null);
            }
        });
    }
  public void til1_and_til2_changeText(){
      til1.getEditText().addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {

          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
              if (s.length() < 1) {
                  til1.setErrorEnabled(true);
                  til1.setError("Please enter a value");
              }

              if (s.length() > 0) {
                  til1.setError(null);
                  til1.setErrorEnabled(false);
              }

          }

          @Override
          public void afterTextChanged(Editable s) {

          }
      });

      til2.getEditText().addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {

          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
              if (s.length() < 1) {
                  til2.setErrorEnabled(true);
                  til2.setError("Please enter a value");
              }

              if (s.length() > 0) {
                  til2.setError(null);
                  til2.setErrorEnabled(false);
              }

          }

          @Override
          public void afterTextChanged(Editable s) {

          }
      });
  }
    public void til3_to_til6_changeText(){
        til3.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 1) {
                    til3.setErrorEnabled(true);
                    til3.setError("Please enter a value");
                }

                if (s.length() > 0) {
                    til3.setError(null);
                    til3.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        til4.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 1) {
                    til4.setErrorEnabled(true);
                    til4.setError("Please enter a value");
                }

                if (s.length() > 0) {
                    til4.setError(null);
                    til4.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        til5.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 1) {
                    til5.setErrorEnabled(true);
                    til5.setError("Please enter a value");
                }

                if (s.length() > 0) {
                    til5.setError(null);
                    til4.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        til6.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 1) {
                    til6.setErrorEnabled(true);
                    til6.setError("Please enter a value");
                }

                if (s.length() > 0) {
                    til6.setError(null);
                    til6.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public  class Login extends AsyncTask<String,Integer,String>
    {
        String z = "";
        Boolean isSuccess = false;
        ProgressDialog progress;
        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(LoginActivity.this, "Synchronising",
                    "Loading! Please Wait...", true);
        }

        @Override
        protected void onPostExecute(String r) {
            progress.dismiss();
            if(isSuccess)
            {
                //CHECK IF BIO ALREADY FILLED
                if(r.equals("1")){
                    Toast.makeText(LoginActivity.this, "Unquies", Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this, "Login Successful", LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this,Home.class));
                }else{
                    Toast.makeText(LoginActivity.this,"Login Failed Due to Server error ", Toast.LENGTH_SHORT).show();
                }
            }

        }

        @SuppressLint("SetTextI18n")
        @Override
        protected String doInBackground(String... params) {
            // @SuppressLint("WrongThread") String usernam = userTv.getText().toString();
            //@SuppressLint("WrongThread") String passwordd = passwordTv.getText().toString();

            try {
                // Connect to database
                if (con == null) {
                    z = "Check Your Internet Access!";
                } else {
                    String stPass= Objects.requireNonNull(L_password.getText()).toString();
                    String stUser= Objects.requireNonNull(L_username.getText()).toString();
                    String query = "SELECT * FROM userData WHERE username='"+stUser+"' and password ='"+stPass+"';";

                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    if (rs != null)
                    {
                        while (rs.next())
                        {
                            try {
                                String phoneNumber = rs.getString("username");
                                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                                int rows = rs.getRow();
                                z=rows+"";
                                System.out.println(rows+"");
                                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        isSuccess = true;
                    } else {
                        isSuccess = false;
                        z="invalid username";
                    }
                }
            } catch (Exception ex) {
                isSuccess = false;
                z = ex.getMessage();
            }

            return z;


        }

    }
    public  class RegisterUser extends AsyncTask<String,Integer,String>
    {
        String message = "Can't connect to server, try again";
        Boolean isSuccess = false;
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(LoginActivity.this, "Synchronising",
                    "Entering! Please Wait...", true);
        }

        @Override
        protected String doInBackground(String... strings) {

            try
            {
                if (con == null)
                {
                    message = "Check Your Internet Access!";
                }
                else
                {
                    String stEmail= Objects.requireNonNull(R_email.getText()).toString();
                    String stUser= Objects.requireNonNull(R_username.getText()).toString();
                    String stPass= Objects.requireNonNull(R_password.getText()).toString();

                    //FOR PUSHING DATA TO bioData
                    String query =" INSERT INTO userData (email,username,password)VALUES ('"+stEmail+"','"+stUser+"','"+stPass+"') ;";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(query);
                    isSuccess=true;
                }
            }
            catch (Exception ex)
            {
                isSuccess = false;
                message = ex.getMessage();
            }
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            progress.dismiss();
            if(isSuccess)
            {
                Toast.makeText(LoginActivity.this , "Data Added Successfully, PLease login Again." , Toast.LENGTH_LONG).show();
                //sharedPref.putInt("BIOFILLED",1);

            }
            else
                Toast.makeText(LoginActivity.this,message, Toast.LENGTH_LONG).show();
        }

    }
}
/*
    public  class Login extends AsyncTask<String,Integer,String>
    {
        String message = "Can't connect to server, try again";
        Boolean isSuccess = false;
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(LoginActivity.this, "Synchronising",
                    "Loading! Please Wait...", true);
        }

        @Override
        protected String doInBackground(String... strings) {

            try
            {


                    String stPass= Objects.requireNonNull(L_password.getText()).toString();
                    String stUser= Objects.requireNonNull(L_username.getText()).toString();
                    String query ="SELECT * FROM userData WHERE username='"+stUser+"' and password ='"+stPass+"';";
                    Statement stmt = con.createStatement();
                    ResultSet resultSet = stmt.executeQuery(query);

                    if (resultSet != null)
                    {
                        while (resultSet.next())
                        {
                            try {
                                String st = resultSet.getString("username");
                                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                                System.out.println(message);
                                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                                System.out.println(ex+"kl");
                                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                            }
                        }
                        isSuccess = true;
                    } else {
                        isSuccess = false;
                    }

            }
            catch (Exception ex)
            {
                isSuccess = false;
                message = ex.getMessage();
            }
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            progress.dismiss();
            if(isSuccess)
            {
                //CHECK IF BIO ALREADY FILLED
                if(s.equals("1")){
                    Toast.makeText(LoginActivity.this, "Unquies", Toast.LENGTH_SHORT).show();
                }else if(s.equals("")){
                    Toast.makeText(LoginActivity.this, "nothing", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this, s+"hai", Toast.LENGTH_SHORT).show();
                }
            }
            else
                Toast.makeText(LoginActivity.this,message, Toast.LENGTH_LONG).show();
        }

    }
 */