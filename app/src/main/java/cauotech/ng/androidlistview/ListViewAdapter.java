package cauotech.ng.androidlistview;

/**
 * Created by UWA on 19 Dec 2016.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public ListViewAdapter(Context context,
                           ArrayList<HashMap<String, String>> versionList) {
        this.context = context;
        data = versionList;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Declare Variables
        TextView name;
        ImageView pic;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        // Get the position
        resultp = data.get(position);

        // Locate the TextViews in listview_item.xml
        name = (TextView) itemView.findViewById(R.id.tvName);

        // Locate the ImageView in listview_item.xml
        pic = (ImageView) itemView.findViewById(R.id.pic1);

        // Capture position and set results to the TextViews
        name.setText(resultp.get(MainActivity.NAME));
        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
       // imageLoader.DisplayImage(resultp.get(MainActivity.IMAGE), pic);
        // Capture ListView item click
        itemView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position
                resultp = data.get(position);
                Intent intent = new Intent(context, SingleItemView.class);
                // Pass all data rank
                intent.putExtra("name", resultp.get(MainActivity.NAME));
                // Pass all data rank
                intent.putExtra("version", resultp.get(MainActivity.VERSION));
                // Pass all data country
                intent.putExtra("released", resultp.get(MainActivity.RELEASED));
                // Pass all data population
                intent.putExtra("api",resultp.get(MainActivity.API));
                // Pass all data flag
                intent.putExtra("image", resultp.get(MainActivity.IMAGE));
                // Start SingleItemView Class
                context.startActivity(intent);

            }
        });
        return itemView;
    }

}