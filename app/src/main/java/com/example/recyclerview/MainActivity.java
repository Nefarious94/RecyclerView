package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    private LinkedList<String> mWordList = new LinkedList<String>();
    private FloatingActionButton fab;
    private static final String[] mWordListStringArray={"Word 0", "Word 1", "Word 2", "Word 3", "Word 4", "Word 5",
            "Word 6", "Word 7", "Word 8", "Word 9", "Word 10", "Word 11", "Word 12", "Word 13", "Word 14", "Word 15", "Word 16"};
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //db = new DatabaseHelper(this);
        fab = findViewById(R.id.fab);
        /*for(int i=0;i<20;i++){
            mWordList.add("Word " + mWordList.size());
        }*/
        //initDataIntoDB();
        //initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        for(int i=0;i<mWordListStringArray.length;i++){
            this.mWordList.add(mWordListStringArray[i]);
        }
        mAdapter = new WordListAdapter(this, mWordList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mAdapter.addItem();
                mWordList.add("+ Word " + mWordList.size());
                mAdapter.notifyItemInserted(mWordList.size() - 1);
            }
        });
    }

    private void initDataIntoDB() {
        for (int i = 0; i < 10; i++) {
            if(db.addProduct("Word " + i)){
                Toast.makeText(this,"Inserted " + i,Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Not inserted " + i,Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initData() {
        SQLiteDatabase dbase = db.getWritableDatabase();
        Cursor c = null;
        c = dbase.rawQuery("SELECT * FROM "+db.TABLE_NAME, null);
        if (c != null && c.getCount() > 0) {
            if (c.moveToFirst()) {
                while(c.moveToNext()){
                    mWordList.add(c.getString(1));
                }
            }
        } else {
            Toast.makeText(this, "No hay elementos en la bdd.", Toast.LENGTH_LONG).show();
        }
        dbase.close();
        db.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
