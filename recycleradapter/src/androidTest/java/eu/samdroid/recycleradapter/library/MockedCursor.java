package eu.samdroid.recycleradapter.library;

import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;

/**
 * @author Richard Gottschalk
 */
public class MockedCursor implements Cursor {

    private final int count;
    private int position;

    public MockedCursor(int count) {
        this.count = count;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public boolean move(int offset) {
        position += offset;
        return !(isBeforeFirst() || isAfterLast());
    }

    @Override
    public boolean moveToPosition(int position) {
        this.position = position;
        return false;
    }

    @Override
    public boolean moveToFirst() {
        position = 0;
        return isFirst();
    }

    @Override
    public boolean moveToLast() {
        position = count;
        return isLast();
    }

    @Override
    public boolean moveToNext() {
        position++;
        return position < getCount();
    }

    @Override
    public boolean moveToPrevious() {
        position--;
        return position >= 0;
    }

    @Override
    public boolean isFirst() {
        return position == 0;
    }

    @Override
    public boolean isLast() {
        return position == (count-1);
    }

    @Override
    public boolean isBeforeFirst() {
        return position < 0;
    }

    @Override
    public boolean isAfterLast() {
        return position >= count;
    }

    @Override
    public int getColumnIndex(String columnName) {
        return 0;
    }

    @Override
    public int getColumnIndexOrThrow(String columnName) throws IllegalArgumentException {
        return 0;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return null;
    }

    @Override
    public String[] getColumnNames() {
        return new String[0];
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public byte[] getBlob(int columnIndex) {
        return new byte[0];
    }

    @Override
    public String getString(int columnIndex) {
        return "" + position;
    }

    @Override
    public void copyStringToBuffer(int columnIndex, CharArrayBuffer buffer) {

    }

    @Override
    public short getShort(int columnIndex) {
        return (short) position;
    }

    @Override
    public int getInt(int columnIndex) {
        return position;
    }

    @Override
    public long getLong(int columnIndex) {
        return position;
    }

    @Override
    public float getFloat(int columnIndex) {
        return position;
    }

    @Override
    public double getDouble(int columnIndex) {
        return position;
    }

    @Override
    public int getType(int columnIndex) {
        return 0;
    }

    @Override
    public boolean isNull(int columnIndex) {
        return false;
    }

    @Override
    public void deactivate() {

    }

    @Override
    public boolean requery() {
        return false;
    }

    @Override
    public void close() {

    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public void registerContentObserver(ContentObserver observer) {

    }

    @Override
    public void unregisterContentObserver(ContentObserver observer) {

    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void setNotificationUri(ContentResolver cr, Uri uri) {

    }

    @Override
    public Uri getNotificationUri() {
        return null;
    }

    @Override
    public boolean getWantsAllOnMoveCalls() {
        return false;
    }

    @Override
    public void setExtras(Bundle extras) {

    }

    @Override
    public Bundle getExtras() {
        return null;
    }

    @Override
    public Bundle respond(Bundle extras) {
        return null;
    }
}
