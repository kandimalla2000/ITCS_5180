package com.uncc.inclass05;
/**
 * InClass #05
 * File Name:AppCategoriesFragment.java
 * Full Name of the student:
 * 1. Sai Kandimalla
 * 2. Andy Le
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppCategoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppCategoriesFragment extends Fragment {

    Button btnEditProfile, btnLogOut;
    TextView tvWelcomeUser;

    ListView lstAppCategories;
    ArrayList<String> appCategoriesList;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_ACCOUNT = "ARG_PARAM_ACCOUNT";
    private static final String ARG_PARAM_ACCOUNT_TOKEN = "TOKEN";


    private DataServices.Account account;
    private String token;

    public AppCategoriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param account This object hold the information of an account.
     * @return A new instance of fragment AppCategoriesFragment.
     */
    public static AppCategoriesFragment newInstance(String token, DataServices.Account account) {
       AppCategoriesFragment accountFragment = new AppCategoriesFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_ACCOUNT, account);
        args.putString(ARG_PARAM_ACCOUNT_TOKEN,token);
        accountFragment.setArguments(args);
        return accountFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            account = (DataServices.Account) getArguments().getSerializable(ARG_PARAM_ACCOUNT);
            token = getArguments().getString(ARG_PARAM_ACCOUNT_TOKEN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("App Categories");
        View view = inflater.inflate(R.layout.fragment_app_categories, container, false);

        //mapping components on view and show data from account variable.
        btnLogOut = view.findViewById(R.id.btnLogOut);
        tvWelcomeUser = view.findViewById(R.id.txtWelcomeUser);
        lstAppCategories = view.findViewById(R.id.lstAppCategories);


        tvWelcomeUser.setText("Welcome " + account.getName());
        /*ArrayList<String> lstData = new ArrayList<>();
        lstData.add("Top Free Apps");
        lstData.add("Top Free Apps 1");
        lstData.add("Top Free Apps 2");
        lstData.add("Top Free Apps 3");*/

        /**
         * The app categories should be retrieved from DataServices by calling
         * getAppCategories method. Note that you should provide the token retrieved during
         * login/registration in order to retrieve the list of categories.
         */
        DataServices.getAppCategories(token, new DataServices.DataResponse<String>() {
            @Override
            public void onSuccess(ArrayList<String> data) {
                appCategoriesList = (ArrayList<String>) data.clone();
            }

            @Override
            public void onFailure(DataServices.RequestException exception) {

            }
        });

        ListAppCategoriesAdapter myAdapter=new ListAppCategoriesAdapter(getContext(),appCategoriesList);
        lstAppCategories.setAdapter(myAdapter);

        btnLogOut.setOnClickListener(v -> {
            //delete the account stored in the Main Activity
            iMainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.deleteAccount(null);

            //replace this fragment with the Login Fragment
            mainActivity.openLoginWindow();
        });

        return view;
    }



}