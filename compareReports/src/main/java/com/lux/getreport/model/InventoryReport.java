package com.lux.getreport.model;

public class InventoryReport {

    private String componentName;
    private String licenses;
    private String licensesLinks;
    private String packageType;
    private String componentId;
    private String packageId;
    private String version;

 
    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getLicenses() {
        return licenses;
    }

    public void setLicenses(String licenses) {
        this.licenses = licenses;
    }

    public String getLicensesLinks() {
        return licensesLinks;
    }

    public void setLicensesLinks(String licensesLinks) {
        this.licensesLinks = licensesLinks;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((componentName == null) ? 0 : componentName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        InventoryReport other = (InventoryReport) obj;
        if (componentName == null) {
            if (other.componentName != null) {
                return false;
            }
        } else if (!componentName.equals(other.componentName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "InventoryReport [componentName=" + componentName + ", licenses=" + licenses + ", licensesLinks="
                + licensesLinks + ", packageType=" + packageType + ", componentId=" + componentId + ", packageId="
                + packageId + ", version=" + version + "]";
    }


}
