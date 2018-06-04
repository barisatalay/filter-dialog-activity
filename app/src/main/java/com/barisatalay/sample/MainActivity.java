package com.barisatalay.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.barisatalay.filterdialog.FilterDialog;
import com.barisatalay.filterdialog.model.DialogListener;
import com.barisatalay.filterdialog.model.FilterItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<mdlHotel> personList = new ArrayList<>();

                personList.add(new mdlHotel("1","Bir"));
                personList.add(new mdlHotel("2","İki"));
                personList.add(new mdlHotel("3","Üç"));
                personList.add(new mdlHotel("4","Dört"));

                final FilterDialog filterDialog = new FilterDialog(MainActivity.this);

                filterDialog.setToolbarTitle("Model Filter");
                filterDialog.setSearchBoxHint("You can search");
                filterDialog.setList(personList);

                filterDialog.show("Otel", "Adi", new DialogListener.Single() {
                    @Override
                    public void onResult(FilterItem selectedItem) {
                        Toast.makeText(MainActivity.this, "Selected is: " + selectedItem.getName(), Toast.LENGTH_SHORT).show();
                        filterDialog.dispose();
                    }
                });
            }
        });


        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> stringList = new ArrayList<>();
                stringList.add("Item 1");
                stringList.add("Item 2");
                stringList.add("Item 3");
                stringList.add("Item 4");
                stringList.add("Item 5");
                stringList.add("Item 6");
                stringList.add("Item 7");

                final FilterDialog filterDialog = new FilterDialog(MainActivity.this);

                filterDialog.setToolbarTitle("String Filter");
                filterDialog.setSearchBoxHint("You can search");
                filterDialog.setList(stringList);


                filterDialog.backPressedEnabled(false);
                filterDialog.setOnCloseListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        filterDialog.dispose();
                    }
                });

                filterDialog.show(new DialogListener.Single() {
                    @Override
                    public void onResult(FilterItem selectedItem) {
                        Toast.makeText(MainActivity.this, "Selected is: " + selectedItem.getName(), Toast.LENGTH_SHORT).show();
                        filterDialog.dispose();
                    }
                });
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Integer> integerList = new ArrayList<>();
                integerList.add(1);
                integerList.add(2);
                integerList.add(3);
                integerList.add(4);
                integerList.add(5);
                integerList.add(6);
                integerList.add(7);

                final FilterDialog filterDialog = new FilterDialog(MainActivity.this);

                filterDialog.setToolbarTitle("Integer Filter");
                filterDialog.setSearchBoxHint("You can search");
                filterDialog.setList(integerList);

                filterDialog.show(new DialogListener.Single() {
                    @Override
                    public void onResult(FilterItem selectedItem) {
                        Toast.makeText(MainActivity.this, "Selected is: " + selectedItem.getName(), Toast.LENGTH_SHORT).show();
                        filterDialog.dispose();
                    }
                });
            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<mdlHotel> personList = new ArrayList<>();

                personList.add(new mdlHotel("1","Bir"));
                personList.add(new mdlHotel("2","İki"));
                personList.add(new mdlHotel("3","Üç"));
                personList.add(new mdlHotel("4","Dört"));

                final FilterDialog filterDialog = new FilterDialog(MainActivity.this);

                filterDialog.setToolbarTitle("Model Filter");
                filterDialog.setSearchBoxHint("You can search");
                filterDialog.setSelectButtonText("Select");
                filterDialog.setList(personList);
                filterDialog.setSelectableCount(2);

                filterDialog.show("Otel", "Adi", new DialogListener.Multiple() {
                    @Override
                    public void onResult(List<FilterItem> selectedItems) {
                        for (int i=0;i<selectedItems.size();i++)
                            Toast.makeText(MainActivity.this, "Selected is: " + selectedItems.get(i).getName(), Toast.LENGTH_SHORT).show();
                        filterDialog.dispose();
                    }
                });
            }
        });
    }
}
