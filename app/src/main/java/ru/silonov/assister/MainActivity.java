//package ru.silonov.assister;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//import com.google.android.material.textfield.TextInputEditText;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import ru.silonov.assister.model.login.LoginRequest;
//import ru.silonov.assister.model.login.Trainer;
//
//public class MainActivity extends AppCompatActivity {
//
//    TextInputEditText login, password;
//    Button btnLogin;
//    Spinner spinner;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_welcome);
//
//        login = findViewById(R.id.edUsername);
//        password = findViewById(R.id.edPassword);
//        btnLogin = findViewById(R.id.btnLogin);
//        spinner = findViewById(R.id.spnrAuth);
//
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (TextUtils.isEmpty(login.getText().toString()) || TextUtils.isEmpty(password.getText().toString()) ){
//                    Toast.makeText(MainActivity.this, "Username / Password required", Toast.LENGTH_LONG).show();
//                }else{
//
//                }
//            }
//        });
//    }
//
//    private void authoriseTrainer(){
//        LoginRequest loginRequest = new LoginRequest(login.getText().toString(), password.getText().toString());
//
//        Call<Trainer> loginResponseCall = ApiClient.getUserService().trainerLogin(loginRequest);
//        loginResponseCall.enqueue(new Callback<Trainer>() {
//            @Override
//            public void onResponse(Call<Trainer> call, Response<Trainer> response) {
//                if (response.isSuccessful()){
//                    Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_LONG).show();
//                }else{
//                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Trainer> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Throwable"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//
//    private void authoriseClient(){
//
//    }
//}