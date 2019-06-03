package com.mymultiselect.multispinner;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mymultiselect.R;
import com.mymultiselect.multselect.ModelMultiSelect;
import com.mymultiselect.multselect.MyArrayAdapter;

import java.util.List;

public class MultiSpinner extends TextInputLayout implements SearchView.OnQueryTextListener, View.OnClickListener, AdapterView.OnItemClickListener {


    private String title = "Select item";

    private ListView listView;
    private MyArrayAdapter mAdapter;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;

    private Button select_deselect;
    private Button done, cancel;
    private SearchView searchView;
    private View searchView_line, select_deselect_line;
    private TextView custom_title;

    public MultiSpinner(Context context) {
        super(context);
    }

    public MultiSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        setFocusable(false);

        if (getEditText() != null) {
            getEditText().setFocusable(false);
            getEditText().setOnClickListener(this);
        }

        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_multi_select_dialog, viewGroup, false);


        //Now we need an AlertDialog.Builder object
        builder = new AlertDialog.Builder(getContext());

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        initAlertView(dialogView);

        alertDialog = builder.create();


//        Drawable drawable = getResources().getDrawable(R.drawable.yourdrawable);
//
//// set opacity
//        drawable.setAlpha(10);
//
////Set it
//        button.setCompoundDrawablesWithIntrinsicBounds(drawable, 0, 0, 0);

        EditText editText = getEditText();
        // Left, top, right, bottom drawables.
        Drawable[] compoundDrawables = editText.getCompoundDrawables();

        for (int i = 0; i < compoundDrawables.length; i++) {
            Drawable topCompoundDrawable = compoundDrawables[i];
            if (topCompoundDrawable != null)
                topCompoundDrawable.setAlpha(150);
        }

// This is the top drawable.


    }

    public void setTitle(String title) {
        this.title = title;

        if (alertDialog != null) alertDialog.setTitle(title);

        if (custom_title != null) custom_title.setVisibility(GONE);
    }

    public void setCustomTitle(String title) {

        this.title = title;

//        if (alertDialog != null) alertDialog.setTitle(title);

        if (custom_title != null) {
            custom_title.setText(title);
            custom_title.setVisibility(VISIBLE);
        }
    }

    public void enableSelectAllButton(boolean isEnabled) {

        if (select_deselect != null) select_deselect.setVisibility(isEnabled ? VISIBLE : GONE);
        if (select_deselect_line != null)
            select_deselect_line.setVisibility(isEnabled ? VISIBLE : GONE);

    }

    public void enableSearchView(boolean isEnabled) {

        if (searchView != null) searchView.setVisibility(isEnabled ? VISIBLE : GONE);
        if (searchView_line != null) searchView_line.setVisibility(isEnabled ? VISIBLE : GONE);

    }

    public void enableCustomTitle(boolean isEnabled) {

        if (custom_title != null) custom_title.setVisibility(isEnabled ? VISIBLE : GONE);

    }

    public void setMaxSelectionlimit(int maxLimit) {

        if (mAdapter != null) mAdapter.setMaxSelection(maxLimit);

    }

    public void setMinSelectionlimit(int minLimit) {

        if (mAdapter != null) mAdapter.setMinSelection(minLimit);

    }

    public <T extends MyArrayAdapter> void setAdapter(T mAdapter) {
        this.mAdapter = mAdapter;
        if (listView != null) listView.setAdapter(mAdapter);
    }

    public <T extends ModelMultiSelect> void setList(List<T> list) {

        this.mAdapter = new MyArrayAdapter<T>(getContext(), android.R.layout.simple_list_item_checked, list);

        if (listView != null) listView.setAdapter(mAdapter);
    }

    private void initAlertView(View dialogView) {

        searchView = dialogView.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);
        listView = dialogView.findViewById(R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listView.setOnItemClickListener(this);
        custom_title = dialogView.findViewById(R.id.title);

        custom_title.setText(title);

        select_deselect = dialogView.findViewById(R.id.select_deselect);
        select_deselect_line = dialogView.findViewById(R.id.select_deselect_line);
        searchView_line = dialogView.findViewById(R.id.searchView_line);

        done = dialogView.findViewById(R.id.done);
        cancel = dialogView.findViewById(R.id.cancel);

        select_deselect.setOnClickListener(this);
        done.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        if (mAdapter != null) mAdapter.getFilter().filter(newText);

        return false;
    }

    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.select_deselect) {

            mAdapter.selectDeselecAll();

            if (mAdapter.isSelectAll()) ((Button) v).setText("Deselect All");
            else ((Button) v).setText("Select All");


        } else if (v.getId() == R.id.done) {

            if (isMinSelection()) {

                if (alertDialog != null && alertDialog.isShowing()) alertDialog.dismiss();

                String names = getSelectedNames();

                if (getEditText() != null) getEditText().setText(names);
            } else {
                showToast("Please select atleast " + mAdapter.getMinSelection() + " items");
            }

        } else if (v.getId() == R.id.cancel) {
            if (alertDialog != null && alertDialog.isShowing()) alertDialog.dismiss();
        } else {

            if (alertDialog != null && !alertDialog.isShowing()) alertDialog.show();
            else showToast("Adapter is not set");
        }

    }

    private void showToast(String s) {

        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mAdapter.toggleChecked(position);
    }

    public <T> List<T> getSelectedItem() {

        return mAdapter.getCheckedItems();
    }

    private String getSelectedNames() {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < getSelectedItem().size(); i++) {

            builder.append(getSelectedItem().get(i));

            if (!(i == getSelectedItem().size() - 1)) {

                builder.append(", ");
            }
        }

        return builder.toString();
    }

    private boolean isMinSelection() {

        return getSelectedItem().size() >= mAdapter.getMinSelection();

    }
}
