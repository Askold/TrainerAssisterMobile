package ru.silonov.assister.authorization;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import ru.silonov.assister.CallbackEnterFragment;
import ru.silonov.assister.R;

public class EnterFragment extends Fragment {

    MaterialButton btnSignUp, btnSignIn;

    CallbackEnterFragment callbackEnterFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enter, container, false);

        btnSignUp = view.findViewById(R.id.btnSignUp);
        btnSignIn = view.findViewById(R.id.btnSignIn);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callbackEnterFragment !=null){
                    callbackEnterFragment.changeRegistrationFragment();
                }
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callbackEnterFragment !=null){
                    callbackEnterFragment.changeLoginFragment();
                }
            }
        });

        return view;
    }

    public void setCallbackFragment(CallbackEnterFragment callbackEnterFragment){
        this.callbackEnterFragment = callbackEnterFragment;
    }
}
