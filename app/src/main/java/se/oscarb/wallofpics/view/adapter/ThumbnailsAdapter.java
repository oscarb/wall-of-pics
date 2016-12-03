package se.oscarb.wallofpics.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import se.oscarb.wallofpics.R;
import se.oscarb.wallofpics.databinding.ItemThumbnailBinding;
import se.oscarb.wallofpics.model.Photo;
import se.oscarb.wallofpics.viewmodel.ItemThumbnailViewModel;

public class ThumbnailsAdapter extends RecyclerView.Adapter<ThumbnailsAdapter.ViewHolder> {

    private List<Photo> photos;

    public ThumbnailsAdapter() {
        photos = Collections.emptyList();
    }

    public ThumbnailsAdapter(List<Photo> photos) {
        this.photos = photos;
    }

    public List<Photo> getPhotoList() {
        return photos;
    }

    public void setPhotoList(List<Photo> photos) {
        this.photos = photos;
        //this.photos.clear();;
        //this.photos.addAll(photos);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemThumbnailBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_thumbnail, parent, false);
        viewDataBinding.nsfw.setVisibility(View.GONE); // Avoid flickering of NSFW on first load
        return new ViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindPhoto(photos.get(position));
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemThumbnailBinding binding;

        public ViewHolder(ItemThumbnailBinding itemViewBinding) {
            super(itemViewBinding.getRoot());
            binding = itemViewBinding;
        }

        void bindPhoto(Photo photo) {
            if (binding.getThumbnailViewModel() == null) {
                binding.setThumbnailViewModel(new ItemThumbnailViewModel(itemView.getContext(), photo));
            } else {
                binding.getThumbnailViewModel().setPhoto(photo);
            }
        }
    }
}
