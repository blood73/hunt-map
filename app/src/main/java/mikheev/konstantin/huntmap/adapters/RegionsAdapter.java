package mikheev.konstantin.huntmap.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.List;
import mikheev.konstantin.huntmap.R;
import mikheev.konstantin.huntmap.models.Region;

public class RegionsAdapter extends RecyclerView.Adapter<RegionsAdapter.PersonViewHolder> {

    protected static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView regionCardView;
        TextView regionName;
        TextView regionPrice;
        ImageButton regionSelectButton;

        PersonViewHolder(View itemView) {
            super(itemView);
            regionCardView = (CardView) itemView.findViewById(R.id.regionCardView);
            regionName = (TextView) itemView.findViewById(R.id.region_name);
            regionPrice = (TextView) itemView.findViewById(R.id.region_price);
            regionSelectButton = (ImageButton) itemView.findViewById(R.id.selectImageButton);
        }
    }

    private List<Region> regions;

    public RegionsAdapter(List<Region> regions) {
        this.regions = regions;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.region_item, viewGroup, false);
        return new PersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.regionName.setText(regions.get(i).getRegionName());
        personViewHolder.regionPrice.setText(Integer.toString(regions.get(i).getRegionPrice()));
    }

    @Override
    public int getItemCount() {
        return regions.size();
    }
}