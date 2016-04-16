package eu.samdroid.recycleradapter.library.binding;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import eu.samdroid.recycleradapter.library.binding.holder.ClassicViewHolder;
import eu.samdroid.recycleradapter.library.binding.holder.CursorViewTypeInformation;

/**
 * Created by Richard Gottschalk.
 */
public class CursorViewHandler implements RecyclerAdapterViewHandler<ClassicViewHolder, Cursor> {

    private Map<Integer, CursorViewTypeInformation> viewTypeInformationMap = new HashMap<>();

    public CursorViewHandler(CursorViewTypeInformation... cursorViewTypeInformation) {
        parseViewTypeInformations(cursorViewTypeInformation);
    }

    private void parseViewTypeInformations(CursorViewTypeInformation... cursorViewTypeInformations) {
        for (CursorViewTypeInformation cursorViewTypeInformation : cursorViewTypeInformations) {
            viewTypeInformationMap.put(cursorViewTypeInformation.getViewTypeId(), cursorViewTypeInformation);
        }
    }

    @Override
    public ClassicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        CursorViewTypeInformation cursorViewTypeInformation = getViewTypeInformation(viewType);

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(cursorViewTypeInformation.getLayout(), parent, false);
        return new ClassicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassicViewHolder holder, @Nullable Cursor cursor, int viewType) {
        CursorViewTypeInformation cursorViewTypeInformation = getViewTypeInformation(viewType);
        if (cursor != null) {
            holder.bindView(cursor, cursorViewTypeInformation.getFrom(cursor), cursorViewTypeInformation.getTo());
        }
    }

    private CursorViewTypeInformation getViewTypeInformation(int viewType) {
        return viewTypeInformationMap.get(viewType);
    }
}
