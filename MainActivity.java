package basicandroid.com.swiperefreshlayoutgetjson;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    TextView name,email;
    LinearLayout linearview;
    List list;
    int items_count = 0;

    public static final String url = "https://api.myjson.com/bins/wpvhy";

    public static final String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private List<Place> placeList = new ArrayList<>();
    CustomListAdaptor adaptor;
    ListView itemlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
        name = (TextView)findViewById(R.id.name);
        email = (TextView)findViewById(R.id.email);
        itemlist = (ListView) findViewById(R.id.items_listview);
//        listView = (ListView)findViewById(R.id.items_listview);
//        textView = (TextView)findViewById(R.id.items_title);
//        textView.setText("Swipe to Refresh...");


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getData();

                adaptor = new CustomListAdaptor(MainActivity.this,placeList);
                itemlist.setAdapter(adaptor);

                swipeRefreshLayout.setRefreshing(false);

//                refreshLayout();

            }
        });


    }

    private void getData() {


        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG,response.toString());
                hidePDialog();

                try {
                    JSONArray keralaPlaceArray = response.getJSONArray("candidate");

                    for(int i=0;i<keralaPlaceArray.length();i++){

                        JSONObject object = keralaPlaceArray.getJSONObject(i);

                        Place place = new Place();

                        place.setName(object.getString("name"));
                        place.setEmail(object.getString("email"));

//                        place.setThumbnailUrl(object.getString("image"));
//                        place.setPlace(object.getString("place"));
//                        place.setDescription(object.getString("description"));
//                        place.setBesttime(object.getString("besttime"));
//                        place.setAirport(object.getString("airport"));
//                        place.setRailwaystation(object.getString("railwaystation"));

                        placeList.add(place);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adaptor.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error:"+error.getMessage());
                hidePDialog();
            }
        });

        AppController.getInstance().addToRequestQueue(request);




    }

    private void refreshLayout(){

//        items_count++;
//        adapter = new CustomListAdaptor(MainActivity.this,)
//        listView.setAdapter(adapter);
//
//        if(items_count>3){
//            items_count = 0;
//        }

        swipeRefreshLayout.setRefreshing(false);



    }



    class CustomListAdaptor extends BaseAdapter {

        private Activity activity;
        private LayoutInflater inflater;
        private List<Place> placeList;

//        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        public CustomListAdaptor(Activity activity, List<Place> placeList) {
            this.activity = activity;
            this.placeList = placeList;
        }

        @Override
        public int getCount() {
            return placeList.size();
        }

        @Override
        public Object getItem(int location) {
            return placeList.get(location);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {


            if(inflater == null){
                inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }

            if(convertView == null){
                convertView = inflater.inflate(R.layout.items_layout,null);
            }
//            if(imageLoader == null)
//                imageLoader = AppController.getInstance().getImageLoader();
//            NetworkImageView thumbnail = (NetworkImageView)convertView.findViewById(R.id.thumbnail);
//            TextView placenew = (TextView)convertView.findViewById(R.id.place_id);
//            TextView description = (TextView)convertView.findViewById(R.id.description_id);
//            TextView besttime = (TextView)convertView.findViewById(R.id.besttime_id);
//            TextView airport = (TextView)convertView.findViewById(R.id.airport_id);
//            TextView railwaystation = (TextView)convertView.findViewById(R.id.railwaystation_id);

            TextView name = (TextView)convertView.findViewById(R.id.name);
            TextView email = (TextView)convertView.findViewById(R.id.email);

            Place place = placeList.get(position);

//            thumbnail.setImageUrl(place.getThumbnailUrl(),imageLoader);
//            placenew.setText("Place : " +place.getPlace());
//            description.setText("Description : " +place.getDescription());
//            besttime.setText("Best time to Visit : " +place.getBesttime());
//            airport.setText("Nearest Airport : " +place.getAirport());
//            railwaystation.setText("Railway Station : " +place.getRailwaystation());

            name.setText("Name : " +place.getName());
            email.setText("Email : " +place.getEmail());


            return convertView;
        }
    }

    private void hidePDialog() {

        if(pDialog != null){
            pDialog.dismiss();
            pDialog = null;
        }

    }


}



