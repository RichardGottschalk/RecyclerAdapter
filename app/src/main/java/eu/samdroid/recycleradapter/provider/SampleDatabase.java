package eu.samdroid.recycleradapter.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Richard Gottschalk.
 */
public class SampleDatabase extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;

    private static final String SQL_CREATE_SIMPLE_DATA =
            "CREATE  TABLE \"main\".\"simple_data\" (" +
                    "\"_id\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , " +
                    "\"text\" TEXT NOT NULL " +
                    ")";

    private static final String SQL_CREATE_DATA_GROUP =
            "CREATE  TABLE \"main\".\"data_group\" (" +
                    "\"_id\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , " +
                    "\"text1\" TEXT NOT NULL , " +
                    "\"text2\" TEXT NOT NULL " +
                    ")";

    private static final String SQL_CREATE_DATA_CHILDREN =
            "CREATE  TABLE \"main\".\"data_children\" (" +
                    "\"_id\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , " +
                    "\"data_group_id\" INTEGER NOT NULL, " +
                    "\"text1\" TEXT NOT NULL , " +
                    "\"text2\" TEXT NOT NULL " +
                    ")";

    public SampleDatabase(Context context) {
        super(context, "sample.db", null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_SIMPLE_DATA);
        db.execSQL(SQL_CREATE_DATA_GROUP);
        db.execSQL(SQL_CREATE_DATA_CHILDREN);

        prefillExpandable(db);
        prefillSimpleData(db);
    }

    private void prefillExpandable(SQLiteDatabase db) {
        for (int groupId = 1; groupId <= 10; groupId++) {
            ContentValues groupContentValues = new ContentValues();
            groupContentValues.put("text1", "db group " + groupId);
            groupContentValues.put("text2", "db column group: " + groupId);

            db.insert("data_group", null, groupContentValues);

            for (int childrenId = 1; childrenId <= 10; childrenId++) {
                ContentValues childrenContentValues = new ContentValues();
                childrenContentValues.put("text1", "db group: " + groupId + " children: " + childrenId);
                childrenContentValues.put("text2", "db column for group: " + groupId + " children: " + childrenId);
                childrenContentValues.put("data_group_id", groupId);

                db.insert("data_children", null, childrenContentValues);
            }
        }
    }

    private void prefillSimpleData(SQLiteDatabase db) {
        for (int i = 0; i < 100; i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("text", "db element " + i);
            db.insert("simple_data", null, contentValues);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
