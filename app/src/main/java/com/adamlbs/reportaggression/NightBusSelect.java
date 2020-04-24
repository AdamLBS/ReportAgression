package com.adamlbs.reportaggression;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NightBusSelect extends AppCompatActivity {
    private SessionHandler session;
    public Spinner spinner3, spinner2;
    public ImageButton b3, b145, b509,b518, b521,b526,b530,b533,b535,b540,b582,b583;
    private SharedPreferences sharedPreference;
    private String text;
    Activity context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        getSupportActionBar().hide();

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nightbusselect);
        session = new SessionHandler(getApplicationContext());
        User user = session.getUserDetails();
        addListenerOnButton();
    }

    //get the selected dropdown list value
    public void addListenerOnButton() {
        b3 = (ImageButton) findViewById(R.id.b3);
        b145 = (ImageButton) findViewById(R.id.b145);
        b509 = (ImageButton) findViewById(R.id.b509);
        b518 = (ImageButton) findViewById(R.id.b518);
        b521 = (ImageButton) findViewById(R.id.b521);
        b526 = (ImageButton) findViewById(R.id.b526);
        b530 = (ImageButton) findViewById(R.id.b530);
        b533 = (ImageButton) findViewById(R.id.b533);
        b535 = (ImageButton) findViewById(R.id.b535);
        b540 = (ImageButton) findViewById(R.id.b540);
        b582 = (ImageButton) findViewById(R.id.b582);
        b583 = (ImageButton) findViewById(R.id.b583);

        b3.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                                      SharedPreferences.Editor editor = sp.edit();
                                      text = "B3";
                                      SharedPreference.save(context, text);

                                      Intent i = new Intent(NightBusSelect.this, ReportActivy.class);
                                      i.putExtra("key", text ); //Optional parameters
                                      startActivity(i);
                                  }



                                  public void newActivity(View v) {
                                  }
                              }

        );


        b145.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                                      SharedPreferences.Editor editor = sp.edit();
                                      text = "145";
                                      SharedPreference.save(context, text);

                                      Intent i = new Intent(NightBusSelect.this, ReportActivy.class);
                                      i.putExtra("key", text ); //Optional parameters
                                      startActivity(i);
                                  }



                                  public void newActivity(View v) {
                                  }
                              }

        );
        b509.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                                      SharedPreferences.Editor editor = sp.edit();
                                      text = "509";
                                      SharedPreference.save(context, text);

                                      Intent i = new Intent(NightBusSelect.this, ReportActivy.class);
                                      i.putExtra("key", text ); //Optional parameters
                                      startActivity(i);
                                  }



                                  public void newActivity(View v) {
                                  }
                              }

        );
        b518.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                                      SharedPreferences.Editor editor = sp.edit();
                                      text = "518";
                                      SharedPreference.save(context, text);

                                      Intent i = new Intent(NightBusSelect.this, ReportActivy.class);
                                      i.putExtra("key", text ); //Optional parameters
                                      startActivity(i);
                                  }



                                  public void newActivity(View v) {
                                  }
                              }

        );
        b521.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                                      SharedPreferences.Editor editor = sp.edit();
                                      text = "521";
                                      SharedPreference.save(context, text);

                                      Intent i = new Intent(NightBusSelect.this, ReportActivy.class);
                                      i.putExtra("key", text ); //Optional parameters
                                      startActivity(i);
                                  }



                                  public void newActivity(View v) {
                                  }
                              }

        );
        b526.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                                      SharedPreferences.Editor editor = sp.edit();
                                      text = "526";
                                      SharedPreference.save(context, text);

                                      Intent i = new Intent(NightBusSelect.this, ReportActivy.class);
                                      i.putExtra("key", text ); //Optional parameters
                                      startActivity(i);
                                  }



                                  public void newActivity(View v) {
                                  }
                              }

        );
        b530.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                                      SharedPreferences.Editor editor = sp.edit();
                                      text = "530";
                                      SharedPreference.save(context, text);

                                      Intent i = new Intent(NightBusSelect.this, ReportActivy.class);
                                      i.putExtra("key", text ); //Optional parameters
                                      startActivity(i);
                                  }



                                  public void newActivity(View v) {
                                  }
                              }

        );
        b533.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                                      SharedPreferences.Editor editor = sp.edit();
                                      text = "533";
                                      SharedPreference.save(context, text);

                                      Intent i = new Intent(NightBusSelect.this, ReportActivy.class);
                                      i.putExtra("key", text ); //Optional parameters
                                      startActivity(i);
                                  }



                                  public void newActivity(View v) {
                                  }
                              }

        );
        b535.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                                      SharedPreferences.Editor editor = sp.edit();
                                      text = "535";
                                      SharedPreference.save(context, text);

                                      Intent i = new Intent(NightBusSelect.this, ReportActivy.class);
                                      i.putExtra("key", text ); //Optional parameters
                                      startActivity(i);
                                  }



                                  public void newActivity(View v) {
                                  }
                              }

        );
        b540.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                                      SharedPreferences.Editor editor = sp.edit();
                                      text = "540";
                                      SharedPreference.save(context, text);

                                      Intent i = new Intent(NightBusSelect.this, ReportActivy.class);
                                      i.putExtra("key", text ); //Optional parameters
                                      startActivity(i);
                                  }



                                  public void newActivity(View v) {
                                  }
                              }

        );
        b582.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                                      SharedPreferences.Editor editor = sp.edit();
                                      text = "582";
                                      SharedPreference.save(context, text);

                                      Intent i = new Intent(NightBusSelect.this, ReportActivy.class);
                                      i.putExtra("key", text ); //Optional parameters
                                      startActivity(i);
                                  }



                                  public void newActivity(View v) {
                                  }
                              }

        );
        b583.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                                      SharedPreferences.Editor editor = sp.edit();
                                      text = "583";
                                      SharedPreference.save(context, text);

                                      Intent i = new Intent(NightBusSelect.this, ReportActivy.class);
                                      i.putExtra("key", text ); //Optional parameters
                                      startActivity(i);
                                  }



                                  public void newActivity(View v) {
                                  }
                              }

        );
    }



    public void loadStatistics() {
        Intent i = new Intent(NightBusSelect.this, Stats.class);
        i.putExtra("key", String.valueOf(spinner3.getSelectedItem())); //Optional parameters
        startActivity(i);

    }
}
