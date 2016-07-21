package com.cnoguerol.tinoexamen.photolist.ui.adapters;

import android.widget.ImageView;

import com.cnoguerol.tinoexamen.entities.Photo;

/**
 * Created by cnoguerol.
 */
public interface OnItemClickListener {
    void onPlaceClick(Photo photo);
    void onShareClick(Photo photo, ImageView img);
    void onDeleteClick(Photo photo);
}
