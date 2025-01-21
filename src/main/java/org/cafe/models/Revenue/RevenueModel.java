package org.cafe.models.Revenue;
public class RevenueModel {
    private String name;
    private double value;
    private String period;
    private String revenue;
    private String description;

    public RevenueModel(String name,
                        int value,
                        String period,
                        String revenue,
                        String description
                        ){
        this.name = name;
        this.value = value;
        this.period = period;
        this.revenue = revenue;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
