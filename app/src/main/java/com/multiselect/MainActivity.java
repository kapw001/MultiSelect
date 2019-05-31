package com.multiselect;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.mymultiselect.popup.MyPopupWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    String[] city = {
            "Bangalore",
            "Chennai",
            "Mumbai",
            "Pune",
            "Delhi",
            "Jabalpur",
            "Indore",
            "Ranchi",
            "Hyderabad",
            "Ahmedabad",
            "Kolkata",
            "Bhopal",
            "Delhi",
            "Jabalpur",
            "Indore",
            "Ranchi",
            "Hyderabad",
            "Ahmedabad",
            "Kolkata",
            "Bhopal"
    };
    List<City> list = new ArrayList<>();
    MyPopupWindow<City> myPopupWindow;

    CustomSpinnerInputLayout<City> spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);

        for (int i = 0; i < city.length; i++) {

            City team1 = new City(city[i]);
//            team1.setChecked(true);

            list.add(team1);
        }


        spinner.setList(list);

//        spinner.setItemSelectedListener(new CustomSpinnerInputLayout.ItemSelectedListener<City>() {
//            @Override
//            public void onItemChanged(City o) {
//
//            }
//
//        });


//        ArrayList<ModelMultiSelect> teams = new ArrayList<>();
//
//        for (int i = 0; i < city.length; i++) {
//
//            teams.add(new Team(i, city[i]));
//        }
//
//
//        MyMultiSelectDialogFragment multiSelectDialog = new MyMultiSelectDialogFragment()
//                .title("Select a team")
//                .multiSelectList(teams)
//                .setMaxSelectionLimit(3)
//                .titleSize(18)
//                .positiveText("Done")
//                .negativeText("Cancel")
//                .onSubmit(new MyMultiSelectDialogFragment.SubmitCallbackListener() {
//                    @Override
//                    public void onSelected(ArrayList<Integer> arrayList, ArrayList<String> arrayList1, String s) {
//
//                    }
//
//                    @Override
//                    public void onCancel() {
//
//                    }
//                });
//
//        multiSelectDialog.show(getSupportFragmentManager(), "Multiselect");

    }

    //
    public void onShow(View view) {


        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.AppTheme1);
        builder.setTitle("Select your reason");

        builder.setSingleChoiceItems(city, -1, null);

        builder.setCancelable(false);

        builder.setPositiveButton("ok", null);

        final AlertDialog alert = builder.create();


        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {

                alert.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        ListView lw = ((AlertDialog) alert).getListView();
//                Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());

                        if (lw.getCheckedItemPosition() != -1) {
                            dialog.dismiss();
                            Toast.makeText(MainActivity.this, "" + city[lw.getCheckedItemPosition()], Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(MainActivity.this, "Please select any one options", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


//        alert.setButton(AlertDialog.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                ListView lw = ((AlertDialog) dialog).getListView();
////                Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
//
//                if (lw.getCheckedItemPosition() != -1)
//                    Toast.makeText(MainActivity.this, "" + city[lw.getCheckedItemPosition()], Toast.LENGTH_SHORT).show();
//                else
//                    Toast.makeText(MainActivity.this, "Please select any one options", Toast.LENGTH_SHORT).show();

//            }
//        });


        alert.show();


//        myPopupWindow = new MyPopupWindow<City>(view, list);
//
//        myPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//                myPopupWindow.dismiss();
//                City city = myPopupWindow.getSelectedItem();
//
//                if (city != null) {
//                    Log.e(TAG, "onItemClick: " + city.getName());
//                }
//
//            }
//        });
//
//        myPopupWindow.show();


//
//        /**
//         * @title : which is used for show the title of the dialog
//         *
//         * @isSearchEnabled is used for wheather searchable enable or not
//         *
//         * @isSelect/Deselect is used for when click the button it select all choices
//         *
//         * @maxselectionlimt is used for max selection of the choices

//         * @list finaly pass the list what you want to show in the Dialog and choose//         *
//         *
//         * @resourceID which is used for row of choices the default is android.R.layout.simple_list_item_checked
//         *             you can change whatever you want
//         *
//         */
//
//        MyMultiSelectDialogFragment dialogTest = MyMultiSelectDialogFragment.getInstance("Select your team", false, false, 5, list, android.R.layout.simple_list_item_multiple_choice);
//
//        dialogTest.show(getSupportFragmentManager(), "Multiselect");
//
//
//        dialogTest.setMultiItemSelectedListener(new MyMultiSelectDialogFragment.MultiItemSelectedListener<City>() {
//            @Override
//            public void onMultiItemSelected(List<City> list, String s) {
//
//                Log.e(TAG, "onMultiItemSelected: " + s);
//
////                List<City> list1 = list;
//
//                for (int i = 0; i < list.size(); i++) {
//
//                    Log.e(TAG, "onMultiItemSelected: " + list.get(i).toString());
//                }
//
//            }
//        });
    }

    public void onShow1(View view) {


        City city = spinner.getItem();

        if (city != null) {

            Log.e(TAG, "onShow1: " + city.getName());
        } else {

            Log.e(TAG, "onShow1: findout ");
        }

//        AlertDialog.Builder sayWindows = new AlertDialog.Builder(
//                MainActivity.this);
//        final EditText saySomething = new EditText(MainActivity.this);
//        sayWindows.setPositiveButton("ok", null);
//        sayWindows.setNegativeButton("cancel", null);
////        sayWindows.setAdapter(city, null);
//        sayWindows.setView(saySomething);
//
//        final AlertDialog mAlertDialog = sayWindows.create();
//        mAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
//
//            @Override
//            public void onShow(DialogInterface dialog) {
//
//                Button b = mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
//                b.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View view) {
//                        // TODO Do something
//                    }
//                });
//            }
//        });
//        mAlertDialog.show();
//

//        City city = myPopupWindow.getSelectedItem();
//
//        if (city != null) {
//            Log.e(TAG, "onItemClick: " + city.getName());
//        }
    }
}
