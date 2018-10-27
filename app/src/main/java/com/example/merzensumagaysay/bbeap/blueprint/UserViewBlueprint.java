package com.example.merzensumagaysay.bbeap.blueprint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;

import com.example.merzensumagaysay.bbeap.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewBlueprint extends AppCompatActivity {


    SafeService safeService;

    CheckBox MFCExit2, backgateExit2, mainExit2, mainGateExit2, LRTExit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_blueprint);

        MFCExit2 = (CheckBox)findViewById(R.id.MFCExit2);
        backgateExit2 = (CheckBox)findViewById(R.id.backgateExit2);
        mainExit2 = (CheckBox)findViewById(R.id.mainExit2);
        mainGateExit2 = (CheckBox)findViewById(R.id.mainGateExit2);
        LRTExit2 = (CheckBox)findViewById(R.id.LRTExit2);
        viewSafeExit();

    }

    private void viewSafeExit(){

        safeService = APIUtils.getSafeService();

        Call<List<SafeExits>> call = safeService.getExit();
        call.enqueue(new Callback<List<SafeExits>>() {
            @Override
            public void onResponse(Call<List<SafeExits>> call, Response<List<SafeExits>> response) {
                List<SafeExits> ls = response.body();

                for (SafeExits value : ls)
                {
                    if(value.getExitID() == 1)
                    {
                        MFCExit2.setChecked(true);
                    }

                    else if(value.getExitID() == 2)
                    {
                        backgateExit2.setChecked(true);
                    }

                    else if(value.getExitID() == 3)
                    {
                        mainExit2.setChecked(true);
                    }

                    else if(value.getExitID() == 4)
                    {
                        mainGateExit2.setChecked(true);
                    }

                    else if(value.getExitID() == 5)
                    {
                        LRTExit2.setChecked(true);
                    }

                    Log.d("responsebody ",String.valueOf(value.getiStatus()));
                }
            }

            @Override
            public void onFailure(Call<List<SafeExits>> call, Throwable t) {
                Log.d("responsebody", "onFailure: " + t.getMessage());
            }
        });
    }
}
