package com.servustech.mediatorlivedatasample;

import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.support.annotation.Nullable;
import android.widget.TextView;

public class TestViewModel extends ViewModel {

    private MutableLiveData<Integer> leftOperand = new MutableLiveData<>();
    private MutableLiveData<Integer> rightOperand = new MutableLiveData<>();
    private MediatorLiveData<Integer> result = new MediatorLiveData<>();

    public void setLeftOperand(String data) {
        if(data.equals("")){
            leftOperand.setValue(0);
        } else
        leftOperand.setValue(Integer.parseInt(data));
    }

    public void setRightOperand(String data) {
        if(data.equals("")){
            rightOperand.setValue(0);
        } else
        rightOperand.setValue(Integer.parseInt(data));
    }

    public MediatorLiveData<Integer> getMediatorLiveData() {
        result.addSource(leftOperand, i -> result.setValue(plusOperands()));
        result.addSource(rightOperand, i -> result.setValue(plusOperands()));
        return result;
    }

    private int plusOperands(){
        return setReturnValueIfIsEmpty(leftOperand.getValue()) + setReturnValueIfIsEmpty(rightOperand.getValue());
    }

    public Integer setReturnValueIfIsEmpty(Integer val){
        if(val == null) return 0; return val;
    }
}
