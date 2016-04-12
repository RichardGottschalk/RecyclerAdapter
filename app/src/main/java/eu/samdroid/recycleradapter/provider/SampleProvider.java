package eu.samdroid.recycleradapter.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Richard Gottschalk.
 */
public class SampleProvider extends ContentProvider {

    public static final String CONTENT_AUTHORITY = "eu.samdroid.recycleradapter";

    private static final UriMatcher URI_MATCHER = buildUriMatcher();

    private static final Uri BASE_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final Uri DATA_URI = BASE_URI.buildUpon().appendPath("data").build();
    public static final Uri GROUPS_URI = BASE_URI.buildUpon().appendPath("groups").build();
    public static final Uri CHILDREN_URI = BASE_URI.buildUpon().appendPath("children").build();

    private static final int DATA = 1;
    private static final int GROUPS = 10;
    private static final int CHILDREN = 11;

    private SampleDatabase database;

    @Override
    public boolean onCreate() {
        database = new SampleDatabase(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;

        switch (URI_MATCHER.match(uri)) {
            case DATA:
                cursor = database
                        .getReadableDatabase()
                        .query("simple_data",
                                projection,
                                selection,
                                selectionArgs,
                                null,
                                null,
                                sortOrder);
                break;
            case GROUPS:
                cursor = database
                        .getReadableDatabase()
                        .query("data_group",
                                projection,
                                selection,
                                selectionArgs,
                                null,
                                null,
                                sortOrder);
                break;
            case CHILDREN:
                cursor = database
                        .getReadableDatabase()
                        .query("data_children",
                                projection,
                                selection,
                                selectionArgs,
                                null,
                                null,
                                sortOrder);
                break;
        }

        if (cursor != null) cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (URI_MATCHER.match(uri)) {
            case DATA:
                database
                        .getWritableDatabase()
                        .insert("simple_data",
                                null,
                                values);
                break;
            case GROUPS:
                database.getWritableDatabase()
                        .insert("data_group",
                                null,
                                values);
                break;
            case CHILDREN:
                database.getWritableDatabase()
                        .insert("data_children",
                                null,
                                values);
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (URI_MATCHER.match(uri)) {
            case DATA:
                database.getWritableDatabase()
                        .delete("simple_data",
                                selection,
                                selectionArgs);
                break;
            case GROUPS:
                database.getWritableDatabase()
                        .delete("data_group",
                                selection,
                                selectionArgs);
                break;
            case CHILDREN:
                database.getWritableDatabase()
                        .delete("data_children",
                                selection,
                                selectionArgs);
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        switch (URI_MATCHER.match(uri)) {
            case DATA:
                database.getWritableDatabase()
                        .update("simple_data",
                                values,
                                selection,
                                selectionArgs);
                break;
            case GROUPS:
                database.getWritableDatabase()
                        .update("data_group",
                                values,
                                selection,
                                selectionArgs);
                break;
            case CHILDREN:
                database.getWritableDatabase()
                        .update("data_children",
                                values,
                                selection,
                                selectionArgs);
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return 0;
    }

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = CONTENT_AUTHORITY;

        matcher.addURI(authority, "data", DATA);
        matcher.addURI(authority, "children", CHILDREN);
        matcher.addURI(authority, "groups", GROUPS);

        return matcher;
    }
}
