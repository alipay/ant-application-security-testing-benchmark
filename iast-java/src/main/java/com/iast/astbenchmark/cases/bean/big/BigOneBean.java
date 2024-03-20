package com.iast.astbenchmark.cases.bean.big;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BigOneBean {
    private String _id;

    private int index;

    private String guid;

    private boolean isActive;

    private String balance;

    private String picture;

    private int age;

    private String eyeColor;

    private String name;

    private String gender;

    private String company;

    private String email;

    private String phone;

    private String address;

    private String about;

    private String registered;

    private double latitude;

    private double longitude;

    private List<String> tags;

    private List<Friends> friends;

    private String greeting;

    private String favoriteFruit;

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_id() {
        return this._id;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getGuid() {
        return this.guid;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBalance() {
        return this.balance;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getEyeColor() {
        return this.eyeColor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return this.company;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAbout() {
        return this.about;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getRegistered() {
        return this.registered;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public void setFriends(List<Friends> friends) {
        this.friends = friends;
    }

    public List<Friends> getFriends() {
        return this.friends;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getGreeting() {
        return this.greeting;
    }

    public void setFavoriteFruit(String favoriteFruit) {
        this.favoriteFruit = favoriteFruit;
    }

    public String getFavoriteFruit() {
        return this.favoriteFruit;
    }

    public static BigOneBean fill(JSONObject jsonobj) {
        BigOneBean entity = new BigOneBean();
        if (jsonobj.containsKey("_id")) {
            entity.set_id(jsonobj.getString("_id"));
        }
        if (jsonobj.containsKey("index")) {
            entity.setIndex(jsonobj.getInt("index"));
        }
        if (jsonobj.containsKey("guid")) {
            entity.setGuid(jsonobj.getString("guid"));
        }
        if (jsonobj.containsKey("isActive")) {
            entity.setIsActive(jsonobj.getBoolean("isActive"));
        }
        if (jsonobj.containsKey("balance")) {
            entity.setBalance(jsonobj.getString("balance"));
        }
        if (jsonobj.containsKey("picture")) {
            entity.setPicture(jsonobj.getString("picture"));
        }
        if (jsonobj.containsKey("age")) {
            entity.setAge(jsonobj.getInt("age"));
        }
        if (jsonobj.containsKey("eyeColor")) {
            entity.setEyeColor(jsonobj.getString("eyeColor"));
        }
        if (jsonobj.containsKey("name")) {
            entity.setName(jsonobj.getString("name"));
        }
        if (jsonobj.containsKey("gender")) {
            entity.setGender(jsonobj.getString("gender"));
        }
        if (jsonobj.containsKey("company")) {
            entity.setCompany(jsonobj.getString("company"));
        }
        if (jsonobj.containsKey("email")) {
            entity.setEmail(jsonobj.getString("email"));
        }
        if (jsonobj.containsKey("phone")) {
            entity.setPhone(jsonobj.getString("phone"));
        }
        if (jsonobj.containsKey("address")) {
            entity.setAddress(jsonobj.getString("address"));
        }
        if (jsonobj.containsKey("about")) {
            entity.setAbout(jsonobj.getString("about"));
        }
        if (jsonobj.containsKey("registered")) {
            entity.setRegistered(jsonobj.getString("registered"));
        }
        if (jsonobj.containsKey("latitude")) {
            entity.setLatitude(jsonobj.getDouble("latitude"));
        }
        if (jsonobj.containsKey("longitude")) {
            entity.setLongitude(jsonobj.getDouble("longitude"));
        }
        if (jsonobj.containsKey("tags")) {
            entity.setTags(jsonobj.getJSONArray("tags"));
        }
        if (jsonobj.containsKey("friends")) {
            entity.setFriends(jsonobj.getJSONArray("friends"));
        }
        if (jsonobj.containsKey("greeting")) {
            entity.setGreeting(jsonobj.getString("greeting"));
        }
        if (jsonobj.containsKey("favoriteFruit")) {
            entity.setFavoriteFruit(jsonobj.getString("favoriteFruit"));
        }
        return entity;
    }

    public static List<BigOneBean> fillList(JSONArray jsonarray) {
        if (jsonarray == null || jsonarray.size() == 0)
            return null;
        List<BigOneBean> olist = new ArrayList<BigOneBean>();
        for (int i = 0; i < jsonarray.size(); i++) {
            olist.add(fill(jsonarray.getJSONObject(i)));
        }
        return olist;
    }

    class Friends {
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;
    }
}
