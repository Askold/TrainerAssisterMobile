
package ru.silonov.assister.trainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.circularreveal.CircularRevealHelper;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import ru.silonov.assister.R;
import ru.silonov.assister.model.login.Client;

public class ClientPageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ClientsAdapter adapter;  //FeedbackAdapter
    private List<Client> clientList; //Feedback

    MaterialButton btnCreateProgram;
    MaterialTextView textViewClient;

    int trainerId;
    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_page);

        trainerId = (int) getIntent().getSerializableExtra("trainerId");
        client = (Client) getIntent().getSerializableExtra("client");
        String name = client.getName()+" "+client.getSurname();

        textViewClient = findViewById(R.id.textViewClient);
        textViewClient.setText(name);

        btnCreateProgram = findViewById(R.id.btnCreateProgram);


        // RecyclerView for feedbacks


        btnCreateProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClientPageActivity.this, CreateProgramActivity.class);
                intent.putExtra("trainerId", trainerId);
                intent.putExtra("client", client);
                startActivity(intent);
            }
        });



    }
}