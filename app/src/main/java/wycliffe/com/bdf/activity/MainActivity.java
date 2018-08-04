package wycliffe.com.bdf.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wycliffe.com.bdf.R;
import wycliffe.com.bdf.model.RecommendResponseModel;
import wycliffe.com.bdf.model.RegisterResponseModel;
import wycliffe.com.bdf.rest.ApiClient;
import wycliffe.com.bdf.rest.RecommendApiInterface;
import wycliffe.com.bdf.rest.RegisterApiInterface;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    /*===========================Session and drawer =================================================*/
    // Session Manager Class
    public SessionManager session;

    HashMap<String, String> details = new HashMap<>();

    String blood_type, gender,current_location, weight, age, rhesus;
    //Boolean rhesus;


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*============================================== Shared preference applied here====================================================*/
        // Session class instance . very important.
        session = new SessionManager(getApplicationContext());

        //For testing purposes.
        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();



        //bug fix
        if(session.isLoggedIn()==false){

            //Killer
            MainActivity.this.finish();

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
//====================================================================== SERVER SERVER SERVER =============================================================================

        //=========================================================  connection to the API

        progressDialog = new ProgressDialog(MainActivity.this);
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


//        Toast.makeText(MainActivity.this, blood_type + " " +  rhesus + " " + age + " " + current_location + " " + gender + " " + weight , Toast.LENGTH_SHORT).show();
        // Giving it the info from the edit text views.
        Call<RecommendResponseModel> call = apiService.getRecommendations (blood_type, rhesus, age,current_location, gender, weight);

        call.enqueue(new Callback<RecommendResponseModel>() {
            @Override
            public void onResponse(Call<RecommendResponseModel> call, Response<RecommendResponseModel> response) {

                //progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "code" +response.code(), Toast.LENGTH_SHORT).show();

                if(response.code()==200) {

                    //To be displayed in Clickable LView (Needs and adapter)
                    Toast.makeText(MainActivity.this, response.body().toString() , Toast.LENGTH_SHORT).show();

                }

                else {

                    Toast.makeText(MainActivity.this,response.message(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<RecommendResponseModel> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(MainActivity.this," Failed to establish connection to server ", Toast.LENGTH_SHORT).show();

            }
        });



        /*=====================================================================================================================================*/



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        MainActivity.this.finish();

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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
