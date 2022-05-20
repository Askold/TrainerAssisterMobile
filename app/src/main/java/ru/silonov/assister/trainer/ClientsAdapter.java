package ru.silonov.assister.trainer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import ru.silonov.assister.R;
import ru.silonov.assister.model.login.Client;

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ClientsViewHolder> {

    private Context context;
    private List<Client> clientList;
    private OnClientListener mOnClientListener;

    public ClientsAdapter(Context context, List<Client> clientList, OnClientListener onClientListener) {
        this.context = context;
        this.clientList = clientList;
        this.mOnClientListener = onClientListener;
    }

    @NonNull
    @Override
    public ClientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerciew_clients, parent, false);
        return new ClientsViewHolder(view, mOnClientListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientsViewHolder holder, int position) {
        Client client = clientList.get(position);

        String name = client.getName() + " " + client.getSurname();
        holder.textViewName.setText(name);
        holder.textViewLogin.setText(client.getLogin());
        holder.textViewAge.setText(String.valueOf(client.getAge()));
        holder.textViewHeight.setText(String.valueOf(client.getHeight()));
        holder.textViewWeight.setText(String.valueOf(client.getWeight()));
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    class ClientsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        MaterialTextView textViewName, textViewLogin, textViewAge, textViewWeight, textViewHeight;
        OnClientListener onClientListener;

        public ClientsViewHolder(@NonNull View itemView, OnClientListener onClientListener) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.vClientName);
            textViewLogin = itemView.findViewById(R.id.vClientLogin);
            textViewAge = itemView.findViewById(R.id.vClientAge);
            textViewWeight = itemView.findViewById(R.id.vClientWeight);
            textViewHeight = itemView.findViewById(R.id.vClientHeight);

            this.onClientListener = onClientListener;
            itemView.setOnClickListener(this);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onClientListener.onClientClick(getAbsoluteAdapterPosition());
        }
    }

    public interface OnClientListener{
        void onClientClick(int position);
    }
}
