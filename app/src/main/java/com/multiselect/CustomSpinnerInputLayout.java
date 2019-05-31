package com.multiselect;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListPopupWindow;
import android.widget.Toast;

import com.mymultiselect.multselect.ModelMultiSelect;
import com.mymultiselect.popup.MyPopupWindow;

import java.util.List;

public class CustomSpinnerInputLayout<T extends ModelMultiSelect> extends TextInputLayout {


    private List<T> list;
    private MyPopupWindow<T> myPopupWindow;

    private ItemSelectedListener<T> itemSelectedListener;

    public CustomSpinnerInputLayout(Context context) {
        super(context);

        init(null);
    }

    public CustomSpinnerInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomSpinnerInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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

    public void setItemSelectedListener(ItemSelectedListener<T> listener) {

        this.itemSelectedListener = listener;

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();


        setFocusable(false);

        if (getEditText() != null) getEditText().setFocusable(false);


        getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (list != null)
                    showPopUp(list);
                else
                    Toast.makeText(getContext(), "Please set list in spinner", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void showPopUp(final List<T> list) {

        myPopupWindow = new MyPopupWindow<T>(this, list);

        myPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                getEditText().setText(myPopupWindow.getSelectedItem().toString());

                if (itemSelectedListener != null)
                    itemSelectedListener.onItemChanged(myPopupWindow.getSelectedItem());

                myPopupWindow.dismiss();


            }
        });

        myPopupWindow.show();

    }


    public int getSelectedPosition() {

        return myPopupWindow.getSelectedPosition();
    }

    public T getItem() {


        return myPopupWindow != null ? myPopupWindow.getSelectedItem() : null;
    }


    public interface ItemSelectedListener<T> {

        void onItemChanged(T o);

    }
}
