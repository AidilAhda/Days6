package com.example.days6.ui.dev;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.days6.databinding.FragmentDevBinding;


public class DevFragment extends Fragment {

    private FragmentDevBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DevViewModel devViewModel = new ViewModelProvider(this).get(DevViewModel.class);

        binding = FragmentDevBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDev;
        devViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}