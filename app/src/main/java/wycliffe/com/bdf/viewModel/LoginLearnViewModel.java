package wycliffe.com.bdf.viewModel;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import wycliffe.com.bdf.model.LoginUserLearn;

public class LoginLearnViewModel extends ViewModel {

    public MutableLiveData<String> EmailAddress = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();

    private MutableLiveData<LoginUserLearn> userLearnMutableLiveData;

    public MutableLiveData<LoginUserLearn> getUser(){

        if(userLearnMutableLiveData == null)
            userLearnMutableLiveData = new MutableLiveData<>();
        return userLearnMutableLiveData;
    }

    public void onClick( View view){
        // This shows how data should come from the repository
        LoginUserLearn loginUser = new LoginUserLearn(EmailAddress.getValue(), Password.getValue());
        userLearnMutableLiveData.setValue(loginUser);
    }
}
