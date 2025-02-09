package org.cafe.models.expense;

public class CreateExpenseModel {
    private String name;
    private double value;
    private String period;
    private String description;

    public CreateExpenseModel(
        String name,
        double value,
        String period,
        String description
    ) {
        this.name = name;
        this.value = value;
        this.period = period;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public String getPeriod() {
        return period;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
