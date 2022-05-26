package com.example.days6.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.days6.R;
import com.example.days6.model.User;
import com.example.days6.response.ResponeLogin;
import com.example.days6.rest.ApiClient;
import com.example.days6.rest.ApiInterface;
import com.example.days6.utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.btnLogin)
    Button login;

    @BindView(R.id.etUsername)
    EditText etUserName;

    @BindView(R.id.etPassword)
    EditText etPassword;

    ApiInterface apiService;
    SessionManager sessionManager;
    private static final String Tag = "LoginActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);
        ButterKnife.bind(this);

        apiService = ApiClient.getClient().create(ApiInterface.class);
        sessionManager = new SessionManager(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginUser();
            }
        });

    }
    //handle bahasa
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.action_settings){
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void loginUser() {
        String UserName = etUserName.getText().toString();
        String Password = etPassword.getText().toString();

        apiService.login(UserName,Password).enqueue(new Callback<ResponeLogin>() {
            @Override
            public void onResponse(Call<ResponeLogin> call, Response<ResponeLogin> response) {
                if (response.isSuccessful()){

                    User userLogged = response.body().getUser();
                    sessionManager.createLoginSession(userLogged);

                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"User Tidak Ditemukan",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponeLogin> call, Throwable t) {
                Log.e(Tag,"failure: " + t.getLocalizedMessage());
                Toast.makeText(LoginActivity.this,"Gagal Terhubung ke server",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
