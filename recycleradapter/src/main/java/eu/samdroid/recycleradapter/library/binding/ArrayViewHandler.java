package eu.samdroid.recycleradapter.library.binding;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import eu.samdroid.recycleradapter.library.binding.holder.ArrayViewTypeInformation;
import eu.samdroid.recycleradapter.library.binding.holder.ClassicViewHolder;

/**
 * Created by Richard Gottschalk.
 */
public class ArrayViewHandler implements RecyclerAdapterViewHandler<ClassicViewHolder, Object> {

    private Map<Integer, ArrayViewTypeInformation> viewTypeInformationMap = new HashMap<>();

    public ArrayViewHandler(ArrayViewTypeInformation... arrayViewTypeInformations) {
        parseViewTypeInformations(arrayViewTypeInformations);
    }

    private void parseViewTypeInformations(ArrayViewTypeInformation... arrayViewTypeInformations) {
        for (ArrayViewTypeInformation arrayViewTypeInformation : arrayViewTypeInformations) {
            viewTypeInformationMap.put(arrayViewTypeInformation.getViewTypeId(), arrayViewTypeInformation);
        }
    }

    @Override
    public ClassicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ArrayViewTypeInformation arrayViewTypeInformation = getViewTypeInformation(viewType);
        if (arrayViewTypeInformation == null) {
            throw new IllegalStateException("No ArrayViewTypeInformation found for viewType " + viewType);
        }

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(arrayViewTypeInformation.getLayout(), parent, false);
        return new ClassicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassicViewHolder holder, @Nullable Object data, int viewType) {
        if (data == null) return;

        ArrayViewTypeInformation viewTypeInformation = getViewTypeInformation(viewType);

        View view = holder.itemView.findViewById(viewTypeInformation.getTextViewRes());
        if (view != null && view instanceof TextView) {
            TextView textView = (TextView) view;
            textView.setText(String.valueOf(data));
        }
    }

    private ArrayViewTypeInformation getViewTypeInformation(int viewType) {
        return viewTypeInformationMap.get(viewType);
    }
}
