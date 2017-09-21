package mikheev.konstantin.huntmap.models;

public class RegionItem {
    private int regionId;
    private String regionName;
    private int regionPrice;
    private boolean isBought;
    private long timestampEnd;

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public int getRegionPrice() {
        return regionPrice;
    }

    public void setRegionPrice(int regionPrice) {
        this.regionPrice = regionPrice;
    }

    public boolean getIsBought() {
        return isBought;
    }

    public void setIsBought(boolean isBought) {
        this.isBought = isBought;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public long getTimestampEnd() {
        return timestampEnd;
    }

    public void setTimestampEnd(long timestampEnd) {
        this.timestampEnd = timestampEnd;
    }
}