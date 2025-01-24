package org.cafe.models.Revenue;
public class revenue {
    private String name;
    private double GrossValue;
    private double NetValue;
    private String period;
    private String RevenueType;
    private String description;

    public revenue(String name,
                   double Grossvalue,
                   double Netvalue,
                   String period,
                   String RevenueType,
                   String description
    )
    {
        this.name = name;
        this.GrossValue = Grossvalue;
        this.NetValue = NetValue;
        this.period = period;
        this.RevenueType = RevenueType;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrossValue() {
        return GrossValue;
    }

    public void setGrossValue(double GrossValue) {
        this.GrossValue = GrossValue;
    }
    public double getNetValue() {
        return NetValue;
    }

    public void setNetValue(double NetValue) {
        this.NetValue = NetValue;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getRevenueType() {
        return RevenueType;
    }

    public void setRevenueType(String revenueType) {
        this.RevenueType = revenueType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
