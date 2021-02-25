package com.uncc.inclass05;
/**
 * InClass #05
 * File Name:RegisterAccountFragment.java
 * Full Name of the student:
 * 1. Sai Kandimalla
 * 2. Andy Le
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
 * RegisterAccountFragment fragment
 * This fragment should allow a user to create a new account.
 */
public class RegisterAccountFragment extends Fragment {

    Button btnSubmit, btnCancel;
    EditText edtName, edtEmail, edtPassword;


    public RegisterAccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AccountFragment.
     */
    public static RegisterAccountFragment newInstance() {

        return new RegisterAccountFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Register Account");

        // Inflate the layout for this fragment
        View registerAccountView = inflater.inflate(R.layout.fragment_register_account, container, false);

        btnSubmit = registerAccountView.findViewById(R.id.btnSubmit);
        btnCancel = registerAccountView.findViewById(R.id.btnCancel);

        edtName = registerAccountView.findViewById(R.id.edtName);
        edtEmail = registerAccountView.findViewById(R.id.edtEmailAddress);
        edtPassword = registerAccountView.findViewById(R.id.edtPassword);

        btnSubmit.setOnClickListener(v -> {
            //Toast.makeText(getActivity(), "Fragment Login-click on login button", Toast.LENGTH_SHORT).show();
            if (validate()) {
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String passWord = edtPassword.getText().toString();
                //If all the inputs are not empty, you should attempt to signup the user by calling the
                //DataServices.register function
                DataServices.register(name, email, passWord, new DataServices.AuthResponse() {
                    @Override
                    public void onSuccess(String token) {
                        DataServices.getAccount(token, new DataServices.AccountResponse() {
                            @Override
                            public void onSuccess(DataServices.Account account) {
                                iMainActivity mainActivity = (MainActivity) getActivity();
                                mainActivity.setAccountGoToAppCategoriesFragment(token, account);
                            }

                            @Override
                            public void onFailure(DataServices.RequestException exception) {
                                //If login is not successful, show a Toast message indicating that the login was not
                                //successful
                                Toast.makeText(getActivity(), exception.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onFailure(DataServices.RequestException exception) {
                        Toast.makeText(getActivity(), exception.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            /**
             * Clicking “Cancel” should replace this fragment with the Login Fragment.
             */
            @Override
            public void onClick(View v) {
                iMainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.openLoginWindow();
            }
        });

        return registerAccountView;
    }

    /**
     * This method is used to check if an input is valid, if it is not it will send a Toast
     * about what is causing the error
     *
     * @return Returns a Boolean of whether the values for account are valid or not
     */
    public Boolean validate() {
        //Declares the strings
        String email = edtEmail.getText().toString();
        String passWord = edtPassword.getText().toString();
        String name = edtName.getText().toString();

        //Checks name
        if (name.replaceAll("\\s", "").isEmpty()) {
            Toast.makeText(getActivity(), getString(R.string.msgNameErrorEmpty), Toast.LENGTH_SHORT).show();
            return false;
        }

        //Checks email
        if (email.replaceAll("\\s", "").isEmpty()) {
            Toast.makeText(getActivity(), getString(R.string.msgEmailErrorEmpty), Toast.LENGTH_SHORT).show();
            return false;
        }

        //Checks password
        if (passWord.isEmpty()) {
            Toast.makeText(getActivity(), getString(R.string.msgPasswordErrorEmpty), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}