package test.gyatsina.axelspringer.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import test.gyatsina.axelspringer.R;
import test.gyatsina.axelspringer.models.ShutterImage;

/**
 * Created by gyatsina
 */

/* This class is adapter for flower image items representation
Adapter is binding views to layout
 */
public class ImageItemsAdapter extends BindableArrayAdapter<ShutterImage> {
    Context context;

    public ImageItemsAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void bindView(ShutterImage itemData, int position, View view) {
        View mainLayout = ViewHolder.get(view, R.id.main_layout_litem);
        mainLayout.setClickable(false);
        bindContentThumbnail(view, itemData);
        bindTitleText(view, itemData);
        bindDescriptionText(view, itemData);
    }

    private void bindContentThumbnail(View view, ShutterImage itemData) {
        ImageView imageView = ViewHolder.get(view, R.id.item_thumbnail);
        String thumbnailUriString = itemData.getAssets().getLargeThumb().getUrl();
        if (thumbnailUriString == null) {
            showDummyImage(imageView);
        } else {
            showImage(imageView, thumbnailUriString);
        }
    }

    private void showImage(ImageView imageView, String thumbnailUriString) {
        Uri thumbnailUri = Uri.parse(thumbnailUriString);
        Picasso picasso = Picasso.with(context);
        picasso.load(thumbnailUri)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.loading_300)
                .into(imageView);
    }

    private void showDummyImage(ImageView imageView) {
        Picasso picasso = Picasso.with(context);
        picasso.load(R.drawable.loading_300)
                .fit()
                .centerCrop()
                .into(imageView);
    }

    private void bindTitleText(View view, ShutterImage itemData) {
        TextView titleView = ViewHolder.get(view, R.id.item_title);
        titleView.setText(itemData.getDescription());
    }

    private void bindDescriptionText(View view, ShutterImage itemData) {
        TextView descriptionTextView = ViewHolder.get(view, R.id.item_url);
        String text = context.getResources().getString(R.string.image_id) + String.valueOf(itemData.getId());
        descriptionTextView.setText(text);
    }


    @Override
    public View newView(LayoutInflater inflater, int position, ViewGroup container) {
        return inflater.inflate(R.layout.list_item, container, false);
    }
}
