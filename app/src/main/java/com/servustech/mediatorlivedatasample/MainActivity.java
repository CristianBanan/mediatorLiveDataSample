package com.servustech.mediatorlivedatasample;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.servustech.mediatorlivedatasample.databinding.ActivityMainBinding;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding screen = DataBindingUtil.setContentView(this, R.layout.activity_main);
        screen.setLifecycleOwner(this);
        TestViewModel model = ViewModelProviders.of(this).get(TestViewModel.class);

        screen.leftOperandEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                model.setLeftOperand(screen.leftOperandEditText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
        screen.rightOperandEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                model.setRightOperand(screen.rightOperandEditText.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        model.getMediatorLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer i) {
                screen.resultEditText.setText(i.toString());
            }
        });

    }
}
