package com.example.mymapactivty;

public class InfoWindowData {

    private String store;
    private String city;
    private String address;
    private String phone;
    private String img;

    public InfoWindowData() {
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBrand() {
        return store;
    }

    public void setStore(String brand) {
        this.store = brand;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
