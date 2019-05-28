# MultiSelect

Just impresed https://github.com/abumoallim/Android-Multi-Select-Dialog

How to use

Create a model class and extend ModelMultiSelect like below

    public class City extends ModelMultiSelect {
        public City(String name) {
            super(name);
        }
    }

then create list like below

    List<City> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(new City("Test " + i));
    }

then

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

    MyMultiSelectDialogFragment dialogTest = MyMultiSelectDialogFragment.getInstance("Select your team", false, false, 5, list,                android.R.layout.simple_list_item_multiple_choice);
    dialogTest.show(getSupportFragmentManager(), "Multiselect");

or

    MyMultiSelectDialogFragment dialogTest = MyMultiSelectDialogFragment.getInstance("Select your team", false, false, -1, list);
    dialogTest.show(getSupportFragmentManager(), "Multiselect");

listener 

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

        
    
