package com.mycommonlibrary.customview;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.mycommonlibrary.multselect.ModelMultiSelect;
import com.mycommonlibrary.multselect.MyMultiSelectDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class CustomMultiSpinnerInputLayout<T extends ModelMultiSelect> extends TextInputLayout {


    private List<T> list;

    private String title;
    private FragmentManager fragmentManager;
    private MyMultiSelectDialogFragment.MultiItemSelectedListener<T> itemSelectedListener;

    private MyMultiSelectDialogFragment<T> dialogTest;

    public CustomMultiSpinnerInputLayout(Context context) {
        super(context);

        init(null);
    }

    public CustomMultiSpinnerInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomMultiSpinnerInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        setFocusable(false);

        if (getEditText() != null) getEditText().setFocusable(false);


//        getEditText().setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (list != null) {
//
//                    showPopUp(list);
//                }
//            }
//        });

    }

    public void setItemSelectedListener(final MyMultiSelectDialogFragment.MultiItemSelectedListener<T> listener) {

        this.itemSelectedListener = listener;

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();


        setFocusable(false);

        if (getEditText() != null) getEditText().setFocusable(false);


        getEditText().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (list != null && fragmentManager != null)
                    showPopUp(list);
                else
                    Toast.makeText(getContext(), "Please set list in spinner", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void setList(List<T> list, FragmentManager fragmentManager, String title) {
        this.fragmentManager = fragmentManager;
        this.list = list;
        this.title = title;
    }

    public void showPopUp(final List<T> list) {

        if (title == null) title = "Choose anything ";

        dialogTest = MyMultiSelectDialogFragment.getInstance(title, false, false, -1, list);
        dialogTest.show(fragmentManager, "Multiselect");


        MyMultiSelectDialogFragment.MultiItemSelectedListener<T> selectedListener = new MyMultiSelectDialogFragment.MultiItemSelectedListener<T>() {
            @Override
            public void onMultiItemSelected(List<T> list, String selectedNames) {

                if (!TextUtils.isEmpty(selectedNames)) {

                    getEditText().setText(selectedNames);
                }

                if (itemSelectedListener != null)
                    itemSelectedListener.onMultiItemSelected(list, selectedNames);
            }
        };


        if (dialogTest != null) dialogTest.setMultiItemSelectedListener(selectedListener);


    }


    public List<T> getSelectedList() {
        return dialogTest!=null?dialogTest.getSelectedList():new ArrayList<T>();
    }
}
