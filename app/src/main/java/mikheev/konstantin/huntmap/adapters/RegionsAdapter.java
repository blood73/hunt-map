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
import mikheev.konstantin.huntmap.utils.Utils;

public class RegionsAdapter extends SelectableAdapter<RegionsAdapter.RegionViewHolder> {

    public static class RegionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private static final String TAG = RegionViewHolder.class.getSimpleName();
        CardView regionCardView;
        TextView regionName;
        TextView regionPrice;
        ImageView regionSelectButton;
        LinearLayout selectedIconLayout;
        LinearLayout rootLayout;
        private ClickListener listener;

        RegionViewHolder(View itemView, ClickListener listener) {
            super(itemView);

            regionCardView = (CardView) itemView.findViewById(R.id.regionCardView);
            regionName = (TextView) itemView.findViewById(R.id.region_name);
            regionPrice = (TextView) itemView.findViewById(R.id.region_price);
            regionSelectButton = (ImageView) itemView.findViewById(R.id.select_image_button);
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
    private RegionViewHolder.ClickListener clickListener;

    public RegionsAdapter(List<RegionItem> regionItems, RegionViewHolder.ClickListener clickListener) {
        this.regionItems = regionItems;
        this.clickListener = clickListener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RegionViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.region_item, viewGroup, false);
        return new RegionViewHolder(v, clickListener);
    }

    @Override
    public void onBindViewHolder(RegionViewHolder regionViewHolder, int position) {
        regionViewHolder.regionName.setText(regionItems.get(position).getRegionName());
        regionViewHolder.regionPrice.setText(Integer.toString(regionItems.get(position).getRegionPrice()));

        if (regionItems.get(position).getIsBought()) {
            regionViewHolder.selectedIconLayout.setVisibility(View.GONE);
            regionViewHolder.rootLayout.setBackgroundResource(R.color.boughtRegionBackground);
        } else {
            if (isSelected(position)) {
                regionViewHolder.selectedIconLayout.setVisibility(View.VISIBLE);
                regionViewHolder.rootLayout.setBackgroundResource(R.color.selectedRegionBackground);
            } else {
                regionViewHolder.selectedIconLayout.setVisibility(View.GONE);
                regionViewHolder.rootLayout.setBackgroundResource(R.color.unselectedRegionBackground);
            }
        }
    }

    @Override
    public int getItemCount() {
        return regionItems != null ? regionItems.size() : 0;
    }

    public void setIsBoughtRegions() {
        for (Integer i : getSelectedItems()) {
            RegionItem regionItem = regionItems.get(i);
            regionItem.setIsBought(true);
            Long generatedTimestamp = Utils.getGeneratedTimestamp();
            regionItem.setTimestampEnd(generatedTimestamp);

            notifyItemChanged(i);
        }
    }

    public List<RegionItem> getRegionItems() {
        return regionItems;
    }

    public void setRegionItems(List<RegionItem> newRegionItems) {
        this.regionItems.clear();
        this.regionItems.addAll(newRegionItems);
        notifyDataSetChanged();
    }

}