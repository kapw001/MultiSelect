package com.mymultiselect;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyMultiSelectDialogFragment<T extends ModelMultiSelect> extends DialogFragment implements View.OnClickListener {

    private static final String TAG = "MyMultiSelectDialogFragment";

    private static final String ARG_TITLE = "title";
    private static final String ARG_SEARCH_ENABLED = "search_enabled";
    private static final String ARG_SELEXTALL_ENABLED = "select_all_enabled";
    private static final String ARG_MAXSELECT_LIMIT = "max_select_limit";
    private static final String ARG_LIST = "my_choice_list";
    private static final String ARG_RESOURCE = "my_resource";


    private ListView listview;
    private MyArrayAdapter<T> arrayAdapter;
    private Button select_deselect, done, cancel;
    private TextView title;
    private SearchView searchView;
    private MultiItemSelectedListener multiItemSelectedListener;
    private List<T> selectedList = new ArrayList<>();

    private View select_deselect_line, searchView_line;

    public MyMultiSelectDialogFragment() {
        // Required empty public constructor
    }


    public static <T> MyMultiSelectDialogFragment getInstance(String title, boolean isSearchEnabled, boolean isSelectAllEnabled, int maxSelectionLimit, List<T> list) {

        MyMultiSelectDialogFragment dialogTest = new MyMultiSelectDialogFragment();

        Bundle bundle = new Bundle();

        bundle.putSerializable(ARG_LIST, (Serializable) list);

        bundle.putString(ARG_TITLE, title);
        bundle.putBoolean(ARG_SEARCH_ENABLED, isSearchEnabled);
        bundle.putBoolean(ARG_SELEXTALL_ENABLED, isSelectAllEnabled);
        bundle.putInt(ARG_MAXSELECT_LIMIT, maxSelectionLimit);

        dialogTest.setArguments(bundle);

        return dialogTest;


    }

    public static <T> MyMultiSelectDialogFragment getInstance(String title, boolean isSearchEnabled, boolean isSelectAllEnabled, int maxSelectionLimit, List<T> list, int resourceID) {

        MyMultiSelectDialogFragment dialogTest = new MyMultiSelectDialogFragment();

        Bundle bundle = new Bundle();

        bundle.putSerializable(ARG_LIST, (Serializable) list);

        bundle.putString(ARG_TITLE, title);
        bundle.putBoolean(ARG_SEARCH_ENABLED, isSearchEnabled);
        bundle.putBoolean(ARG_SELEXTALL_ENABLED, isSelectAllEnabled);
        bundle.putInt(ARG_MAXSELECT_LIMIT, maxSelectionLimit);
        bundle.putInt(ARG_RESOURCE, resourceID);

        dialogTest.setArguments(bundle);

        return dialogTest;


    }


    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {


//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        LayoutInflater inflater = getActivity().getLayoutInflater();
//        View view = inflater.inflate(R.layout.fragment_add_event, null);
//        builder.setView(view);
//        Dialog dialog = builder.create();
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//
//        return dialog;

//        // the content
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


        // creating the fullscreen dialog
        final Dialog dialog = new Dialog(getActivity());

//        dialog.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        dialog.setContentView(root);
        dialog.setCancelable(false);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        return dialog;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_multi_select_dialog, container, false);
        listview = view.findViewById(R.id.list);
        searchView = view.findViewById(R.id.searchView);
        select_deselect = view.findViewById(R.id.select_deselect);
        done = view.findViewById(R.id.done);
        cancel = view.findViewById(R.id.cancel);
        title = view.findViewById(R.id.title);
        select_deselect_line = view.findViewById(R.id.select_deselect_line);
        searchView_line = view.findViewById(R.id.searchView_line);

        select_deselect.setOnClickListener(this);
        done.setOnClickListener(this);
        cancel.setOnClickListener(this);

        int resourceId = android.R.layout.simple_list_item_checked;
        int maxSelectionLimit = -1;

        List<T> list = new ArrayList<>();

        Bundle bundle = getArguments();

        if (bundle != null) {


            select_deselect.setVisibility(bundle.getBoolean(ARG_SELEXTALL_ENABLED, true) ? View.VISIBLE : View.GONE);
            select_deselect_line.setVisibility(bundle.getBoolean(ARG_SELEXTALL_ENABLED, true) ? View.VISIBLE : View.GONE);
            searchView.setVisibility(bundle.getBoolean(ARG_SEARCH_ENABLED, true) ? View.VISIBLE : View.GONE);
            searchView_line.setVisibility(bundle.getBoolean(ARG_SEARCH_ENABLED, true) ? View.VISIBLE : View.GONE);

            String titile_txt = bundle.getString(ARG_TITLE);

            if (!TextUtils.isEmpty(titile_txt)) {

                title.setText(titile_txt);
                title.setVisibility(View.VISIBLE);
            } else {
                title.setVisibility(View.GONE);
            }

            maxSelectionLimit = bundle.getInt(ARG_MAXSELECT_LIMIT, -1);

            list = (List<T>) bundle.getSerializable(ARG_LIST);

            if (list == null) list = new ArrayList<>();

            resourceId = bundle.getInt(ARG_RESOURCE, android.R.layout.simple_list_item_checked);
        }


        //	listview.setChoiceMode(listview.CHOICE_MODE_NONE);
//        	listview.setChoiceMode(listview.CHOICE_MODE_SINGLE);
        listview.setChoiceMode(listview.CHOICE_MODE_MULTIPLE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                arrayAdapter.getFilter().filter(newText);

                return false;
            }
        });

        //--	text filtering
        listview.setTextFilterEnabled(true);

        arrayAdapter = new MyArrayAdapter<T>(getContext(),
                resourceId, list);


        listview.setAdapter(arrayAdapter);


        if (maxSelectionLimit > 0) {
            arrayAdapter.setMaxSelection(maxSelectionLimit);
            arrayAdapter.selectDeselecAll();
        }


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arrayAdapter.toggleChecked(position);
            }
        });

        return view;


    }

    @Override
    public void onClick(View v) {


        int i = v.getId();
        if (i == R.id.select_deselect) {
            arrayAdapter.selectDeselecAll();

            if (arrayAdapter.isSelectAll()) select_deselect.setText("Deselect All");
            else select_deselect.setText("Select All");


        } else if (i == R.id.done) {
            dismiss();

//                searchView.setQuery("", false);

            List<T> list = arrayAdapter.getCheckedItems();

            setSelectedList(list);

            if (multiItemSelectedListener != null)
                multiItemSelectedListener.onMultiItemSelected(selectedList, getSelectedNames());


        } else if (i == R.id.cancel) {
            dismiss();


        }


    }

    public void setMultiItemSelectedListener(MultiItemSelectedListener multiItemSelectedListener) {
        this.multiItemSelectedListener = multiItemSelectedListener;
    }


    private String getSelectedNames() {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < selectedList.size(); i++) {

            builder.append(selectedList.get(i));

            if (!(i == selectedList.size() - 1)) {

                builder.append(", ");
            }
        }

        return builder.toString();
    }


    public void setSelectedList(List<T> selectedList) {

        this.selectedList = selectedList;
    }

    public List<T> getSelectedList() {
        return selectedList;
    }

    //    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//
//        CheckedTextView item = (CheckedTextView) v;
//        Toast.makeText(v.getContext(), city[position] + " checked : " +
//                item.isChecked(), Toast.LENGTH_SHORT).show();
//    }

    public interface MultiItemSelectedListener<T> {

        void onMultiItemSelected(List<T> list, String selectedNames);

    }
}
