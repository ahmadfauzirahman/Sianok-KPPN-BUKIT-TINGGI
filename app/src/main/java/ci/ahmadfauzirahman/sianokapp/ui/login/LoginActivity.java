package ci.ahmadfauzirahman.sianokapp.ui.login;

import android.app.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import ci.ahmadfauzirahman.sianokapp.MainActivity;
import ci.ahmadfauzirahman.sianokapp.Model.AkunModel;
import ci.ahmadfauzirahman.sianokapp.R;
import ci.ahmadfauzirahman.sianokapp.Response.AkunResponse;
import ci.ahmadfauzirahman.sianokapp.rest.ApiClient;
import ci.ahmadfauzirahman.sianokapp.rest.ApiInterface;
import ci.ahmadfauzirahman.sianokapp.ui.login.LoginViewModel;
import ci.ahmadfauzirahman.sianokapp.ui.login.LoginViewModelFactory;
import ci.ahmadfauzirahman.sianokapp.utils.SessionManager;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    ProgressBar loadingProgressBar;
    ApiInterface apiService =
            ApiClient.getClient().create(ApiInterface.class);
    private String TAG = this.getClass().getName();
    SessionManager sessionManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        loadingProgressBar = findViewById(R.id.loading);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });

    }

    private void login(String usernameEditText, String passwordEditText) {
        AlertDialog alertDialog = new AlertDialog.Builder(
                LoginActivity.this).create();
        if (TextUtils.isEmpty(usernameEditText.toString())) {
            alertDialog.setMessage("Username Tidak Boleh Kosong");
            alertDialog.show();
        }

        if (TextUtils.isEmpty(passwordEditText.toString())) {
            alertDialog.setMessage("Password Tidak Boleh Kosong");
            alertDialog.show();
        }

        loadingProgressBar.setVisibility(View.VISIBLE);

        apiService.login(usernameEditText, passwordEditText).enqueue(new Callback<AkunResponse>() {
            @Override
            public void onResponse(Call<AkunResponse> call, Response<AkunResponse> response) {
                if (response.isSuccessful()) {
                    Boolean success = response.body().getCon();
                    if (success) {
                        AkunModel akunModel = response.body().getResults();
                        sessionManager.createLoginSession(akunModel);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        loadingProgressBar.setVisibility(View.GONE);
                    } else {
                        Log.d(TAG, "onResponsePassword: Password Salah & Username Salah");
                        Toast.makeText(getApplicationContext(), "Username & Password Salah", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<AkunResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Anda Tidak Terhubung Kejaringan", Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.getLocalizedMessage());
            }
        });
    }

}
