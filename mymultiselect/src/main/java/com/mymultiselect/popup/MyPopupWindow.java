package com.mymultiselect.popup;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListPopupWindow;

import java.util.List;

public class MyPopupWindow<T> extends ListPopupWindow {

    private int selectedPosition = -1;
    private View view;
    private List<T> objectList;
    private ArrayAdapter<T> adapter;

//    public MyPopupWindow(@NonNull Context context) {
//        super(context);
//    }

//    public MyPopupWindow(@NonNull Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public MyPopupWindow(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    public MyPopupWindow(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }


    public MyPopupWindow(View view, List<T> objectList) {
        super(view.getContext());
        this.view = view;
        this.objectList = objectList;
        adapter = new ArrayAdapter<T>(this.view.getContext(), android.R.layout.simple_list_item_1, this.objectList);

        setAdapter(adapter);


        setAnchorView(view);
        setWidth(ListPopupWindow.WRAP_CONTENT);
        setHeight(ListPopupWindow.WRAP_CONTENT);
        int mContentWidth = ListUtils.measureListContentWidth(adapter, view.getContext());

        //        int mContentHeight = ListUtils.measureListContentHeight(adapter, view.getContext());

        setContentWidth(mContentWidth);
        setModal(true);

//        Toast.makeText(view.getContext(), "" + mContentWidth, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setOnItemClickListener(@Nullable final AdapterView.OnItemClickListener clickListener) {
//        super.setOnItemClickListener(clickListener);

        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                if (clickListener != null)
                    clickListener.onItemClick(parent, view, position, id);
            }
        };

        super.setOnItemClickListener(onItemClickListener);

    }

    public int getSelectedPosition() {
        return selectedPosition;
    }
//    public void setItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
//
//        setOnItemClickListener(onItemClickListener);
//    }

//    public void showPopup(View view) {
//
//        setAnchorView(view);
//        show();
//
//    }


//    @Nullable
//    @Override
//    public Object getSelectedItem() {
//        return super.getSelectedItem();
//    }


    public T getSelectedItem() {

        return selectedPosition == -1 ? null : getItem(selectedPosition);
    }

    public T getItem(int position) {

        return adapter.getItem(position);
    }


}
