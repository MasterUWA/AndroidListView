package cauotech.ng.androidlistview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleItemView extends Activity {

    // Declare Variables
    String name;
    String version;
    String released;
    String api;
    String pic;
    String position;
    //ImageLoader imageLoader = new ImageLoader(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.singleitemview);

        Intent i = getIntent();
        // Get the result of rank
        name = i.getStringExtra("name");
        // Get the result of country
        version = i.getStringExtra("version");
        // Get the result of country
        released = i.getStringExtra("released");
        // Get the result of population
        api = i.getStringExtra("api");
        // Get the result of flag
        pic = i.getStringExtra("pic");

        // Locate the TextViews in singleitemview.xml
        TextView txtname = (TextView) findViewById(R.id.tvName);
        TextView txtversion = (TextView) findViewById(R.id.tvVersn);
        TextView txtreleased = (TextView) findViewById(R.id.tvRelDate);
        TextView txtapi = (TextView) findViewById(R.id.tvApi);

        // Locate the ImageView in singleitemview.xml
        ImageView imgPic = (ImageView) findViewById(R.id.imageView1);

        // Set results to the TextViews
        txtname.setText(name);
        txtversion.setText(version);
        txtreleased.setText(released);
        txtapi.setText(api);

        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
        //imageLoader.DisplayImage(pic, imgPic);


        //back button
        Button btnPrev = (Button) findViewById(R.id.btnBack);
        btnPrev.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent xn = new Intent(SingleItemView.this, MainActivity.class);
                startActivity(xn);
            }
        });//close back button
    }
}