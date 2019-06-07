package wycliffe.com.bdf.view.activity;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import wycliffe.com.bdf.R;
import wycliffe.com.bdf.databinding.ActivityLoginLearnBinding;
import wycliffe.com.bdf.model.LoginUserLearn;
import wycliffe.com.bdf.viewModel.LoginLearnViewModel;

public class LoginLearn extends AppCompatActivity {

    private LoginLearnViewModel loginLearnViewModel;
    private ActivityLoginLearnBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginLearnViewModel = ViewModelProviders.of(this).get(LoginLearnViewModel.class);
        initBinding();
        loginLearnViewModel.getUser().observe(this, new Observer<LoginUserLearn>() {
            @Override
            public void onChanged(LoginUserLearn loginUserLearn) {

                if (TextUtils.isEmpty(Objects.requireNonNull(loginUserLearn).getStrEmailAddress())) {
                    binding.txtEmailAddress.setError("Enter an E-Mail Address");
                    binding.txtEmailAddress.requestFocus();
                }
                else if (!loginUserLearn.isEmailValid()) {
                    binding.txtEmailAddress.setError("Enter a Valid E-mail Address");
                    binding.txtEmailAddress.requestFocus();
                }
                else if (TextUtils.isEmpty(Objects.requireNonNull(loginUserLearn).getStrPassword())) {
                    binding.txtPassword.setError("Enter a Password");
                    binding.txtPassword.requestFocus();
                }
                else if (!loginUserLearn.isPasswordLengthGreaterThan5()) {
                    binding.txtPassword.setError("Enter at least 6 Digit password");
                    binding.txtPassword.requestFocus();
                }
                else {
                    binding.lblEmailAnswer.setText(loginUserLearn.getStrEmailAddress());
                    binding.lblPasswordAnswer.setText(loginUserLearn.getStrPassword());
                }

            }
        });

    }

    private void initBinding (){
        binding = DataBindingUtil.setContentView(LoginLearn.this, R.layout.activity_login_learn);
        binding.setLifecycleOwner(this);
        binding.setLoginLearnViewModel(loginLearnViewModel);
    }
}
