package edu.orangecoastcollege.cs273.dkim127.tapcounter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Context context = this;

    // associate this controller with the views
    private Button mTapButton;
    private Button mResetButton;
    private TextView mNumTaps;
    private Counter counter;

    // saving data
    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor sharedPrefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiate the model
        counter = new Counter();

        // initialize private preferences store
        sharedPrefs = this.getPreferences(Context.MODE_PRIVATE);
        sharedPrefsEditor = sharedPrefs.edit();

        // load from the private preferences store
        counter.setCount(sharedPrefs.getInt(getString(R.string.saved_tap_count),0));

        // actually connect the views to this controller
        mTapButton = (Button) findViewById(R.id.tapButton);
        mNumTaps = (TextView) findViewById(R.id.tapCountTextView);
        mResetButton = (Button) findViewById(R.id.resetButton);

        // set the text of textview to reflect the current count
        mNumTaps.setText(String.valueOf(counter.getCount()));

        // add listener links
        mTapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter.tap();
                mNumTaps.setText(String.valueOf(counter.getCount()));
                sharedPrefsEditor.putInt(getString(R.string.saved_tap_count), counter.getCount());
                sharedPrefsEditor.commit();
            }
        });
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(context)
                        .setTitle("Reset Warning")
                        .setMessage(R.string.reset_warning)
                        .setPositiveButton(R.string.reset_confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                counter.reset();
                                mNumTaps.setText(String.valueOf(counter.getCount()));
                                Toast.makeText(context, R.string.approval, Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton(R.string.reset_abort, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context, R.string.disappoint,Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();

            }
        });

    }
}
