package cn.dalelegamalek.demo.model;

import com.google.gson.annotations.SerializedName;

public class contact_home {
    @SerializedName("id")
    int id;
    @SerializedName("image")
    String image;
    @SerializedName("points")
    int points;
    @SerializedName("name")
    String name;
    @SerializedName("email")
    String email;
    @SerializedName("date")
    String date;
    @SerializedName("phone")
    String phone;
    @SerializedName("address")
    String address;
    @SerializedName("password")
    String password;
    @SerializedName("category")
    int category;

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
