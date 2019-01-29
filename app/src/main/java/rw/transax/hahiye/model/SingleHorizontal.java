package rw.transax.hahiye.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

public class SingleHorizontal {
    private int images;
    private String title;
    private String desc;

    public SingleHorizontal() {

    }

    public SingleHorizontal(int images, String title, String desc, String pubDate) {
        this.images = images;
        this.title = title;
        this.desc = desc;
        this.pubDate = pubDate;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String pubDate;

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public static DiffUtil.ItemCallback<SingleHorizontal> HORIZONTAL_ITEM_CALLBACK =
            new DiffUtil.ItemCallback<SingleHorizontal>() {
                @Override
                public boolean areItemsTheSame(@NonNull SingleHorizontal oldItem, @NonNull SingleHorizontal newItem) {
                    return oldItem.desc.equals(newItem.desc);
                }

                @Override
                public boolean areContentsTheSame(@NonNull SingleHorizontal oldItem, @NonNull SingleHorizontal newItem) {
                    return oldItem.equals(newItem);
                }
            };

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this) return true;
        SingleHorizontal horizontal = (SingleHorizontal) obj;
        if (horizontal != null) {
            return horizontal.desc.equals(this.desc);
        }
        return true;
    }
}
