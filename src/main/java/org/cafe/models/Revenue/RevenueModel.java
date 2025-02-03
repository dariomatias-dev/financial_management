package org.cafe.models.Revenue;

public class RevenueModel {
    private String name;
    private double grossValue;
    private double netValue;
    private String period;
    private String revenueType;
    private String description;

    public RevenueModel(
       String name,
       double grossValue,
       double netValue,
       String period,
       String description,
       String revenueType
    )
    {
        this.name = name;
        this.grossValue = grossValue;
        this.netValue = netValue;
        this.period = period;
        this.description = description;
        this.revenueType = revenueType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrossValue() {
        return grossValue;
    }

    public void setGrossValue(double grossValue) {
        this.grossValue = grossValue;
    }
    public double getNetValue() {
        return netValue;
    }

    public void setNetValue(double netValue) {
        this.netValue = netValue;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getRevenueType() {
        return revenueType;
    }

    public void setRevenueType(String revenueType) {
        this.revenueType = revenueType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
