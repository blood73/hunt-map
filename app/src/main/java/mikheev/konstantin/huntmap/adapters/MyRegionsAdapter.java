package mikheev.konstantin.huntmap.adapters;

import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import java.util.List;
import mikheev.konstantin.huntmap.R;
import mikheev.konstantin.huntmap.models.RegionItem;
import mikheev.konstantin.huntmap.utils.Utils;

public class MyRegionsAdapter extends RecyclerView.Adapter<MyRegionsAdapter.MyRegionViewHolder> {

    public static class MyRegionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView regionCardView;
        TextView regionName;
        TextView prolongateText;
        TextView dateText;
        LinearLayout rootLayout;
        SwipeRevealLayout swipeRevealLayout;
        ImageView deleteImageButton;

        private MyRegionsAdapter.MyRegionViewHolder.ClickListener listener;

        MyRegionViewHolder(View itemView, MyRegionsAdapter.MyRegionViewHolder.ClickListener listener) {
            super(itemView);

            regionCardView = (CardView) itemView.findViewById(R.id.regionCardView);
            regionName = (TextView) itemView.findViewById(R.id.region_name);
            rootLayout = (LinearLayout) itemView.findViewById(R.id.root_layout);
            prolongateText = (TextView) itemView.findViewById(R.id.prolongate);
            dateText = (TextView) itemView.findViewById(R.id.date_text);
            swipeRevealLayout = (SwipeRevealLayout) itemView.findViewById(R.id.swipe_layout);
            deleteImageButton = (ImageView) itemView.findViewById(R.id.delete_button);

            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClicked(getAdapterPosition());
            }
        }

        public interface ClickListener {
            public void onItemClicked(int position);
        }
    }

    private List<RegionItem> regionItems;
    private MyRegionsAdapter.MyRegionViewHolder.ClickListener clickListener;
    private static int PROLONGATE_DAYS = 30;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public MyRegionsAdapter(List<RegionItem> regionItems, MyRegionsAdapter.MyRegionViewHolder.ClickListener clickListener) {
        this.regionItems = regionItems;
        this.clickListener = clickListener;
    }

    @Override
    public MyRegionsAdapter.MyRegionViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_map_item, viewGroup, false);
        return new MyRegionsAdapter.MyRegionViewHolder(v, clickListener);
    }

    @Override
    public void onBindViewHolder(MyRegionsAdapter.MyRegionViewHolder myRegionViewHolder, final int position) {
        final RegionItem regionItem = regionItems.get(position);

        viewBinderHelper.bind(myRegionViewHolder.swipeRevealLayout, regionItem.getRegionName());

        myRegionViewHolder.regionName.setText(regionItem.getRegionName());
        myRegionViewHolder.dateText.setText(Utils.getDateFromTimestamp(regionItem.getTimestampEnd()));

        if (regionItems.get(position).getTimestampEnd() >= Utils.getCurrentTimestamp()) {
            myRegionViewHolder.dateText.setVisibility(View.VISIBLE);
            myRegionViewHolder.prolongateText.setVisibility(View.GONE);
        } else {
            myRegionViewHolder.dateText.setVisibility(View.GONE);
            myRegionViewHolder.prolongateText.setVisibility(View.VISIBLE);
        }

        myRegionViewHolder.prolongateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regionItem.setTimestampEnd(Utils.getNewTimestampByAddDays(Utils.getCurrentTimestamp(), PROLONGATE_DAYS));
                regionItem.setIsBought(true);
                notifyItemChanged(position);
            }
        });

        myRegionViewHolder.deleteImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regionItem.setIsBought(false);
                regionItems.remove(regionItem);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return regionItems != null ? regionItems.size() : 0;
    }

}