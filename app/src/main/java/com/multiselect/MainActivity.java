package com.multiselect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.mymultiselect.MyMultiSelectDialogFragment;

import java.util.ArrayList;
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
            "Bhopal"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<City> list = new ArrayList<>();


        for (int i = 0; i < city.length; i++) {

            City team1 = new City(city[i]);
//            team1.setChecked(true);

            list.add(team1);
        }


        /**
         * @title : which is used for show the title of the dialog
         *
         * @isSearchEnabled is used for wheather searchable enable or not
         *
         * @isSelect/Deselect is used for when click the button it select all choices
         *
         * @maxselectionlimt is used for max selection of the choices
         *
         * @list finaly pass the list what you want to show in the Dialog and choose
         *
         * @resourceID which is used for row of choices the default is android.R.layout.simple_list_item_checked
         *             you can change whatever you want
         *
         */

        MyMultiSelectDialogFragment dialogTest = MyMultiSelectDialogFragment.getInstance("Select your team", false, false, 5, list, android.R.layout.simple_list_item_multiple_choice);

        dialogTest.show(getSupportFragmentManager(), "Multiselect");


        dialogTest.setMultiItemSelectedListener(new MyMultiSelectDialogFragment.MultiItemSelectedListener<City>() {
            @Override
            public void onMultiItemSelected(List<City> list, String s) {

                Log.e(TAG, "onMultiItemSelected: " + s);

//                List<City> list1 = list;

                for (int i = 0; i < list.size(); i++) {

                    Log.e(TAG, "onMultiItemSelected: " + list.get(i).toString());
                }

            }
        });

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
}
