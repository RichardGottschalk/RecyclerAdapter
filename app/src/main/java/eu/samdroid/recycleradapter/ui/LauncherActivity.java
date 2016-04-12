package eu.samdroid.recycleradapter.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import eu.samdroid.recycleradapter.R;
import eu.samdroid.recycleradapter.ui.samples.ArrayActivity;
import eu.samdroid.recycleradapter.ui.samples.ArrayDataBindingActivity;
import eu.samdroid.recycleradapter.ui.samples.CursorActivity;
import eu.samdroid.recycleradapter.ui.samples.CursorDataBindingActivity;
import eu.samdroid.recycleradapter.ui.samples.ExpandableArrayActivity;
import eu.samdroid.recycleradapter.ui.samples.ExpandableArrayDataBindingActivity;
import eu.samdroid.recycleradapter.ui.samples.ExpandableCursorTreeActivity;
import eu.samdroid.recycleradapter.ui.samples.ExpandableCursorDataBindingActivity;

/**
 * Created by Richard Gottschalk.
 */
public class LauncherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.launcher_activity);
    }

    public void onClickArray(View view) {
        Intent intent = new Intent(this, ArrayActivity.class);
        startActivity(intent);
    }

    public void onClickArrayDataBinding(View view) {
        Intent intent = new Intent(this, ArrayDataBindingActivity.class);
        startActivity(intent);
    }

    public void onClickExpandableArray(View view) {
        Intent intent = new Intent(this, ExpandableArrayActivity.class);
        startActivity(intent);
    }

    public void onClickExpandableArrayDataBinding(View view) {
        Intent intent = new Intent(this, ExpandableArrayDataBindingActivity.class);
        startActivity(intent);
    }

    public void onClickCursor(View view) {
        Intent intent = new Intent(this, CursorActivity.class);
        startActivity(intent);
    }

    public void onClickCursorDataBinding(View view) {
        Intent intent = new Intent(this, CursorDataBindingActivity.class);
        startActivity(intent);
    }

    public void onClickExpandableCursor(View view) {
        Intent intent = new Intent(this, ExpandableCursorTreeActivity.class);
        startActivity(intent);
    }

    public void onClickExpandableCursorDataBinding(View view) {
        Intent intent = new Intent(this, ExpandableCursorDataBindingActivity.class);
        startActivity(intent);
    }
}
