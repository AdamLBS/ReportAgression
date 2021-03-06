/*
 * *
 *  * Created by Adam Elaoumari on 13/12/20 00:23
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 13/12/20 00:23
 *
 */

package com.adamlbs.reportaggression;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class BottomSheetDialog extends BottomSheetDialogFragment {
    private final String report_url = "https://api.lineflag.com/report.php";
    private static final String LOCATION = "location";
    private static final String AGGRESSION = "aggression";
    private String location;
    private String agression;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.SheetDialog);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        assert getArguments() != null;
        location = getArguments().getString("text");
        String city = PreferenceManager.getDefaultSharedPreferences(this.getActivity()).getString("city", "null");

        View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);
        ImageButton sexual = (ImageButton) v.findViewById(R.id.sexual);
        ImageButton verbal = (ImageButton) v.findViewById(R.id.verbal);
        ImageButton physical = (ImageButton) v.findViewById(R.id.physical);
        ImageButton stats = (ImageButton) v.findViewById(R.id.stats);
        sexual.setOnClickListener(
                v1 -> {
                    if (Objects.equals(city, "Marseille")) {
                        agression = "Agression sexuelle";
//                            report();
        agression = "Agression sexuelle";
  report();
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Voulez vous contacter la RTM pour signaler l'agression ?");
                        builder.setMessage("Votre signalement a bien été pris en compte."
                                +"\n"
                                +"\n"
                                +"\nLineFlag peut aussi vous mettre en contact avec la RTM pour signaler l'agression." +
                                "\n" +
                                "\nPour ce faire, cliquez sur le bouton ci-dessous." +
                                "\n"+
                                "\nL'application composera le numéro de la plateforme de signalement de la RTM."+
                                "\n" +
                                "\nVous pouvez aussi choisir de ne pas contacter la RTM vis-à-vis de ce signalement.")
                                .setCancelable(false)
                                .setPositiveButton("Contacter la RTM", (dialog, id) -> {
                                    intent.setData(Uri.parse("tel:0800710567"));
                                    startActivity(intent);
                                    Objects.requireNonNull(getActivity()).onBackPressed();

                                });
                        builder.setNeutralButton("Ne pas contacter la RTM", (dialog, whichButton) -> Objects.requireNonNull(getActivity()).onBackPressed());

                        AlertDialog alert = builder.create();
                        alert.show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Votre ville n'est pas compatible");
                        builder.setMessage("LineFlag est pour l'instant seulement compatible avec la ville de Marseille et de Paris" +
                                "\n" +
                                "\nNous n'avons pas pu vous localiser à Marseille." +
                                "\n" +
                                "\nIl se peut que votre GPS soit désactivé, si c'est le cas veuillez l'activer et redémarrer l'application." +
                                "\n" +
                                "\nSi vous le souhaitez vous pouvez changer de ville en cliquant sur le bouton ci dessous, vous pouvez aussi suggérer une ville à ajouter."+
                                "\n" +
                                "\nVous pouvez aussi visualiser les statistiques des lignes mais vous ne pouvez pas signaler d'agression.")
                                .setCancelable(false)
                                .setPositiveButton("Changer de ville", (dialog, id) -> {
                                    Intent i1 = new Intent(getActivity(),DashboardParis.class);
                                    i1.putExtra("key", location); //Optional parameters
                                    startActivity(i1);
                                });
                        builder.setNeutralButton("Ok", (dialog, whichButton) -> {

                        });
                        builder.setNegativeButton("Suggérer une ville", (dialog, whichButton) -> {
                            Intent i1 = new Intent(getActivity(),WebView.class);
                            startActivity(i1);
                        });


                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }

        );
        verbal.setOnClickListener(
                v12 -> {
                    if (Objects.equals(city, "Marseille")) {
                        agression = "Agression verbale";
                        report();
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Voulez vous contacter la RTM pour signaler l'agression ?");
                        builder.setMessage("Votre signalement a bien été pris en compte."
                                +"\n"
                                +"\n"
                                +"\nLineFlag peut aussi vous mettre en contact avec la RTM pour signaler l'agression." +
                                "\n" +
                                "\nPour ce faire, cliquez sur le bouton ci-dessous." +
                                "\n"+
                                "\nL'application composera le numéro de la plateforme de signalement de la RTM."+
                                "\n" +
                                "\nVous pouvez aussi choisir de ne pas contacter la RTM vis-à-vis de ce signalement.")
                                .setCancelable(false)
                                .setPositiveButton("Contacter la RTM", (dialog, id) -> {
                                    intent.setData(Uri.parse("tel:0800710567"));
                                    startActivity(intent);
                                    Objects.requireNonNull(getActivity()).onBackPressed();

                                });
                        builder.setNeutralButton("Ne pas contacter la RTM", (dialog, whichButton) -> Objects.requireNonNull(getActivity()).onBackPressed());

                        AlertDialog alert = builder.create();
                        alert.show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Votre ville n'est pas compatible");
                        builder.setMessage("LineFlag est pour l'instant seulement compatible avec la ville de Marseille et de Paris" +
                                "\n" +
                                "\nNous n'avons pas pu vous localiser à Marseille." +
                                "\n" +
                                "\nIl se peut que votre GPS soit désactivé, si c'est le cas veuillez l'activer et redémarrer l'application." +
                                "\n" +
                                "\nSi vous le souhaitez vous pouvez changer de ville en cliquant sur le bouton ci dessous, vous pouvez aussi suggérer une ville à ajouter."+
                                "\n" +
                                "\nVous pouvez aussi visualiser les statistiques des lignes mais vous ne pouvez pas signaler d'agression.")
                                .setCancelable(false)
                                .setPositiveButton("Changer de ville", (dialog, id) -> {
                                    Intent i1 = new Intent(getActivity(),DashboardParis.class);
                                    i1.putExtra("key", location); //Optional parameters
                                    startActivity(i1);
                                });
                        builder.setNeutralButton("Ok", (dialog, whichButton) -> {

                        });
                        builder.setNegativeButton("Suggérer une ville", (dialog, whichButton) -> {
                            Intent i1 = new Intent(getActivity(),WebView.class);
                            startActivity(i1);
                        });


                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }
        );
        physical.setOnClickListener(
                v14 -> {
                    if (Objects.equals(city, "Marseille")) {
                        agression = "Agression physique";
                        report();
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Voulez vous contacter la RTM pour signaler l'agression ?");
                        builder.setMessage("Votre signalement a bien été pris en compte."
                                +"\n"
                                +"\n"
                                +"\nLineFlag peut aussi vous mettre en contact avec la RTM pour signaler l'agression." +
                                "\n" +
                                "\nPour ce faire, cliquez sur le bouton ci-dessous." +
                                "\n"+
                                "\nL'application composera le numéro de la plateforme de signalement de la RTM."+
                                "\n" +
                                "\nVous pouvez aussi choisir de ne pas contacter la RTM vis-à-vis de ce signalement.")
                                .setCancelable(false)
                                .setPositiveButton("Contacter la RTM", (dialog, id) -> {
                                    intent.setData(Uri.parse("tel:0800710567"));
                                    startActivity(intent);
                                    Objects.requireNonNull(getActivity()).onBackPressed();

                                });
                        builder.setNeutralButton("Ne pas contacter la RTM", (dialog, whichButton) -> Objects.requireNonNull(getActivity()).onBackPressed());

                        AlertDialog alert = builder.create();
                        alert.show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Votre ville n'est pas compatible");
                        builder.setMessage("LineFlag est pour l'instant seulement compatible avec la ville de Marseille et de Paris" +
                                "\n" +
                                "\nNous n'avons pas pu vous localiser à Marseille." +
                                "\n" +
                                "\nIl se peut que votre GPS soit désactivé, si c'est le cas veuillez l'activer et redémarrer l'application." +
                                "\n" +
                                "\nSi vous le souhaitez vous pouvez changer de ville en cliquant sur le bouton ci dessous, vous pouvez aussi suggérer une ville à ajouter."+
                                "\n" +
                                "\nVous pouvez aussi visualiser les statistiques des lignes mais vous ne pouvez pas signaler d'agression.")
                                .setCancelable(false)
                                .setPositiveButton("Changer de ville", (dialog, id) -> {
                                    Intent i1 = new Intent(getActivity(), DashboardParis.class);
                                    i1.putExtra("key", location); //Optional parameters
                                    startActivity(i1);
                                });
                        builder.setNeutralButton("Ok", (dialog, whichButton) -> {

                        });
                        builder.setNegativeButton("Suggérer une ville", (dialog, whichButton) -> {
                            Intent i1 = new Intent(getActivity(), WebView.class);
                            startActivity(i1);
                        });


                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }
        );
        stats.setOnClickListener(
                v13 -> loadStatistics()
        );
        return v;

    }

    private void report() {

                JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put(LOCATION, location);
            request.put(AGGRESSION,agression);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, report_url, request, response -> {

                }, error -> {

                    //Display error message whenever an error occurs
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("User-Agent", "LineFlag-App");
                params.put("language", "fr");

                return params;
            }

        };

        jsArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        requestQueue.add(jsArrayRequest);
        Toast.makeText(getActivity(), agression + " signalée sur la ligne "+location, Toast.LENGTH_SHORT).show();


    }
    public void loadStatistics() {
        Intent i1 = new Intent(getActivity(),Stats.class);
        i1.putExtra("key", location); //Optional parameters
        startActivity(i1);

    }

}

