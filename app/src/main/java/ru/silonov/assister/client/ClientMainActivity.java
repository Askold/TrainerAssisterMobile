package ru.silonov.assister.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import ru.silonov.assister.R;
import ru.silonov.assister.authorization.EnterActivity;
import ru.silonov.assister.model.login.Client;

public class ClientMainActivity extends AppCompatActivity {


    MaterialTextView nameTextView, loginTextView;

    MaterialButton btnPrograms, btnExit, btnCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_main);

        nameTextView = findViewById(R.id.vNameSurname1);
        loginTextView = findViewById(R.id.vLogin1);

        btnPrograms = findViewById(R.id.btnPrograms);
        btnCurrent = findViewById(R.id.btnCurrent);
        btnExit = findViewById(R.id.Exit);

        Client transfer = (Client) getIntent().getSerializableExtra("client");
        System.out.println(transfer);
        String name = transfer.getName()+" "+transfer.getSurname();
        nameTextView.setText(name);
        loginTextView.setText(transfer.getLogin());

        btnPrograms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClientMainActivity.this, MyProgramsActivity.class);
                intent.putExtra("clientId", transfer.getUserId());
                startActivity(intent);
            }
        });

        btnCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClientMainActivity.this, CurrentProgramActivity.class);
                startActivity(intent);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClientMainActivity.this, EnterActivity.class);
                startActivity(intent);
            }
        });
    }
}