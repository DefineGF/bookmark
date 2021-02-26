package com.cjm.resourcedemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import static android.content.Intent.*;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    private static final String TAG = "MainActivity_TAG";
    private static final int PICK_CONTACT_REQUEST= 1;
    private static final int REQUEST_CONTACT_PERMISSION = 2;
    private TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDisplay = findViewById(R.id.main_btn_start);
        btnDisplay.setOnClickListener(this);
        tvDisplay = findViewById(R.id.main_tv_display);

        //申请权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    MainActivity.this,new String[]{Manifest.permission.READ_CONTACTS},
                    REQUEST_CONTACT_PERMISSION);
        }

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.main_btn_start){
            pickContact();
        }
    }

    private void pickContact(){
        Intent intent = new Intent(ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent,PICK_CONTACT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == PICK_CONTACT_REQUEST && resultCode == Activity.RESULT_OK){

            if(data != null && data.getData() != null){//Uri uri = data.getData()
                @SuppressLint("Recycle")
                Cursor cursor = getContentResolver().query(data.getData(),
                       null, null, null, null);
                ContactPerson person = getContactPerson(cursor);
                Log.d(TAG,person.toString());
                tvDisplay.setText(person.toString());
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private ContactPerson getContactPerson(Cursor cursor){
        List<String> phoneNumbs = new LinkedList<>();
        String name = null;
        if(cursor != null && cursor.moveToFirst()){
            name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String  id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

            int phoneCount =
                    cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
            if(phoneCount > 0){//1：表明至少一个电话号码；0：无号码
                @SuppressLint("Recycle")
                Cursor phone = getContentResolver()
                        .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id,
                        null,null);//根据id选择相应的电话号码
                if(phone != null && phone.moveToFirst()){
                    for(;!phone.isAfterLast();phone.moveToNext()){
                        int numbIndex = phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                        phoneNumbs.add(phone.getString(numbIndex));

                    }
                    if(!phone.isClosed()){
                        phone.close();
                    }
                }
            }
        }
        return new ContactPerson(name,phoneNumbs);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CONTACT_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "获取权限", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "获取联系人权限失败", Toast.LENGTH_SHORT).show();
            }
        }

    }

    class ContactPerson{
        String name;
        List<String> numbers;
        ContactPerson(String name,List<String> number){
            this.name = name;
            this.numbers = number;
        }

        @NonNull
        @Override
        public String toString() {
            return "name:" + name + "numbers:" + numbers.toString();
        }
    }
}
