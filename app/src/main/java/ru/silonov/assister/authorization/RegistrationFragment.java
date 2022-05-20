package ru.silonov.assister.authorization;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

import ru.silonov.assister.CallbackEnterFragment;
import ru.silonov.assister.R;

public class RegistrationFragment extends Fragment {

    View view;

    TextInputEditText edUserName, edUserSurname, edUserLogin, edUserPassword, edUserPasswordRepeat;
    MaterialButton btnSignUp;

    TextInputLayout tilRole;
    AutoCompleteTextView actRole;

    ArrayList<String> arrayListRole;
    ArrayAdapter<String> arrayAdapterRole;

    CallbackEnterFragment callbackEnterFragment;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public void onAttach(@NonNull Context context) {
        sharedPreferences = context.getSharedPreferences("userFile", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_registration, container, false);

        setDropDownMenu();

        edUserName = view.findViewById(R.id.edUserName);
        edUserSurname = view.findViewById(R.id.edUserSurname);
        edUserLogin = view.findViewById(R.id.edUserLogin);
        edUserPassword = view.findViewById(R.id.edUserPassword);
        edUserPasswordRepeat = view.findViewById(R.id.edUserPasswordRepeat);


        btnSignUp = view.findViewById(R.id.btnSignUp1);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, surname, login, password, rPassword;

                //Добавить проверки на null

                name = edUserName.getText().toString();
                surname = edUserSurname.getText().toString();
                login = edUserLogin.getText().toString();
                password = edUserPassword.getText().toString();
                rPassword = edUserPasswordRepeat.getText().toString();

               if (!password.equals(rPassword)){
                   Toast.makeText(getContext(), "Пароли не совпадают", Toast.LENGTH_LONG).show();
               }else{
                   editor.putString("name", name);
                   editor.putString("surname",  surname);
                   editor.putString("login", login);
                   editor.putString("password", password);
                   editor.commit();

                   if (callbackEnterFragment !=null){
                       if (actRole.getText().toString().equals("Тренер")){
                           callbackEnterFragment.changeTrainerRegistrationFragment();
                       }
                       else {
                           callbackEnterFragment.changeClientRegistrationFragment();
                       }
                   }
               }
            }
        });


        return view;
    }

    public void setCallbackFragment(CallbackEnterFragment callbackEnterFragment){
        this.callbackEnterFragment = callbackEnterFragment;
    }

    public void setDropDownMenu(){
        tilRole = view.findViewById(R.id.til_role);
        actRole = view.findViewById(R.id.act_role);

        arrayListRole = new ArrayList<>();
        arrayListRole.add("Тренер");
        arrayListRole.add("Клиент");

        arrayAdapterRole = new ArrayAdapter<>(this.getContext(), R.layout.dropdown_role_menu, arrayListRole);
        actRole.setAdapter(arrayAdapterRole);

        actRole.setThreshold(1);


    }
}
