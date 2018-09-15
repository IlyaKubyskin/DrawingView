package com.kubik.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity {

    private DrawOnTouchView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new DrawOnTouchView(this, null);
        setContentView(view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.color_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.changeColorMenuItem:
                initDialog(view.getPaintCurrentColor());
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initDialog(int initialColor) {
        final AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, initialColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                // color is the color selected by the user.
                view.setPaintColor(color);

            }

            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                // cancel was selected by the user
            }
        });
        dialog.show();
    }
}
