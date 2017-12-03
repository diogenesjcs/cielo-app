package br.com.cielo.app.ui.main;

/**
 * Created by dioge on 03-Dec-17.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import br.com.cielo.app.R;

public class SuggestionAdapter extends BaseAdapter implements Filterable {

    protected static final String TAG = "SuggestionAdapter";
    private List<ProfileAuto> suggestions;
    private Context mContext;
    public SuggestionAdapter(Activity context, String nameFilter) {
        suggestions = new ArrayList<ProfileAuto>();
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.simple_dropdown_item_2line, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.text2)).setText(getItem(position).name);
        ProfilePictureView profilePictureView;
        profilePictureView = (ProfilePictureView) convertView.findViewById(R.id.inputPic2);
        profilePictureView.setProfileId(getItem(position).id);
        profilePictureView.setVisibility(View.VISIBLE);
        return convertView;
    }

    @Override
    public int getCount() {
        return suggestions.size();
    }

    @Override
    public ProfileAuto getItem(int index) {
        return suggestions.get(index);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Filter getFilter() {
        Filter myFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    // A class that queries a web API, parses the data and
                    // returns an ArrayList<GoEuroGetSet>

                    GraphRequest request = GraphRequest.newGraphPathRequest(
                            AccessToken.getCurrentAccessToken(),
                            "/search",
                            new GraphRequest.Callback() {
                                public void onCompleted(GraphResponse response) {
                                    suggestions.clear();
                                    try {
                                        if(response.getJSONObject().getJSONArray("data")!=null){
                                            try {
                                                for(int i=0;i<response.getJSONObject().getJSONArray("data").length();i++){
                                                    suggestions.add(new ProfileAuto(response.getJSONObject().getJSONArray("data").getJSONObject(i).getString("name"),
                                                            response.getJSONObject().getJSONArray("data").getJSONObject(i).getString("id")));
                                                }

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            filterResults.values = suggestions;
                                            filterResults.count = suggestions.size();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id,name");
                    parameters.putLong("limit",10);
                    parameters.putString("type","user");
                    parameters.putString("q",constraint.toString());
                    request.setParameters(parameters);
                    request.executeAndWait();
                    // Now assign the values and count to the FilterResults
                    // object

                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence contraint,
                                          FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return myFilter;
    }

    public class ProfileAuto{
        String name;
        String id;

        ProfileAuto(String name, String id){
            this.name = name;
            this.id = id;
        }
    }

}