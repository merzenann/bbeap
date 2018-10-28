package com.example.merzensumagaysay.bbeap.blueprint;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.merzensumagaysay.bbeap.R;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminBlueprint extends AppCompatActivity {

    private static final String TAG = "AdminBlueprint";
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

                Intent i = new Intent(AdminBlueprint.this, UserViewBlueprint.class);
                AdminBlueprint.this.startActivity(i);
                //JUST TO CHECK KUNG NAPAPASA
                //PUSH NOTIF HERE
            }

        });

        getExits();

    }

    public void getExits(){
        safeService = APIUtils.getSafeService();

        Call<List<SafeExits>> call = safeService.getExit();
        call.enqueue(new Callback<List<SafeExits>>() {
            @Override
            public void onResponse(Call<List<SafeExits>> call, Response<List<SafeExits>> response) {
                final List<SafeExits> sl = response.body();

                for (final SafeExits value : sl) {
                    CompoundButton.OnCheckedChangeListener checkListener = new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            Log.d(TAG, "onCheckedChanged: " + compoundButton.isChecked());
                            if(compoundButton.isChecked()){
                                setExits(value.getExitID(), 1);
                            }
                            else{
                                setExits(value.getExitID(), 0);
                            }
                        }
                    };

                    if (value.getExitID() == 1) {
                        if(value.getiStatus() == 1){
                            MFCExit.setChecked(true);
                        }
                        MFCExit.setOnCheckedChangeListener(checkListener);

                    } else if (value.getExitID() == 2) {
                        if(value.getiStatus() == 1){
                            backgateExit.setChecked(true);
                        }
                        backgateExit.setOnCheckedChangeListener(checkListener);

                    } else if (value.getExitID() == 3) {
                        if(value.getiStatus() == 1){
                            mainExit.setChecked(true);
                        }
                        mainExit.setOnCheckedChangeListener(checkListener);

                    } else if (value.getExitID() == 4) {
                        if(value.getiStatus() == 1){
                            mainGateExit.setChecked(true);
                        }
                        mainGateExit.setOnCheckedChangeListener(checkListener);

                    } else if (value.getExitID() == 5) {
                        if(value.getiStatus() == 1){
                            LRTExit.setChecked(true);
                        }
                        LRTExit.setOnCheckedChangeListener(checkListener);

                    }

                    Log.d("responsebody ", String.valueOf(value.getiStatus()));
                }
            }

            @Override
            public void onFailure(Call<List<SafeExits>> call, Throwable t) {
                Log.d("responsebody", "onFailure: " + t.getMessage());
            }
        });
    }

    public void setExits(int exitID, int iStatus){

        safeService = APIUtils.getSafeService();

        Call<ResponseBody> call = safeService.updateExit(exitID, iStatus);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                if(response.isSuccessful())
                {
                    Log.d(TAG, "onResponse: " + response.body());
                }
                else
                {
                 Toast.makeText(AdminBlueprint.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
                
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(AdminBlueprint.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }


//    private void sendExit()
//    {
//
//        safeService = APIUtils.getSafeService();
//
//
//        if (!MFCExit.isChecked() && !backgateExit.isChecked() && !mainExit.isChecked() && !mainGateExit.isChecked() && !LRTExit.isChecked())
//        {
//            check = "noCheck";
//        }
//
//        if (MFCExit.isChecked())
//        {
//            check = "MFCExit";
//        }
//
//        if (backgateExit.isChecked())
//        {
//            check = "backgateExit";
//
//        }
//
//        if (mainExit.isChecked())
//        {
//            check = "mainExit";
//
//        }
//
//        if (mainGateExit.isChecked())
//        {
//            check = "mainGateExit";
//
//        }
//
//        if (LRTExit.isChecked())
//        {
//            check = "LRTExit";
//
//        }
//
//
//        switch (check)
//        {
//            case "noCheck":
//                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AdminBlueprint.this);
//                mBuilder.setTitle("NO EXITS SELECTED");
//                mBuilder.setMessage("Please choose safe exits");
//                mBuilder.setCancelable(false);
//                mBuilder.setPositiveButton("Okay", new DialogInterface.OnClickListener()
//                {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i)
//                    {
//
//                        dialogInterface.dismiss();
//                    }
//                });
//
//                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
//                {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i)
//                    {
//                        dialogInterface.dismiss();
//                    }
//                });
//
//                AlertDialog alertDialog = mBuilder.create();
//                alertDialog.show();
//                break;
//
//            case "MFCExit":
//
//                break;
//
//            case "backgateExit":
//
//                break;
//
//            case "mainExit":
//
//                break;
//
//            case "mainGateExit":
//
//                break;
//
//            case "LRTExit":
//
//                break;
//        }
//
//    }


}

