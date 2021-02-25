package com.uncc.inclass05;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.Objects;
/**
 * InClass #05
 * File Name:LoginFragment.java
 * Full Name of the student:
 * 1. Sai Kandimalla
 * 2. Andy Le
 */

/**
 * Login fragment let user login by the valid email and password.
 */
public class LoginFragment extends Fragment {
    Button btnLogin, btnCreateNewAccount;
    EditText edtEmail, edtPassword;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */
    public static LoginFragment newInstance() {

        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Objects.requireNonNull(getActivity()).setTitle("Login");

        // Inflate the layout for this fragment
        View loginView = inflater.inflate(R.layout.fragment_login, container, false);
        edtEmail = loginView.findViewById(R.id.edtEmailAddress);
        edtPassword = loginView.findViewById(R.id.edtPassword);
        edtEmail.setText("b@b.com");
        edtPassword.setText("test123");

        /**
         * Clicking “Login” button, if all the inputs are not empty, you should attempt to login
         * the user by calling the DataServices.login function.
         *
         */
        btnLogin = loginView.findViewById(R.id.btnEditProfile);
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //If there is missing input, show a Toast indicating missing input.
                if (validate()) {
                    String email = edtEmail.getText().toString();
                    String passWord = edtPassword.getText().toString();
                    DataServices.login(email, passWord, new DataServices.AuthResponse() {
                        @Override
                        public void onSuccess(String token) {
                            //If the login is successful, then communicate the returned account with the activity
                            //and replace the current fragment with the Account Fragment.
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
                            //If login is not successful, show a Toast message indicating that the login was not
                            //successful
                            Toast.makeText(getActivity(), exception.toString(), Toast.LENGTH_SHORT).show();
                        }




                    });


                }

            }
        });


        btnCreateNewAccount = loginView.findViewById(R.id.btnLogOut);


        /**
         * Clicking the “Create New Account” should replace this fragment with the Register
         * Fragment.
         */
        btnCreateNewAccount.setOnClickListener(v -> {
            //Toast.makeText(getActivity(), "Fragment Login-click on Create New Account button", Toast.LENGTH_SHORT).show();
            iMainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.openRegisterWindow();

        });

        return loginView;
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