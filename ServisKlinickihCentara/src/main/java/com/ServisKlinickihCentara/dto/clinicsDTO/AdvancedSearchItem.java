package com.ServisKlinickihCentara.dto.clinicsDTO;

public class AdvancedSearchItem {
    private String id;
    private String name;
    private String rating;
    private String address;
    private String price;

    public AdvancedSearchItem(){
        super();
    }

    public AdvancedSearchItem(String id, String name, String rating, String address, String price) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.address = address;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
