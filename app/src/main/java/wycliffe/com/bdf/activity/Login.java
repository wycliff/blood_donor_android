package wycliffe.com.bdf.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
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
import wycliffe.com.bdf.model.LoginModel;
import wycliffe.com.bdf.rest.ApiClient;
import wycliffe.com.bdf.rest.LoginApiInterface;

public class Login extends AppCompatActivity {


    EditText etEmail,etPassword;
    Button btnLogin, btnToReg;
    CheckBox cbShowPassword;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword;
    String email, password;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.buttonLogin);
        btnToReg = (Button) findViewById(R.id.btnToRegister);
        btnToReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
                Login.this.finish();
            }
        });


//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                //intent which to next
//                Intent in = new Intent(Login.this,MainActivity.class);
//                startActivity(in);
//                Login.this.finish();
//            }
//        });




//----------------------------------------------------------------------------------------------------------------------------------------

        //Getting the email from user
        etEmail = (EditText) findViewById(R.id.etEmail);

        //Getting the password from user
        etPassword = (EditText) findViewById(R.id.etPassword);

        //Getting the login Button
        btnLogin = (Button)findViewById(R.id.buttonLogin);


//-------------------------------------------The check box---------------------------------------------------------------------------
        //show password check box
        cbShowPassword = (CheckBox) findViewById(R.id.showPassword);

        cbShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                else {
                    // hide password
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

//==========================================Handling foalting Labels=============================================================================

        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);

        //All the <TextInput> editables
//        etEmail.addTextChangedListener(new MyTextWatcher(etEmail));
//        etPassword.addTextChangedListener(new MyTextWatcher(etPassword));

//============== REST



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //saving them into a String
                email = etEmail.getText().toString().trim();
                password = etPassword.getText().toString().trim();

                if(email.trim().length() > 0 && password.trim().length() > 0) {


                //=========================================================  connection to the API

                progressDialog = new ProgressDialog(Login.this);
                progressDialog.setMax(100);
                progressDialog.setMessage("Its loading....");
                progressDialog.setTitle("Fetching Server Data");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();



                    //prepare call in retrofit 2.0
                    // get type retrofit object stored into service.
                    LoginApiInterface apiService = ApiClient.getClient().create(LoginApiInterface.class);


                    // Giving it the info from the edit text views.
                    Call<LoginModel> call = apiService.getLogged(email,password);

                    call.enqueue(new Callback<LoginModel>() {
                        @Override
                        public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                            progressDialog.dismiss();
                            Toast.makeText(Login.this, "code"+response.code(), Toast.LENGTH_SHORT).show();

                            if(response.code()==200) {


                                String emailResponse = response.body().getEmail();
                                Toast.makeText(Login.this, "Welcome" + emailResponse, Toast.LENGTH_SHORT).show();



                                        Intent in = new Intent(Login.this,MainActivity.class);
                                        startActivity(in);
                                        Login.this.finish();

                            }

                            else {

                                Toast.makeText(Login.this, " Some code ", Toast.LENGTH_SHORT).show();


                            }
                        }

                        @Override
                        public void onFailure(Call<LoginModel> call, Throwable t) {

                            progressDialog.dismiss();
                            Toast.makeText(Login.this, " Failed to establish connection to server ", Toast.LENGTH_SHORT).show();


                        }
                    });







                }else{
                    //Toast.makeText(Login.this,"Email/ Password fields cannot be empty",Toast.LENGTH_LONG).show();

                    //Setting error on view
                    if(email.trim().length() <= 0 && password.trim().length() <= 0) {
                        inputLayoutEmail.setError("Email field cannot be empty");
                        inputLayoutPassword.setError("Password Required");
                    }
                    else if(password.trim().length() <= 0) {
                        inputLayoutPassword.setError("Password Required");
                    }
                    else{
                        inputLayoutEmail.setError("Email field cannot be empty");

                    }
                }


            }
        });



  } // End onCreate()

    //======================Methods/ classes handling the floating Labels====================================================================

    //------------------------------------------- Validators ---------------------------------------------------------------------------------
    private boolean validateEmail() {
        String email = etEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email) ) {
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
        }
        else {

            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    } //Each needs its own validator

    //checks the pattern of the email.
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


//---------------------------  Wactches text activity and calls needed validator --------------------------------------------------------

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
            // Done for each editText view and each should have a validate(), some are similar create validateEdit()
            case R.id.etEmail:
                validateEmail();
                break;
            case R.id.etPassword:
                validatePassword();
                break;
        }
    }
}
} // End class
