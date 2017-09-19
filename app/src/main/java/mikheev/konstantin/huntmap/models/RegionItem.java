package mikheev.konstantin.huntmap.models;

public class RegionItem {
    private int regionId;
    private String regionName;
    private int regionPrice;
    private boolean isActive;

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

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }
}