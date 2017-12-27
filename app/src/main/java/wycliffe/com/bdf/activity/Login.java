package wycliffe.com.bdf.activity;

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

import wycliffe.com.bdf.R;

public class Login extends AppCompatActivity {


    EditText etEmail,etPassword;
    Button btnLogin;
    CheckBox cbShowPassword;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.buttonLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //intent which to next
                Intent in = new Intent(Login.this,MainActivity.class);
                startActivity(in);
                Login.this.finish();
            }
        });




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
}
