package me.pacasian.sample_app_ast.login_and_signUp.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
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
import java.util.Objects;

//import me.pacasian.sample_app_ast.BackgroundWorker;
import me.pacasian.sample_app_ast.Home.Home;
import me.pacasian.sample_app_ast.R;



public class LoginActivity extends AppCompatActivity {
Button R_btn,L_btn;
TextInputEditText L_username,L_password, R_username,R_password,R_email,R_con_pass;
TextInputLayout til1,til2,til3,til4,til5,til6;
TextView txt_login,txt_register;
LinearLayout layout_login,layout_register;
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
        BadgeView badge = new BadgeView(this, L_btn);
        badge.setText("2");
        badge.toggle(true);
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
                    BackgroundWorker backgroundWorker = new BackgroundWorker(LoginActivity.this);
                    backgroundWorker.execute(type, stUser, stPass);

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
                        BackgroundWorker2 backgroundWorker2 = new BackgroundWorker2(LoginActivity.this);
                        backgroundWorker2.execute(type, stEmail, stUser, stPass);
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

    public class BackgroundWorker extends AsyncTask<String,Void,String> {
        Context context;
        AlertDialog alertDialog;


        public BackgroundWorker(Context ctx) {
            context = ctx;
        }
        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String login_url = "http://192.168.1.8/login.php";
            if(type.equals("login")) {
                try {
                    String user_name = params[1];
                    String password = params[2];
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                            +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line="";
                    while((line = bufferedReader.readLine())!= null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Login Status");
        }

        @Override
        protected void onPostExecute(String result) {

            System.out.println("result----------------------------------------------------------------");
            System.out.println(result);

            if(result.equals("Done2")) {
                startActivity(new Intent(LoginActivity.this, Home.class));
                finish();
            }else{
                alertDialog.setMessage("Failed");
                alertDialog.show();
            }

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
    public class BackgroundWorker2 extends AsyncTask<String,Void,String> {
        Context context;
        AlertDialog alertDialog;


        public BackgroundWorker2(Context ctx) {
            context = ctx;
        }
        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String register_url = "http://192.168.1.8/register.php";
            if(type.equals("register")) {
                try {
                    String user_email = params[1];
                    String user_name = params[2];
                    String password = params[3];
                    URL url = new URL(register_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("user_email","UTF-8")+"="+URLEncoder.encode(user_email,"UTF-8")+"&"
                            +URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                            +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line="";
                    while((line = bufferedReader.readLine())!= null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Register Status");
        }

        @Override
        protected void onPostExecute(String result) {

            System.out.println("result----------------------------------------------------------------");
            System.out.println(result);

            if(result.equals("Done3")) {
                alertDialog.setMessage("Success");
                alertDialog.show();
            }else{
                alertDialog.setMessage("Failed");
                alertDialog.show();
            }

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}