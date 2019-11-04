package com.erivan.santos.contasapp.POJO;

import android.view.View;

import androidx.annotation.NonNull;

import com.mikepenz.fastadapter.items.AbstractItem;

public abstract class GenericItem extends AbstractItem<GenericItemViewHolder> {

    @NonNull
    @Override
    public GenericItemViewHolder getViewHolder(View v) {
        return getCustomViewHolder(v);
    }

    @NonNull
    abstract protected GenericItemViewHolder getCustomViewHolder(View v);
}
