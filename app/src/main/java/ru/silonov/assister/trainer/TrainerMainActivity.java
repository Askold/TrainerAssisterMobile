package ru.silonov.assister.trainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import ru.silonov.assister.R;
import ru.silonov.assister.authorization.EnterActivity;
import ru.silonov.assister.model.login.Trainer;

public class TrainerMainActivity extends AppCompatActivity {

    MaterialTextView nameTextView, loginTextView;

    MaterialButton btnClients, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_main);

        nameTextView = findViewById(R.id.vNameSurname);
        loginTextView = findViewById(R.id.vLogin);

        btnClients = findViewById(R.id.btnClients);
        btnExit = findViewById(R.id.Exit1);

        Trainer transfer = (Trainer) getIntent().getSerializableExtra("transfer");
        System.out.println(transfer.toString());
        String name = transfer.getName()+" "+transfer.getSurname();
        nameTextView.setText(name);
        loginTextView.setText(transfer.getLogin());


        btnClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrainerMainActivity.this, MyClientsActivity.class);
                intent.putExtra("id", transfer.getUserId());
                startActivity(intent);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrainerMainActivity.this, EnterActivity.class);
                startActivity(intent);
            }
        });
    }
}