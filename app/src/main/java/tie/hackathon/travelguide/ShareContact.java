package tie.hackathon.travelguide;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import utils.Constants;
import utils.Services;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShareContact extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.create)
    Button create;
    private static final int ACTIVITY_CREATE = 0, ACTIVITY_SCAN = 1;
    private SharedPreferences s;
    private SharedPreferences.Editor e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_contact);

        ButterKnife.bind(this);

        s = PreferenceManager.getDefaultSharedPreferences(this);
        e = s.edit();

        create.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public void onClick(View view) {

        Intent qrDroid;
        switch (view.getId()) {

            case R.id.create:
                //Create a new Intent to send to QR Droid
//                try {
//                    val i = Intent(Intent.ACTION_SEND)
//                    i.type = "text/plain"
//                    i.putExtra(Intent.EXTRA_SUBJECT, "GIT")
//                    var sAux = "\nLet me recommend you this application\n\n"
//                    sAux = sAux + "https://play.google.com/store/apps/details?id=com.karnataka.election \n\n"
//                    i.putExtra(Intent.EXTRA_TEXT, sAux)
//                    startActivity(Intent.createChooser(i, "choose one"))
//                } catch (e:Exception){ //e.toString();
//            }

                shareTextUrl();
                break;
        }
    }

    private void shareTextUrl() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, "Way Faring");
        share.putExtra(Intent.EXTRA_TEXT, "\nLet me recommend you this application\n\n" + "http://www.wayfaring.com");

        startActivity(Intent.createChooser(share, "Share link!"));
    }
}
