package mikheev.konstantin.huntmap.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import mikheev.konstantin.huntmap.R;
import mikheev.konstantin.huntmap.models.RegionItem;

public class MyRegionsAdapter extends RecyclerView.Adapter<MyRegionsAdapter.MyRegionViewHolder>{

    public static class MyRegionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private static final String TAG = MyRegionsAdapter.MyRegionViewHolder.class.getSimpleName();
        CardView regionCardView;
        TextView regionName;
        TextView regionPrice;
        ImageView regionSelectButton;
        LinearLayout selectedIconLayout;
        LinearLayout rootLayout;
        private MyRegionsAdapter.MyRegionViewHolder.ClickListener listener;

        MyRegionViewHolder(View itemView, MyRegionsAdapter.MyRegionViewHolder.ClickListener listener) {
            super(itemView);

            regionCardView = (CardView) itemView.findViewById(R.id.regionCardView);
            regionName = (TextView) itemView.findViewById(R.id.region_name);
            selectedIconLayout = (LinearLayout) itemView.findViewById(R.id.selected_icon_layout);
            rootLayout = (LinearLayout) itemView.findViewById(R.id.root_layout);

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
    public void onBindViewHolder(MyRegionsAdapter.MyRegionViewHolder MyRegionViewHolder, int position) {
        MyRegionViewHolder.regionName.setText(regionItems.get(position).getRegionName());

        if (regionItems.get(position).getIsBought()) {

        } else {

        }
    }

    @Override
    public int getItemCount() {
        return regionItems != null ? regionItems.size() : 0;
    }

}