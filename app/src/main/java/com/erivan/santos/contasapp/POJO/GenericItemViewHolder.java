package com.erivan.santos.contasapp.POJO;

import android.view.View;

import com.mikepenz.fastadapter.FastAdapter;

import java.util.List;

public abstract class GenericItemViewHolder extends FastAdapter.ViewHolder<GenericItem> {
    public GenericItemViewHolder(View itemView) {
        super(itemView);

        setupViews(itemView);
    }

    @Override
    public void bindView(GenericItem item, List<Object> payloads) {
        bind(item);
    }

    @Override
    public void unbindView(GenericItem item) {
        unbind(item);
    }

    abstract protected void setupViews(View view);
    abstract protected void bind(GenericItem item);
    abstract protected void unbind(GenericItem item);
}