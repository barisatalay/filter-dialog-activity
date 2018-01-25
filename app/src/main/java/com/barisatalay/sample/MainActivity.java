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
                List<mdlPerson> personList = new ArrayList<>();

                personList.add(new mdlPerson("1","Bir"));
                personList.add(new mdlPerson("2","İki"));
                personList.add(new mdlPerson("3","Üç"));
                personList.add(new mdlPerson("4","Dört"));

                final FilterDialog filterDialog = new FilterDialog(MainActivity.this);

                filterDialog.setToolbarTitle("Model Filter");
                filterDialog.setSearchBoxHint("You can search");
                filterDialog.setList(personList);

                filterDialog.show("code", "name", new DialogListener() {
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

                filterDialog.show(new DialogListener() {
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

                filterDialog.show(new DialogListener() {
                    @Override
                    public void onResult(FilterItem selectedItem) {
                        Toast.makeText(MainActivity.this, "Selected is: " + selectedItem.getName(), Toast.LENGTH_SHORT).show();
                        filterDialog.dispose();
                    }
                });
            }
        });
    }
}
