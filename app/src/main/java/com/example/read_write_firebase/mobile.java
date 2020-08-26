package com.example.read_write_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class mobile extends AppCompatActivity {
    Button b1;
    EditText ed1;
    DatabaseReference databaseitem;
    ListView listviewitem;
    List<Item> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);
        databaseitem= FirebaseDatabase.getInstance().getReference("Item2");
        ed1 = (EditText) findViewById(R.id.edit1);
        listviewitem=(ListView) findViewById(R.id.listviewhome);
        itemList =new ArrayList<Item>();
        b1 = (Button) findViewById(R.id.add_button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                additem();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseitem.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                itemList.clear();
                for(DataSnapshot itemsnapshot : snapshot.getChildren())
                {
                    Item item=itemsnapshot.getValue(Item.class);
                    itemList.add(item);
                }
                ItemsList adapter=new ItemsList(mobile.this,itemList);
                listviewitem.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void additem() {
        String item = ed1.getText().toString().trim();
        if(!TextUtils.isEmpty(item))
        {
            String id= databaseitem.push().getKey();
            Item item1=new Item(id,item);
            databaseitem.child(id).setValue(item1);
            Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();


        }
        else
        {
            Toast.makeText(this, "Please Enter the Item", Toast.LENGTH_SHORT).show();
        }
    }

}

