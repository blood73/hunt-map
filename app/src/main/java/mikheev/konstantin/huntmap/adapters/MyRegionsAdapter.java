package mikheev.konstantin.huntmap.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
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
        private MyRegionsAdapter.MyRegionViewHolder.ClickListener listener;

        MyRegionViewHolder(View itemView, MyRegionsAdapter.MyRegionViewHolder.ClickListener listener) {
            super(itemView);

            regionCardView = (CardView) itemView.findViewById(R.id.regionCardView);
            regionName = (TextView) itemView.findViewById(R.id.region_name);
            rootLayout = (LinearLayout) itemView.findViewById(R.id.root_layout);
            prolongateText = (TextView) itemView.findViewById(R.id.prolongate);
            dateText = (TextView) itemView.findViewById(R.id.date_text);

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
    public void onBindViewHolder(MyRegionsAdapter.MyRegionViewHolder MyRegionViewHolder, final int position) {
        final RegionItem regionItem = regionItems.get(position);

        MyRegionViewHolder.regionName.setText(regionItem.getRegionName());
        MyRegionViewHolder.dateText.setText(Utils.getDateFromTimestamp(regionItem.getTimestampEnd()));

        if (regionItems.get(position).getTimestampEnd() >= Utils.getCurrentTimestamp()) {
            MyRegionViewHolder.dateText.setVisibility(View.VISIBLE);
            MyRegionViewHolder.prolongateText.setVisibility(View.GONE);
        } else {
            MyRegionViewHolder.dateText.setVisibility(View.GONE);
            MyRegionViewHolder.prolongateText.setVisibility(View.VISIBLE);
        }

        MyRegionViewHolder.prolongateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regionItem.setTimestampEnd(Utils.getNewTimestampByAddDays(Utils.getCurrentTimestamp(), PROLONGATE_DAYS));
                regionItem.setIsBought(true);
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return regionItems != null ? regionItems.size() : 0;
    }

}