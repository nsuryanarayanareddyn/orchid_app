package com.invages.orchidrus.retrofit.model;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class UserList {

    @SerializedName("page")
    private Integer page;
    @SerializedName("per_page")
    Integer perPage;
    @SerializedName("total")
    Integer total;
    @SerializedName("total_pages")
    Integer totalPages;
    @SerializedName("data")
    private List<Datum> data = new ArrayList<>();

    public class Datum {

        @SerializedName("id")
        Integer id;
        @SerializedName("first_name")
        String first_name;
        @SerializedName("last_name")
        String last_name;
        @SerializedName("avatar")
        String avatar;

        @NotNull
        @Override
        public String toString() {
            return id + " " + first_name + " " + last_name + " " + avatar;
        }
    }

    @NotNull
    @Override
    public String toString() {
        return page + " " + perPage + "" + totalPages + " " + data.size();
    }

}
