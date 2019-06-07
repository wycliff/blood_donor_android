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

public class DonorAdapter extends ArrayAdapter<RecommendResponseModel> {
    public DonorAdapter(Context context, ArrayList<RecommendResponseModel> donors) {
        super(context, 0, donors);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RecommendResponseModel donor = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_donor_item, parent, false);
        }
        TextView Name = convertView.findViewById(R.id.donorName);
        TextView BloodType = convertView.findViewById(R.id.donorBloodGroup);
        TextView Rhesus = convertView.findViewById(R.id.donorRhesus);
        TextView Phone = convertView.findViewById(R.id.donorPhone);
        TextView location = convertView.findViewById(R.id.donorLoc);

        Name.setText(donor.getFullName());
        BloodType.setText(donor.getBloodType());
        Phone.setText(donor.getPhone());

        if (donor.getRhesusFactor().toString().equals("true")) {
            Rhesus.setText("+");
        } else {
            Rhesus.setText("-");
        }

        switch (donor.getCurrentLocation()) {
            case 1:

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
        return convertView;
    }
}





