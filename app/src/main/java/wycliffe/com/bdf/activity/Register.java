package wycliffe.com.bdf.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
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

import wycliffe.com.bdf.R;


public class Register extends AppCompatActivity {


    EditText etEmail,etName,  etPassword, etConfirmPassword, etPhone, etWeight, etAge;
    Button btnRegister;
    CheckBox cbShowPassword;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword, inputLayoutConfirmPassword,
            inputLayoutPhone, inputLayoutWeight, inputLayoutAge, inputLayoutName;

    RadioGroup radioGenderGroup, radioFtdGroup, radioRhesusGroup;
    RadioButton radioGenderButton, radioFtdButton, radioRhesusButton ,radioMale,radioFemale,radioPositive, radioNegative, radioYes, radioNo;

    //For the Spinner
    private Spinner myLocationSpinner, myBloodSpinner;

    //Items in the spinner
    ArrayList<String> location_options=new ArrayList<String>();
    ArrayList<String> blood_type_options=new ArrayList<String>();


    String email, password, phone, gender, blood_type, name, confirmPassword, place;
    public boolean ftd, rhesus;
    int age, weight,location ;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


//=====================================back button  =====================================================================================
        //sugested : Using toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        if (toolbar != null) {
//            setSupportActionBar(toolbar);
//        }

        //adding toggle switch to Action bar (default)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        //setting title
        getSupportActionBar().setTitle("Register here");
//----------------------------------------------------------------------------------------------------------------------------------------

        //Getting textfields from the user
        etEmail = (EditText) findViewById(R.id.EmailReg);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        etName = (EditText) findViewById(R.id.etNameReg);
        etAge = (EditText) findViewById(R.id.etAge);
        etWeight = (EditText) findViewById(R.id.etWeight);
        etPhone = (EditText) findViewById(R.id.etPhoneReg);

        //Getting the login Button
        btnRegister = (Button) findViewById(R.id.registerButton);



        //Saving values in string



//-------------------------------------------The check box---------------------------------------------------------------------------
        //show password check box
        cbShowPassword = (CheckBox) findViewById(R.id.showPasswordreg);

        cbShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

//==========================================Handling foalting Labels=============================================================================

        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        inputLayoutConfirmPassword =  (TextInputLayout) findViewById(R.id.input_layout_passwordConfirm);
        inputLayoutAge =  (TextInputLayout) findViewById(R.id.input_layout_age);
        inputLayoutPhone =  (TextInputLayout) findViewById(R.id.input_layout_phone);
        inputLayoutWeight =  (TextInputLayout) findViewById(R.id.input_layout_weight);


//=================== Setting the Current location and blood type in the spinner =================================

        location_options.add("Central");
        location_options.add("Coast");
        location_options.add("Eastern");
        location_options.add("Nairobi");
        location_options.add("North Eastern");
        location_options.add("Nyanza");
        location_options.add("Rift Valley");
        location_options.add("Western");


        myLocationSpinner = (Spinner) findViewById(R.id.myLocationSpinner);
        ArrayAdapter<String> setter_adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.single_location, R.id.single_location,location_options);
        myLocationSpinner.setAdapter(setter_adapter); // this will set list of values to spinner


        // mySpinner.setSelection(((ArrayAdapter<String>)mySpinner.getAdapter()).getPosition(myString));
        //===========  For resetting the residence ===========================================================================
        //addItemsOnSpinner();
//        addListenerOnSpinnerItemSelection();
        //addItemsOnSpinner();
        place = myLocationSpinner.getSelectedItem().toString();

        switch (place)
        {
            case "Central":
                location = 1;

                break;
            case "Coast":
                location = 2;

                break;
            case "Eastern":
                location = 3;

                break;
            case "Nairobi":
                location = 4;

                break;
            case "North Eastern":
                location = 5;

                break;
            case "Nyanza":
                location = 6;

                break;
            case "Rift Valley":
                location = 7;
                       break;
            default:
                location = 8;
                break;

        }

        blood_type_options.add("A");
        blood_type_options.add("B");
        blood_type_options.add("AB");
        blood_type_options.add("O");

        myBloodSpinner = (Spinner) findViewById(R.id.myBloodGroupSpinner);
        ArrayAdapter<String> blood_type_adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.single_blood_type, R.id.single_blood_type,blood_type_options);
        myBloodSpinner.setAdapter(blood_type_adapter); // set list of values to spinner

        blood_type = myBloodSpinner.getSelectedItem().toString();




        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = etName.getText().toString().trim();
                email = etEmail.getText().toString().trim();
                phone=  etPhone.getText().toString().trim();
                password= etPassword.getText().toString().trim();
                confirmPassword= etConfirmPassword.getText().toString().trim();

                age = Integer.parseInt(etAge.getText().toString().trim());
                weight = Integer.parseInt(etWeight.getText().toString().trim());

                //do inside btn.onclick
                if (password.equals(confirmPassword)){
                    password = confirmPassword;
                }



                //--------------------------------------------Handling Radio Buttons for setting the type and gender (not used here)--------------------------------

                radioMale=(RadioButton)findViewById(R.id.Male);
                radioFemale=(RadioButton)findViewById(R.id.Female);

                radioYes=(RadioButton)findViewById(R.id.yes);
                radioNo=(RadioButton)findViewById(R.id.no);

                radioPositive=(RadioButton)findViewById(R.id.positive);
                radioNegative=(RadioButton)findViewById(R.id.negative);

                // getting (what i am using)
                radioRhesusGroup = (RadioGroup)findViewById(R.id.RadioRhesus);
                radioGenderGroup =  (RadioGroup)findViewById(R.id.RadioGender);
                radioFtdGroup = (RadioGroup)findViewById(R.id.RadioFtd);


//-------------------------------------------Handling the Radio Buttons -----------------------------------------------------------------
                // get selected radio button from radioGroup
                int selectedSexId = radioGenderGroup.getCheckedRadioButtonId();
                int selectedFtdId = radioFtdGroup.getCheckedRadioButtonId();
                int selectedRhesusId = radioRhesusGroup.getCheckedRadioButtonId();

                // find the radio buttons (BY RETURNED ID)
                radioGenderButton = (RadioButton) findViewById(selectedSexId);
                radioFtdButton= (RadioButton)findViewById(selectedFtdId);
                radioRhesusButton = (RadioButton)findViewById(selectedRhesusId);

                //Do what you want with this radio button eg getText() etc,

                //Assigning the selected genders to either 'M' or 'F'
                if ((radioGenderButton.getText()).equals("Male") ){
                    gender= "MALE";
                }
                else {
                    gender = "FEMALE";
                }

                //Assigning the selected student type the either '1' or '2'
                if ((radioFtdButton.getText()).equals("YES")){
                    ftd = true;
                }
                else {
                    ftd = false;
                }


                if ((radioRhesusButton.getText()).equals("positive")){
                    rhesus = true;
                }
                else {
                    rhesus = false;
                }


                Toast.makeText(Register.this, email + password + phone + gender + blood_type +  name + confirmPassword + place, Toast.LENGTH_LONG).show();
                Toast.makeText(Register.this,radioGenderButton.getText().toString() + radioFtdButton.getText().toString() + radioRhesusButton.getText().toString()+ Integer.toString(age) + Integer.toString(weight) + Integer.toString(location), Toast.LENGTH_LONG).show();

            }
        });


    }// end onCreate()


//----------------------------------- Back Arrow -----------
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


//======================Methods/ classes handling the floating Labels====================================================================

    //------------------------------------------- Validators ---------------------------------------------------------------------------------
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

    //checks the pattern of the email.
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
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
    } //Each needs its own validator

    private boolean validateName() {
        if (etName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError("Enter the Name");
            //requestFocus(etPassword);
            return false;
        } else {

            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    } //Each needs its own validator

    private boolean validateAge() {
        if (etAge.getText().toString().trim().isEmpty()) {
            inputLayoutAge.setError("Enter the Age");
            //requestFocus(etPassword);
            return false;
        } else {

            inputLayoutAge.setErrorEnabled(false);
        }

        return true;
    } //Each needs its own validator

    private boolean validatePhone() {
        if (etPhone.getText().toString().trim().isEmpty()) {
            inputLayoutPhone.setError("Enter the Age");
            //requestFocus(etPassword);
            return false;
        } else {

            inputLayoutPhone.setErrorEnabled(false);
        }

        return true;
    } //Each needs its own validator

    private boolean validateWeight() {
        if (etPhone.getText().toString().trim().isEmpty()) {
            inputLayoutPhone.setError("Enter the Age");
            //requestFocus(etPassword);
            return false;
        } else {

            inputLayoutPhone.setErrorEnabled(false);
        }

        return true;
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
