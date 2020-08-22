package com.zybooks.frigo;

public class Items {
    private String itemName;
    private String quantity;
    private String dateOfPurchase;
    private String keepDays;
    private String notes;

    public Items() {
    }

    public Items(String itemName, String quantity, String dateOfPurchase, String keepDays, String notes) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.dateOfPurchase = dateOfPurchase;
        this.keepDays = keepDays;
        this.notes = notes;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public String getKeepDays() {
        return keepDays;
    }

    public void setKeepDays(String keepDays) {
        this.keepDays = keepDays;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


}
