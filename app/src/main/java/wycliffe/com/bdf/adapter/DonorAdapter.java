package wycliffe.com.bdf.adapter;




import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

import wycliffe.com.bdf.R;
import wycliffe.com.bdf.model.RecommendResponseModel;

/**
 * Created by Herick on 4/10/2017.
 */

public class DonorAdapter extends ArrayAdapter<RecommendResponseModel> {
    public DonorAdapter(Context context, ArrayList<RecommendResponseModel> donors) {
        super(context, 0, donors);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        RecommendResponseModel donor = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_donor_item, parent, false);
        }
        // Lookup view for data population
        TextView Name = (TextView) convertView.findViewById(R.id.donorName); // Name
        TextView BloodType = (TextView) convertView.findViewById(R.id.donorBloodGroup); // Bloodtype
        TextView Rhesus = (TextView) convertView.findViewById(R.id.donorRhesus); // Rhesus
        TextView Phone = (TextView) convertView.findViewById(R.id.donorPhone);
        TextView location = (TextView) convertView.findViewById(R.id.donorLoc);

        // Populate the data into the template view using the data object
        Name.setText(donor.getFullName());
        BloodType.setText(donor.getBloodType());
        Phone.setText(donor.getPhone());

        if(donor.getRhesusFactor().toString().equals("true")){
        Rhesus.setText("+");
        }
        else
        {
            Rhesus.setText("-");
        }

        switch (donor.getCurrentLocation()) {
            case 1 :

                location.setText("Central");

                break;
            case 2:

                location.setText("Coast");

                break;
            case 3:

                location.setText("Eastern");

                break;
            case 4:

                location.setText("Nairobi");

                break;
            case 5:

                location.setText("North Eastern");

                break;
            case 6:

                location.setText("Nyanza");

                break;
            case 7:

                Phone.setText("Rift Valley");

                break;
            default:

                location.setText("Western");

                break;
        }
        // Return the completed view to render on screen
        return convertView;
    }
}

















//import java.util.ArrayList;
//import java.util.HashMap;
//
//import android.app.Activity;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import wycliffe.com.bdf.R;
//
//public class DonorAdapter extends BaseAdapter {
//
//    private Activity activity;
//    private ArrayList <HashMap<String,String>> data;
//    private static LayoutInflater inflater=null;
//    //public ImageLoader imageLoader;
//
//    public DonorAdapter(Activity a, ArrayList d) {
//
//        activity = a;
//        data = d;
//        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//            }
//
//    public int getCount() {
//        return data.size();
//    }
//
//    public Object getItem(int position) {
//        return position;
//    }
//
//    public long getItemId(int position) {
//        return position;
//    }
//
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View vi=convertView;
//        if(convertView==null)
//            vi = inflater.inflate(R.layout.single_donor_item, null);
//
//        TextView Name = (TextView)vi.findViewById(R.id.donorName); // Name
//        TextView BloodType = (TextView)vi.findViewById(R.id.donorBloodGroup); // Bloodtype
//        TextView Rhesus = (TextView)vi.findViewById(R.id.donorRhesus); // Rhesus
//       // ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
//
//
//        HashMap<String,String> personDetail = new HashMap<String,String>();
//        personDetail = data.get(position);
//
//        // Setting all values in listview
//        Name.setText(personDetail.get(CustomizedListView.KEY_TITLE));
//        BloodType.setText(song.get(CustomizedListView.KEY_ARTIST));
//        Rhesus.setText(song.get(CustomizedListView.KEY_DURATION));
//        imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);
//        return vi;
//    }
//}