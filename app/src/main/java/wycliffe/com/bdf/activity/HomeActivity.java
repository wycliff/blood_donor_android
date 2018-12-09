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
    public SessionManager session;

    // vars
    String blood_type, gender, current_location, weight, age, rhesus;
    HashMap<String, String> details = new HashMap<>();
    Button btngetDonors;

    public DonorAdapter adapter;
    ArrayList<RecommendResponseModel> arrayDonors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Your Viable Donors");


        session = new SessionManager(getApplicationContext());

        if (session.isLoggedIn() == false) {
            HomeActivity.this.finish();
        }

        session.checkLogin();


        details = session.getUserDetails();


        blood_type = details.get("blood_type");
        rhesus = details.get("rhesus_factor");
        age = details.get("age");
        weight = details.get("weight");
        current_location = details.get("current_location");
        gender = details.get("gender");


        btngetDonors = (Button) findViewById(R.id.recommendButton);
        btngetDonors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                progressDialog = new ProgressDialog(HomeActivity.this);
                progressDialog.setMax(100);
                progressDialog.setMessage("Its loading....");
                progressDialog.setTitle("Fetching Server Data");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();


                RecommendApiInterface apiService = ApiClient.getClient().create(RecommendApiInterface.class);

//        Map<String, String> postParams  = new HashMap<>();
//        postParams.put("email",email);

                arrayDonors = new ArrayList<>();

                Call<ArrayList<RecommendResponseModel>> call = apiService.getRecommendations(blood_type, rhesus, age, current_location, gender, weight);

                call.enqueue(new Callback<ArrayList<RecommendResponseModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<RecommendResponseModel>> call, Response<ArrayList<RecommendResponseModel>> response) {
                        progressDialog.dismiss();
                        if (response.code() >= 400 && response.code() < 599) {

                            Toast.makeText(HomeActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                        } else {

                            for (int i = 1; i < 9; i++) {
                                arrayDonors.add(response.body().get(i));
                            }

                            adapter = new DonorAdapter(HomeActivity.this, arrayDonors);
                            ListView listView = findViewById(R.id.donorListView);
                            listView.setAdapter(adapter);

                            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                @Override
                                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    return false;
                                }
                            });

                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<RecommendResponseModel>> call, Throwable t) {

                        progressDialog.dismiss();
                        Toast.makeText(HomeActivity.this, "Failed to establish connection to server ", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void logout() {
        Toast.makeText(getApplicationContext(), "goodbye!", Toast.LENGTH_LONG).show();
        session.logoutUser();
        HomeActivity.this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
