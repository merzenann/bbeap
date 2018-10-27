package com.example.merzensumagaysay.bbeap.blueprint;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.merzensumagaysay.bbeap.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminBlueprint extends AppCompatActivity
{

    SafeService safeService;

    String check;
    CheckBox MFCExit, backgateExit, mainExit, mainGateExit, LRTExit;
    Button btnSendToUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_blueprint);

        setTitle("Safe Exits - Blueprint");


        MFCExit = (CheckBox) findViewById(R.id.MFCExit);
        backgateExit = (CheckBox) findViewById(R.id.backgateExit);
        mainExit = (CheckBox) findViewById(R.id.mainExit);
        mainGateExit = (CheckBox) findViewById(R.id.mainGateExit);
        LRTExit = (CheckBox) findViewById(R.id.LRTExit);

        btnSendToUsers = (Button) findViewById(R.id.btnSendToUsers);
        btnSendToUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!MFCExit.isChecked() && !backgateExit.isChecked() && !mainExit.isChecked() && !mainGateExit.isChecked() && !LRTExit.isChecked()) {
                    check = "noCheck";
                }

                if (MFCExit.isChecked()) {
                    check = "MFCExit";
                }

                if (backgateExit.isChecked()) {
                    check = "backgateExit";

                }

                if (mainExit.isChecked()) {
                    check = "mainExit";

                }

                if (mainGateExit.isChecked()) {
                    check = "mainGateExit";

                }

                if (LRTExit.isChecked()) {
                    check = "LRTExit";

                }


                switch (check) {
                    case "noCheck":
                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(AdminBlueprint.this);
                        mBuilder.setTitle("NO EXITS SELECTED");
                        mBuilder.setMessage("Please choose safe exits");
                        mBuilder.setCancelable(false);
                        mBuilder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();
                            }
                        });
                        mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();
                            }
                        });

                        AlertDialog alertDialog = mBuilder.create();
                        alertDialog.show();
                        break;

                    case "MFCExit":

                        break;

                    case "backgateExit":

                        break;

                    case "mainExit":

                        break;

                    case "mainGateExit":

                        break;

                    case "LRTExit":

                        break;
                }

            }
        });
    }

}