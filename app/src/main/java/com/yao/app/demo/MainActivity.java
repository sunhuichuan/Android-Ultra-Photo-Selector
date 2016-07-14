package com.yao.app.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.photoselector.model.PhotoModel;
import com.photoselector.ui.PhotoSelectorActivity;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final int SELECT_IMAGE_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initView();
    }

    private void initView() {

        final Activity context = this;
        View btn_pick_photo = findViewById(R.id.btn_pick_photo);
        if (btn_pick_photo!=null){
            btn_pick_photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, PhotoSelectorActivity.class);
                    intent.putExtra(PhotoSelectorActivity.KEY_MAX, 10);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    context.startActivityForResult(intent, SELECT_IMAGE_CODE);


                }
            });
        }


    }




        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (resultCode != RESULT_OK)
                return;
            if (requestCode == SELECT_IMAGE_CODE) {// selected image
                if (data != null && data.getExtras() != null) {
                    @SuppressWarnings("unchecked")
                    List<PhotoModel> photos = (List<PhotoModel>) data.getExtras().getSerializable("photos");
//                    if (photos == null || photos.isEmpty()) {
//                        UIHelper.ToastMessage(this, R.string.no_photo_selected);
//                    } else {
//                        Intent intent = new Intent(this, YourOwnLogic.class);
//                        Bundle b = new Bundle();
//                        b.putSerializable("album_pojo", albumPojo);
//                        b.putSerializable("photos", (Serializable) photos);
//                        intent.putExtras(b);
//                        startActivity(intent);
//                        finish();
//                    }
                }
            }
        }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
