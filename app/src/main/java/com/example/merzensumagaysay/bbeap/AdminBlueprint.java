package com.example.merzensumagaysay.bbeap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminBlueprint extends AppCompatActivity {

    private static final String TAG = "AdminBlueprint";
    SafeService safeService;

    CheckBox MFCExit, backgateExit, mainExit, mainGateExit, LRTExit;
    Button btnSendToUsers;

    EditText instruction;

    private int userID;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_blueprint);

        setTitle("Safe Exits - Blueprint");

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(AdminBlueprint.this);
        userID = prefs.getInt("userID", 1);


        MFCExit = (CheckBox) findViewById(R.id.MFCExit);
        backgateExit = (CheckBox) findViewById(R.id.backgateExit);
        mainExit = (CheckBox) findViewById(R.id.mainExit);
        mainGateExit = (CheckBox) findViewById(R.id.mainGateExit);
        LRTExit = (CheckBox) findViewById(R.id.LRTExit);

        instruction = (EditText)findViewById(R.id.instruc1);

        btnSendToUsers = (Button) findViewById(R.id.btnSendToUsers);
        btnSendToUsers.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(AdminBlueprint.this, UserViewBlueprint.class);
                AdminBlueprint.this.startActivity(i);
                //JUST TO CHECK KUNG NAPAPASA - INTENT
                //PUSH NOTIF HERE
                String ins = instruction.getText().toString();
                try {
                    setInstruction(1, ins);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        instruct();

        getExits();

    }


    public void setInstruction(int exitID, String instruction) throws IOException {

        Call<String> call = safeService.sendMessage(exitID, instruction);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Toast.makeText(AdminBlueprint.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(AdminBlueprint.this, "Error" + t.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }
//
    public void instruct(){
        safeService = APIUtils.getSafeService();

        Call<List<SafeExits>> call = safeService.getMessage();
        call.enqueue(new Callback<List<SafeExits>>() {
            @Override
            public void onResponse(Call<List<SafeExits>> call, Response<List<SafeExits>> response) {
                final List<SafeExits> ins = response.body();
                for (final  SafeExits value : ins)
                {
                    if(value.getExitID() == 1)
                    {
                        instruction.setText(value.getInstruction());

                    }
                }
            }

            @Override
            public void onFailure(Call<List<SafeExits>> call, Throwable t) {

            }
        });
    }



    public void getExits(){
        safeService = APIUtils.getSafeService();

        Call<List<SafeExits>> call = safeService.getExit();
        call.enqueue(new Callback<List<SafeExits>>() {
            @Override
            public void onResponse(Call<List<SafeExits>> call, Response<List<SafeExits>> response) {
                final List<SafeExits> sl = response.body();

                for (final SafeExits value : sl) {
                    CompoundButton.OnCheckedChangeListener checkListener = new CompoundButton.OnCheckedChangeListener()
                    {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b)
                        {
                            Log.d(TAG, "onCheckedChanged: " + compoundButton.isChecked());
                            if(compoundButton.isChecked())
                            {
                                setExits(value.getExitID(), 1);
                            }
                            else
                            {
                                setExits(value.getExitID(), 0);
                            }
                        }
                    };

                    if (value.getExitID() == 1)
                    {
                        if(value.getiStatus() == 1)
                        {
                            MFCExit.setChecked(true);
                        }
                        MFCExit.setOnCheckedChangeListener(checkListener);
                    }

                    else if (value.getExitID() == 2)
                    {
                        if(value.getiStatus() == 1)
                        {
                            backgateExit.setChecked(true);
                        }
                        backgateExit.setOnCheckedChangeListener(checkListener);
                    }

                    else if (value.getExitID() == 3)
                    {
                        if(value.getiStatus() == 1)
                        {
                            mainExit.setChecked(true);
                        }
                        mainExit.setOnCheckedChangeListener(checkListener);
                    }

                    else if (value.getExitID() == 4)
                    {
                        if(value.getiStatus() == 1)
                        {
                            mainGateExit.setChecked(true);
                        }
                        mainGateExit.setOnCheckedChangeListener(checkListener);
                    }

                    else if (value.getExitID() == 5)
                    {
                        if(value.getiStatus() == 1)
                        {
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

    public void setExits(int exitID, int iStatus)
    {

        safeService = APIUtils.getSafeService();

        Call<ResponseBody> call = safeService.updateExit(exitID, iStatus);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body());
                } else {
                    Toast.makeText(AdminBlueprint.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(AdminBlueprint.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
