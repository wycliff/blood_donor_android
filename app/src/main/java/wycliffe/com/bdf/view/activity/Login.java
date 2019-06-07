package wycliffe.com.bdf.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wycliffe.com.bdf.R;
import wycliffe.com.bdf.model.LoginResponseModel;
import wycliffe.com.bdf.other.SessionManager;
import wycliffe.com.bdf.rest.ApiClient;
import wycliffe.com.bdf.rest.LoginApiInterface;

public class Login extends AppCompatActivity {

    // view
    EditText etEmail, etPassword;
    Button btnLogin, btnToReg;
    CheckBox cbShowPassword;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword;

    //other
    String email, password;
    ProgressDialog progressDialog;
    SessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.buttonLogin);
        btnToReg = findViewById(R.id.btnToRegister);
        btnToReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                Login.this.finish();
            }
        });


        session = new SessionManager(getApplicationContext());
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        cbShowPassword = findViewById(R.id.showPassword);

        cbShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        inputLayoutEmail = findViewById(R.id.input_layout_email);
        inputLayoutPassword = findViewById(R.id.input_layout_password);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = etEmail.getText().toString().trim();
                password = etPassword.getText().toString().trim();

                if (email.trim().length() > 0 && password.trim().length() > 0) {


                    progressDialog = new ProgressDialog(Login.this);
                    progressDialog.setMax(100);
                    progressDialog.setMessage("Its loading....");
                    progressDialog.setTitle("Fetching Server Data");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.show();

                    LoginApiInterface apiService = ApiClient.getClient().create(LoginApiInterface.class);
                    Call<LoginResponseModel> call = apiService.getLogged(email, password);
                    call.enqueue(new Callback<LoginResponseModel>() {
                        @Override
                        public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {

                            progressDialog.dismiss();

                            if (response.code() == 200) {
                                String emailResponse = response.body().getEmail();
                                Toast.makeText(Login.this, "Welcome" + response.body().getFullName(), Toast.LENGTH_SHORT).show();


                                session.createLoginSession(response.body());

                                Intent in = new Intent(Login.this, HomeActivity.class);
                                startActivity(in);
                                Login.this.finish();
                            } else {
                                Toast.makeText(Login.this, " Server error", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponseModel> call, Throwable t) {

                            progressDialog.dismiss();
                            Toast.makeText(Login.this, " Failed to establish connection to server ", Toast.LENGTH_SHORT).show();


                        }
                    });


                } else {
                    if (email.trim().length() <= 0 && password.trim().length() <= 0) {
                        inputLayoutEmail.setError("Email field cannot be empty");
                        inputLayoutPassword.setError("Password Required");
                    } else if (password.trim().length() <= 0) {
                        inputLayoutPassword.setError("Password Required");
                    } else {
                        inputLayoutEmail.setError("Email field cannot be empty");

                    }
                }


            }
        });


    }

    private void initViews(){


    }

    private boolean validateEmail() {
        String email = etEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError("Enter the email");
            //requestFocus(etEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (etPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError("Enter the password");
            //requestFocus(etPassword);
            return false;
        } else {

            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    private class MyTextWatcher implements TextWatcher {

        public View view;

        public MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.etEmail:
                    validateEmail();
                    break;
                case R.id.etPassword:
                    validatePassword();
                    break;
            }
        }
    }
}
