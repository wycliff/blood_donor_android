package wycliffe.com.bdf.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wycliffe.com.bdf.R;
import wycliffe.com.bdf.adapter.DonorAdapter;
import wycliffe.com.bdf.model.RecommendResponseModel;
import wycliffe.com.bdf.rest.ApiClient;
import wycliffe.com.bdf.rest.RecommendApiInterface;

public class HomeActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    /*===========================Session and drawer =================================================*/
    // Session Manager Class
    public SessionManager session;

    HashMap<String, String> details = new HashMap<>();

    String blood_type, gender,current_location, weight, age, rhesus;
    //Boolean rhesus;
    Button btngetDonors;

    public DonorAdapter adapter;
    ArrayList<RecommendResponseModel> arrayDonors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//============================  setting title

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Your Viable Donors");


        session = new SessionManager(getApplicationContext());

        //For testing purposes.
        //Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

        //bug fix
        if(session.isLoggedIn()==false){
            //Killer
            HomeActivity.this.finish();
        }

        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity if he/she is not
         * logged in. Very  Important
         * */
        session.checkLogin();


        //get details from shared pref
        details=session.getUserDetails();

        //To send
        blood_type= details.get("blood_type");
        rhesus = details.get("rhesus_factor");
        age = details.get("age");
        weight = details.get("weight");
        current_location = details.get("current_location");
        gender = details.get("gender");
//--------------------------------------------- Communicate with server


        btngetDonors = (Button) findViewById(R.id.recommendButton);
//
        btngetDonors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                progressDialog = new ProgressDialog(HomeActivity.this);
                progressDialog.setMax(100);
                progressDialog.setMessage("Its loading....");
                progressDialog.setTitle("Fetching Server Data");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();


                //prepare call in retrofit 2.0
                // get type retrofit object stored into service.
                RecommendApiInterface apiService = ApiClient.getClient().create(RecommendApiInterface.class);

//        Map<String, String> postParams  = new HashMap<>();
//        postParams.put("email",email);

                arrayDonors = new ArrayList<RecommendResponseModel>();

                Call<ArrayList<RecommendResponseModel>> call = apiService.getRecommendations(blood_type, rhesus, age,current_location, gender, weight);

                call.enqueue(new Callback<ArrayList<RecommendResponseModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<RecommendResponseModel>> call, Response<ArrayList<RecommendResponseModel>> response) {

                        progressDialog.dismiss();
                        //Toast.makeText(HomeActivity.this, "code" +response.code(), Toast.LENGTH_SHORT).show();

                        if(response.code()>= 400 && response.code() < 599){

                            Toast.makeText(HomeActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            //===== Displaying List View
                            // adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list of);


                            for(int i = 1; i< 9; i++ ) {
                                arrayDonors.add(response.body().get(i));
                            }

                           // Toast.makeText(HomeActivity.this, arrayDonors.toString() , Toast.LENGTH_SHORT).show();

                            adapter = new DonorAdapter(HomeActivity.this, arrayDonors);
                            ListView listView = (ListView) findViewById(R.id.donorListView);
                            listView.setAdapter(adapter);

                            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                @Override
                                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                                  // Call

                                    return false;
                                }
                            });

                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<RecommendResponseModel>> call, Throwable t) {

                        progressDialog.dismiss();
                        Toast.makeText(HomeActivity.this,"Failed to establish connection to server ", Toast.LENGTH_SHORT).show();

                    }
                });

                /*=====================================================================================================================================*/



            }
        });



    }//end onCreate()


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }


    //Method that logs you out
    public void logout(){

        Toast.makeText(getApplicationContext(),"goodbye!",Toast.LENGTH_LONG).show();

        // Clear the session data
        // This will clear all session data and
        // redirect user to LoginActivity
        session.logoutUser();


        //Killer
        HomeActivity.this.finish();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}//end Class
