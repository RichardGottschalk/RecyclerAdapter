package eu.samdroid.recycleradapter.library.binding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Richard Gottschalk.
 */
public class SimpleDataBindingViewHandler<DATA>
        implements RecyclerAdapterViewHandler<SimpleDataBindingViewHandler.DataBindingViewHolder, DATA> {

    private final Map<Integer, BindingInformation> bindingInformationMap = new HashMap<>();

    public SimpleDataBindingViewHandler(BindingInformation... bindingInformations) {
        mapBindingInformations(bindingInformations);
    }

    private void mapBindingInformations(BindingInformation[] bindingInformations) {
        for (BindingInformation bindingInformation : bindingInformations) {
            bindingInformationMap.put(bindingInformation.viewType, bindingInformation);
        }
    }

    @Override
    public DataBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BindingInformation bindingInformation = bindingInformationMap.get(viewType);
        if (bindingInformation == null) {
            throw new RuntimeException("Missing BindingInformations for viewType " + viewType);
        }

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, bindingInformation.layoutId, parent, false);

        if (binding == null) {
            throw new RuntimeException("layout for viewType " + viewType + " is probably not prepared for dataBinding");
        }

        return new DataBindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleDataBindingViewHandler.DataBindingViewHolder holder, @Nullable DATA data, int viewType) {
        BindingInformation bindingInformation = bindingInformationMap.get(viewType);

        holder.binding.setVariable(bindingInformation.bindingId, data);
        holder.binding.executePendingBindings();
    }

    public class DataBindingViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        public DataBindingViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
