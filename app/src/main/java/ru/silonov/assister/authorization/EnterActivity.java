package ru.silonov.assister.authorization;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import ru.silonov.assister.CallbackEnterFragment;
import ru.silonov.assister.R;

public class EnterActivity extends AppCompatActivity implements CallbackEnterFragment {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        addFragment();

    }

    public void addFragment(){
        EnterFragment fragment = new EnterFragment();
        fragment.setCallbackFragment(this);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }

    public void replaceRegistrationFragment(){
        RegistrationFragment fragment = new RegistrationFragment();
        fragment.setCallbackFragment(this);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }

    public void replaceTrainerRegistrationFragment(){
        TrainerRegistrationFragment fragment = new TrainerRegistrationFragment();
        fragment.setCallbackFragment(this);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }

    public void replaceClientRegistrationFragment(){
        ClientRegistrationFragment fragment = new ClientRegistrationFragment();
        fragment.setCallbackFragment(this);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }

    public void replaceLoginFragment(){
        LoginFragment fragment = new LoginFragment();
        fragment.setCallbackFragment(this);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void changeRegistrationFragment() {
        replaceRegistrationFragment();
    }

    @Override
    public void changeLoginFragment() {
        replaceLoginFragment();
    }

    @Override
    public void changeTrainerRegistrationFragment() {
        replaceTrainerRegistrationFragment();
    }

    @Override
    public void changeClientRegistrationFragment() {
        replaceClientRegistrationFragment();
    }
}

















