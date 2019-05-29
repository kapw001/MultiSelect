package com.mymultiselect.multselect;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyArrayAdapter<T extends ModelMultiSelect> extends ArrayAdapter<T> {

    private static final String TAG = "MyArrayAdapter";
    private boolean isSelectAll = false;
    //    private SparseBooleanArray myChecked = new SparseBooleanArray();
    private List<T> myCheckedList = new ArrayList<>();


    private int maxSelection = -1;
    private int selectionCount = 0;
//    private final int mResource;


    public MyArrayAdapter(@NonNull Context context, int resource, @NonNull T[] objects) {
        super(context, resource, objects);
//        this.mResource = resource;
        init(Arrays.asList(objects));
    }

    public MyArrayAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull T[] objects) {
        super(context, resource, textViewResourceId, objects);
//        this.mResource = resource;
        init(Arrays.asList(objects));
    }

    public MyArrayAdapter(@NonNull Context context, int resource, @NonNull List<T> objects) {
        super(context, resource, objects);
//        this.mResource = resource;
        init(objects);
    }

    public MyArrayAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<T> objects) {
        super(context, resource, textViewResourceId, objects);
//        this.mResource = resource;
        init(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ModelMultiSelect checked = getItem(position);

        if (checked != null) {
            ((ListView) parent).setItemChecked(position, checked.isChecked());
        }

        return super.getView(position, convertView, parent);
    }


//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
////        View view = super.getView(position, convertView, parent);
//
//        View view = convertView;
//
//        if (view == null) {
//
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_multi_select, parent, false);
//        }
//
//        ModelMultiSelect item = getItem(position);
//
//        CheckedTextView checkedTextView = (CheckedTextView) view.findViewById(R.id.test3);
//
//        if (item != null) {
//            checkedTextView.setText(item.getName());
////            checkedTextView.setChecked(item.isChecked());
//            ((ListView) parent).setItemChecked(position, item.isChecked());
//        }
//
//
//        return view;
//    }

    private void init(List<T> objects) {

        maxSelection = objects.size();

        for (int i = 0; i < objects.size(); i++) {

            if (objects.get(i).isChecked()) {
                myCheckedList.add(objects.get(i));

            }
        }

//        for (int i = 0; i < objects.size(); i++) {
//            myChecked.put(i, false);
//        }

//        for (int i = 0; i < objects.size(); i++) {
//
//            myCheckedList.put(i, objects.get(i));
//        }

    }

    public void setMaxSelection(int maxSelection) {
        this.maxSelection = maxSelection;
    }

    public void selectDeselecAll() {

        myCheckedList.clear();
        selectionCount = 0;

        isSelectAll = !isSelectAll;

        for (int i = 0; i < getCount(); i++) {

            ModelMultiSelect item = getItem(i);

            if (item != null) {


                if (isSelectAll) {

                    if (selectionCount < maxSelection) {
                        selectionCount++;
                        item.setChecked(true);

                        myCheckedList.add((T) item);
                    }


                } else {

                    if (selectionCount > 0) {
                        selectionCount--;
                    }
                    item.setChecked(false);

                }


//                item.setChecked(isSelectAll);

//                if (item.isChecked()) {
//                    item.setChecked(true);
//                } else {
//                    item.setChecked(false);
//                }

            }

        }

//        for (int i = 0; i < myChecked.size(); i++) {
//
//            myChecked.put(i, isSelectAll);
//
//
//        }


        notifyDataSetChanged();
    }


    public boolean isSelectAll() {
        return isSelectAll;
    }

    public void toggleChecked(int position) {


        ModelMultiSelect item = getItem(position);

        if (item != null) {

            if (item.isChecked()) {
                if (selectionCount > 0) {
                    selectionCount--;
                }
                myCheckedList.remove(item);
                item.setChecked(false);
            } else {

                if (selectionCount < maxSelection) {
                    selectionCount++;
                    item.setChecked(true);

                    myCheckedList.add((T) item);
                } else {

                    Toast.makeText(getContext(), "Maximum item selection is " + maxSelection + " only", Toast.LENGTH_SHORT).show();
                }
            }

        }


//        if (myChecked.get(position)) {
//            myChecked.put(position, false);
//        } else {
//            myChecked.put(position, true);
//        }

        notifyDataSetChanged();

    }

//    public List<Integer> getCheckedItemPositions() {
//        List<Integer> checkedItemPositions = new ArrayList<Integer>();
//
//        for (int i = 0; i < myChecked.size(); i++) {
//            if (myChecked.get(i)) {
//                (checkedItemPositions).add(i);
//            }
//        }
//
//        return checkedItemPositions;
//    }

    public List<T> getCheckedItems() {

//        List<T> checkedItems = new ArrayList<T>();
//
//        for (int i = 0; i < getCount(); i++) {
//            if (getItem(i).isChecked()) {
//                (checkedItems).add(getItem(i));
//            }
//        }

        return myCheckedList;
    }
}
