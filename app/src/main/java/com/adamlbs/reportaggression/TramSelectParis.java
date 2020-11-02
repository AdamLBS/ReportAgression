/*
 * *
 *  * Created by Adam Elaoumari on 02/11/20 02:03
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 02/11/20 00:43
 *
 */

package com.adamlbs.reportaggression;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class TramSelectParis extends AppCompatActivity {
    private SessionHandler session;
    public ImageButton t1, t2 ,t3a, t3b, t4, t5, t6, t7, t8, t11;
    private SharedPreferences sharedPreference;
    private String text;
    Activity context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        getSupportActionBar().hide();
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tram_select_paris);
        session = new SessionHandler(getApplicationContext());
        User user = session.getUserDetails();
        addListenerOnButton();
    }

    //get the selected dropdown list value
    public void addListenerOnButton() {
        t1 = (ImageButton) findViewById(R.id.tp1);
        ImageButton t2 = findViewById(R.id.tp2);
        t3a = (ImageButton) findViewById(R.id.tp3a);
        t3b = (ImageButton) findViewById(R.id.tp3b);
        t4 = (ImageButton) findViewById(R.id.tp4);
        t5 = (ImageButton) findViewById(R.id.tp5);
        t6 = (ImageButton) findViewById(R.id.tp6);
        t7 = (ImageButton) findViewById(R.id.tp7);
        t8 = (ImageButton) findViewById(R.id.tp8);
        t11 = (ImageButton) findViewById(R.id.tp11);

        t1.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      text = "T1";
                                      Bundle bundle = new Bundle();
                                      BottomSheetDialogParis bottomSheet = new BottomSheetDialogParis();
                                      bundle.putString("text", text);
                                      bottomSheet.setArguments(bundle);
                                      bottomSheet.show(getSupportFragmentManager(),"text");
                                  }



                                  public void newActivity(View v) {
                                  }
                              }

        );


        t2.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      text = "T2";
                                      Bundle bundle = new Bundle();
                                      BottomSheetDialogParis bottomSheet = new BottomSheetDialogParis();
                                      bundle.putString("text", text);
                                      bottomSheet.setArguments(bundle);
                                      bottomSheet.show(getSupportFragmentManager(),"text");
                                  }



                                  public void newActivity(View v) {
                                  }
                              }

        );
        t3a.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      text = "T3A";
                                      Bundle bundle = new Bundle();
                                      BottomSheetDialogParis bottomSheet = new BottomSheetDialogParis();
                                      bundle.putString("text", text);
                                      bottomSheet.setArguments(bundle);
                                      bottomSheet.show(getSupportFragmentManager(),"text");
                                  }



                                  public void newActivity(View v) {
                                  }
                              }

        );
        t3b.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       text = "T3B";
                                       Bundle bundle = new Bundle();
                                       BottomSheetDialogParis bottomSheet = new BottomSheetDialogParis();
                                       bundle.putString("text", text);
                                       bottomSheet.setArguments(bundle);
                                       bottomSheet.show(getSupportFragmentManager(),"text");
                                   }



                                   public void newActivity(View v) {
                                   }
                               }

        );
        t4.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       text = "T4";
                                       Bundle bundle = new Bundle();
                                       BottomSheetDialogParis bottomSheet = new BottomSheetDialogParis();
                                       bundle.putString("text", text);
                                       bottomSheet.setArguments(bundle);
                                       bottomSheet.show(getSupportFragmentManager(),"text");
                                   }



                                   public void newActivity(View v) {
                                   }
                               }

        );
        t5.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       text = "T5";
                                       Bundle bundle = new Bundle();
                                       BottomSheetDialogParis bottomSheet = new BottomSheetDialogParis();
                                       bundle.putString("text", text);
                                       bottomSheet.setArguments(bundle);
                                       bottomSheet.show(getSupportFragmentManager(),"text");
                                   }



                                   public void newActivity(View v) {
                                   }
                               }

        );
        t6.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       text = "T6";
                                       Bundle bundle = new Bundle();
                                       BottomSheetDialogParis bottomSheet = new BottomSheetDialogParis();
                                       bundle.putString("text", text);
                                       bottomSheet.setArguments(bundle);
                                       bottomSheet.show(getSupportFragmentManager(),"text");
                                   }



                                   public void newActivity(View v) {
                                   }
                               }

        );
        t7.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       text = "T7";
                                       Bundle bundle = new Bundle();
                                       BottomSheetDialogParis bottomSheet = new BottomSheetDialogParis();
                                       bundle.putString("text", text);
                                       bottomSheet.setArguments(bundle);
                                       bottomSheet.show(getSupportFragmentManager(),"text");
                                   }



                                   public void newActivity(View v) {
                                   }
                               }

        );
        t8.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       text = "T8";
                                       Bundle bundle = new Bundle();
                                       BottomSheetDialogParis bottomSheet = new BottomSheetDialogParis();
                                       bundle.putString("text", text);
                                       bottomSheet.setArguments(bundle);
                                       bottomSheet.show(getSupportFragmentManager(),"text");
                                   }



                                   public void newActivity(View v) {
                                   }
                               }

        );
        t11.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       text = "T11e";
                                       Bundle bundle = new Bundle();
                                       BottomSheetDialogParis bottomSheet = new BottomSheetDialogParis();
                                       bundle.putString("text", text);
                                       bottomSheet.setArguments(bundle);
                                       bottomSheet.show(getSupportFragmentManager(),"text");
                                   }



                                   public void newActivity(View v) {
                                   }
                               }

        );
    }


}