package cauotech.ng.androidlistview;

/**
 * Created by UWA on 19 Dec 2016.
 */

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    //static variables used throughout the application
    static String NAME = "name";
    static String VERSION = "version";
    static String RELEASED = "released";
    static String API = "api";
    static String IMAGE = "image";

    //variable string for the main class
    private String TAG = MainActivity.class.getSimpleName();

    //variables
    private ProgressDialog pDialog;
    ListView lv;

    // URL to get versions JSON
    private static String url = "http://codetest.cobi.co.za/androids.json";

    //store arrayList with a variable
    ArrayList<HashMap<String, String>> versionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assigning the variable for arrayList
        versionList = new ArrayList<>();
        //assigning the listview to the xml id
        lv = (ListView) findViewById(R.id.list);

        //create a new class to executes json loading
        new GetVersions().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetVersions extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray versions = jsonObj.getJSONArray("versions");

                    // looping through All Versions
                    for (int i = 0; i < versions.length(); i++) {
                        JSONObject c = versions.getJSONObject(i);

                        String name = c.getString("name");
                        String version = c.getString("version");
                        /*String released = c.getString("released");
                        String api = c.getString("api");
                        String imgPic = c.getString("image");*/

                        // tmp hash map for single version
                        HashMap<String, String> vrs = new HashMap<>();

                        // adding each child node to HashMap key => value
                        vrs.put("name", name);
                        vrs.put("version", version);
                        /*vrs.put("released", released);
                        vrs.put("api", api);
                        vrs.put("image", imgPic);*/

                        // adding version to version list
                        versionList.add(vrs);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, versionList,
                    R.layout.list_item, new String[]{"name", "version"},
                    new int[]{R.id.name, R.id.versn});

            lv.setAdapter(adapter);


            // React to user clicks on item

           /* lv.setOnItemClickListener(new SimpleAdapter() {
                public void onItemClick(SimpleAdapter parent, View view,
                                        int position, long id) {

                    // When clicked, show a toast with the TextView text
                    Intent xn = new Intent(MainActivity.this, SingleItemView.class);
                    startActivity(xn);

                }
            });*/

        }

    }

 }

