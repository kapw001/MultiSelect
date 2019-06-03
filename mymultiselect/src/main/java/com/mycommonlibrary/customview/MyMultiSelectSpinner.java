package com.mycommonlibrary.customview;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.mycommonlibrary.multselect.MyMultiSelectDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class MyMultiSelectSpinner extends TextInputLayout implements View.OnClickListener, MyMultiSelectDialogFragment.MultiItemSelectedListener {


    private FragmentManager fragmentManager;
    private MyMultiSelectDialogFragment.MultiItemSelectedListener itemSelectedListener;

    private MyMultiSelectDialogFragment dialogTest;

    public MyMultiSelectSpinner(Context context) {
        super(context);

        init(null);
    }

    public MyMultiSelectSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MyMultiSelectSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        setFocusable(false);
        if (getEditText() != null) getEditText().setFocusable(false);

    }

    public void setItemSelectedListener(final MyMultiSelectDialogFragment.MultiItemSelectedListener listener) {

        this.itemSelectedListener = listener;

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();


        setFocusable(false);

        if (getEditText() != null) {
            getEditText().setFocusable(false);
            getEditText().setOnClickListener(this);
        }

    }

//    public void setMultiDialogFragment(MyMultiSelectDialogFragment dialogTest) {
//
//        this.dialogTest = dialogTest;
//
//        this.dialogTest.setMultiItemSelectedListener(this);
//
//    }

    public <T> void setMultiDialogList(List<T> list, FragmentManager fragmentManager, String title) {
        if (title == null) title = "Choose anything ";
        this.fragmentManager = fragmentManager;
        this.dialogTest = MyMultiSelectDialogFragment.getInstance(title, false, false, -1, list);
        this.dialogTest.setMultiItemSelectedListener(this);
    }


    public List getSelectedList() {
        return dialogTest != null ? dialogTest.getSelectedList() : new ArrayList<>();
    }

    @Override
    public void onClick(View v) {

        if (dialogTest != null) dialogTest.show(fragmentManager, "Multiselect");
        else showToast("MulitDialog fragment list is not set");
    }


    @Override
    public void onMultiItemSelected(List list, String selectedNames) {


        if (!TextUtils.isEmpty(selectedNames)) {

            if (getEditText() != null) getEditText().setText(selectedNames);
        }

        if (itemSelectedListener != null)
            itemSelectedListener.onMultiItemSelected(list, selectedNames);

    }

    private void showToast(String s) {

        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();

    }
}
