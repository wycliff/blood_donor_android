package wycliffe.com.bdf.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wycliffe.com.bdf.R;
import wycliffe.com.bdf.model.RegisterResponseModel;
import wycliffe.com.bdf.rest.ApiClient;
import wycliffe.com.bdf.rest.RegisterApiInterface;


public class Register extends AppCompatActivity {

    // Views
    EditText etEmail, etName, etPassword, etConfirmPassword, etPhone, etWeight, etAge;
    Button btnRegister;
    CheckBox cbShowPassword;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword, inputLayoutConfirmPassword,
            inputLayoutPhone, inputLayoutWeight, inputLayoutAge, inputLayoutName;

    RadioGroup radioGenderGroup, radioFtdGroup, radioRhesusGroup;
    RadioButton radioGenderButton, radioFtdButton, radioRhesusButton, radioMale, radioFemale, radioPositive, radioNegative, radioYes, radioNo;

    // Spinner
    private Spinner myLocationSpinner, myBloodSpinner;
    ArrayList<String> location_options = new ArrayList<String>();
    ArrayList<String> blood_type_options = new ArrayList<String>();

    // vars
    String email, password, phone, gender, blood_type, name, confirmPassword, place, ftd, rhesus, age, weight, location;


    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Register here");

        etEmail = findViewById(R.id.EmailReg);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etName = findViewById(R.id.etNameReg);
        etAge = findViewById(R.id.etAge);
        etWeight = findViewById(R.id.etWeight);
        etPhone = findViewById(R.id.etPhoneReg);


        btnRegister = findViewById(R.id.registerButton);

        cbShowPassword = findViewById(R.id.showPasswordreg);

        cbShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (!isChecked) {
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        inputLayoutEmail = findViewById(R.id.input_layout_email);
        inputLayoutName = findViewById(R.id.input_layout_name);
        inputLayoutPassword = findViewById(R.id.input_layout_password);
        inputLayoutConfirmPassword = findViewById(R.id.input_layout_passwordConfirm);
        inputLayoutAge = findViewById(R.id.input_layout_age);
        inputLayoutPhone = findViewById(R.id.input_layout_phone);
        inputLayoutWeight = findViewById(R.id.input_layout_weight);


        location_options.add("Central");
        location_options.add("Coast");
        location_options.add("Eastern");
        location_options.add("Nairobi");
        location_options.add("North Eastern");
        location_options.add("Nyanza");
        location_options.add("Rift Valley");
        location_options.add("Western");


        myLocationSpinner = (Spinner) findViewById(R.id.myLocationSpinner);
        ArrayAdapter<String> setter_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.single_location, R.id.single_location, location_options);
        myLocationSpinner.setAdapter(setter_adapter);

        blood_type_options.add("A");
        blood_type_options.add("B");
        blood_type_options.add("AB");
        blood_type_options.add("O");

        myBloodSpinner = findViewById(R.id.myBloodGroupSpinner);
        ArrayAdapter<String> blood_type_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.single_blood_type, R.id.single_blood_type, blood_type_options);
        myBloodSpinner.setAdapter(blood_type_adapter);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                blood_type = myBloodSpinner.getSelectedItem().toString();
                place = myLocationSpinner.getSelectedItem().toString();

                switch (place) {
                    case "Central":
                        location = "1";

                        break;
                    case "Coast":
                        location = "2";

                        break;
                    case "Eastern":
                        location = "3";

                        break;
                    case "Nairobi":
                        location = "4";

                        break;
                    case "North Eastern":
                        location = "5";

                        break;
                    case "Nyanza":
                        location = "6";

                        break;
                    case "Rift Valley":
                        location = "7";
                        break;
                    default:
                        location = "8";
                        break;

                }

                name = etName.getText().toString().trim();
                email = etEmail.getText().toString().trim();
                phone = etPhone.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                confirmPassword = etConfirmPassword.getText().toString().trim();

                try {
                    age = etAge.getText().toString().trim();
                    weight = etWeight.getText().toString().trim();

                } catch (NumberFormatException e) {

                }

                if (password.equals(confirmPassword)) {
                    password = confirmPassword;
                }

                radioMale = findViewById(R.id.Male);
                radioFemale = findViewById(R.id.Female);

                radioYes = findViewById(R.id.yes);
                radioNo = findViewById(R.id.no);

                radioPositive = findViewById(R.id.positive);
                radioNegative = findViewById(R.id.negative);


                radioRhesusGroup = findViewById(R.id.RadioRhesus);
                radioGenderGroup = findViewById(R.id.RadioGender);
                radioFtdGroup = findViewById(R.id.RadioFtd);


                int selectedSexId = radioGenderGroup.getCheckedRadioButtonId();
                int selectedFtdId = radioFtdGroup.getCheckedRadioButtonId();
                int selectedRhesusId = radioRhesusGroup.getCheckedRadioButtonId();


                radioGenderButton = findViewById(selectedSexId);
                radioFtdButton = findViewById(selectedFtdId);
                radioRhesusButton = findViewById(selectedRhesusId);


                if ((radioGenderButton.getText()).equals("Male")) {
                    gender = "MALE";
                } else {
                    gender = "FEMALE";
                }

                if ((radioFtdButton.getText()).equals("YES")) {
                    ftd = "true";
                } else {
                    ftd = "false";
                }


                if ((radioRhesusButton.getText()).equals("positive")) {
                    rhesus = "true";
                } else {
                    rhesus = "false";
                }

                if (email.trim().length() > 0 && password.trim().length() > 0 && confirmPassword.trim().length() > 0 &&
                        name.trim().length() > 0 && phone.trim().length() > 0 && age.trim().length() > 0 &&
                        weight.trim().length() > 0) {

                    progressDialog = new ProgressDialog(Register.this);
                    progressDialog.setMax(100);
                    progressDialog.setMessage("Its loading....");
                    progressDialog.setTitle("Fetching Server Data");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.show();

                    RegisterApiInterface apiService = ApiClient.getClient().create(RegisterApiInterface.class);
                    Map<String, String> postParams = new HashMap<>();

                    Call<RegisterResponseModel> call = apiService.getRegistered(email
                            , password, name, phone, ftd, blood_type, rhesus, age, location, gender, weight);

                    call.enqueue(new Callback<RegisterResponseModel>() {
                        @Override
                        public void onResponse(Call<RegisterResponseModel> call, Response<RegisterResponseModel> response) {

                            progressDialog.dismiss();
                            Toast.makeText(Register.this, "code" + response.code(), Toast.LENGTH_SHORT).show();


                            if (response.code() >= 400 && response.code() < 599) {

                                Toast.makeText(Register.this, "Server Error", Toast.LENGTH_SHORT).show();
                            } else {

                                Toast.makeText(Register.this, "Welcome", Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(Register.this, MainActivity.class);
                                startActivity(in);
                                Register.this.finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<RegisterResponseModel> call, Throwable t) {

                            progressDialog.dismiss();
                            Toast.makeText(Register.this, " Failed to establish connection to server ", Toast.LENGTH_SHORT).show();

                        }
                    });
                } else {

                    //Setting error on view
                    if (email.trim().length() <= 0 && password.trim().length() <= 0 && name.trim().length() <= 0 &&
                            confirmPassword.trim().length() <= 0 && phone.trim().length() <= 0) {
                        inputLayoutEmail.setError("Field cannot be empty");
                        inputLayoutConfirmPassword.setError("Field cannot be empty");
                        inputLayoutName.setError("Field cannot be empty");
                        inputLayoutPhone.setError("Field cannot be empty");
                        inputLayoutPassword.setError("Field cannot be empty");
                        inputLayoutAge.setError("Field cannot be empty");
                        inputLayoutWeight.setError("Field cannot be empty");

                    } else if (password.trim().length() <= 0) {
                        inputLayoutPassword.setError("Field cannot be empty");
                    } else if (name.trim().length() <= 0) {
                        inputLayoutName.setError("Field cannot be empty");
                    } else if (phone.trim().length() <= 0) {
                        inputLayoutPhone.setError("Field cannot be empty");
                    } else if (email.trim().length() <= 0) {
                        inputLayoutEmail.setError("Email field cannot be empty");
                    } else if (age.trim().length() <= 0) {
                        inputLayoutEmail.setError("Age field cannot be empty");
                    } else if (weight.trim().length() <= 0) {
                        inputLayoutEmail.setError("Weight field cannot be empty");
                    } else {
                        Toast.makeText(Register.this, "ALL FIELDS REQUIRED", Toast.LENGTH_LONG).show();
                    }
                }
            }

        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private boolean validateEmail() {
        String email = etEmail.getText().toString().trim();
        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError("Enter the email");
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }
        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validatePassword() {
        if (etPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError("Enter the password");
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateName() {
        if (etName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError("Enter the Name");
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateAge() {
        if (etAge.getText().toString().trim().isEmpty()) {
            inputLayoutAge.setError("Enter the Age");
            return false;
        } else {
            inputLayoutAge.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePhone() {
        if (etPhone.getText().toString().trim().isEmpty()) {
            inputLayoutPhone.setError("Enter the Age");
            return false;
        } else {
            inputLayoutPhone.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateWeight() {
        if (etPhone.getText().toString().trim().isEmpty()) {
            inputLayoutPhone.setError("Enter the Age");
            return false;
        } else {
            inputLayoutPhone.setErrorEnabled(false);
        }
        return true;
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
                case R.id.etNameReg:
                    validateName();
                    break;
                case R.id.etAge:
                    validateAge();
                    break;
                case R.id.etWeight:
                    validatePassword();
                    break;
                case R.id.etPhoneReg:
                    validatePhone();
                    break;
            }
        }
    }
}
