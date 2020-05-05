package com.example.testapp.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.testapp.Constant;
import com.example.testapp.R;

public class ProfileFragment extends Fragment {

    private EditText nameEditText;
    private EditText emailEditText;
    private Button editSaveButton;

    static ProfileFragment newInstance(){
        return new ProfileFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

//        instantiate view
        nameEditText = root.findViewById(R.id.edittext_name);
        emailEditText = root.findViewById(R.id.edittext_email);

        editSaveButton = root.findViewById(R.id.button_edit_save);

        editSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    boolean isEdit = !nameEditText.isEnabled();

                    nameEditText.setEnabled(isEdit);
                    emailEditText.setEnabled(isEdit);
                    editSaveButton.setText(getString(isEdit ? R.string.save : R.string.edit));


                    if(isEdit){
                        updateData(Constant.NAME, nameEditText.getText().toString());
                        updateData(Constant.EMAIL, emailEditText.getText().toString());
                    }



            }
        });
        getData();

        return root;
    }

    //get profile data from sharedpreference
    private void getData(){
        SharedPreferences sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE);
        String name = sharedPref.getString(Constant.NAME, Constant.MY_NAME);
        nameEditText.setText(name);

        String email = sharedPref.getString(Constant.EMAIL, Constant.MY_EMAIL);
        emailEditText.setText(email);

    }

//    save profile data in sharedpreference
    private void updateData(String key, String value){
        SharedPreferences sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }
}
