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

                final FilterDialog filterDialog = new FilterDialog<mdlPerson>(MainActivity.this);

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


    }
}
