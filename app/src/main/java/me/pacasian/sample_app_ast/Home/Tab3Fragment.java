package me.pacasian.sample_app_ast.Home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import me.pacasian.sample_app_ast.R;


public class Tab3Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";

    private Button btnTEST3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab3,container,false);
        btnTEST3 = (Button) view.findViewById(R.id.btnTEST3);

        btnTEST3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "TESTING BUTTON CLICK 1",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}