package com.invages.orchidrus.retrofit.model;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**

 */

public class MultipleResource {

    @SerializedName("page")
    private Integer page;
    @SerializedName("per_page")
    private Integer perPage;
    @SerializedName("total")
    public Integer total;
    @SerializedName("total_pages")
    public Integer totalPages;
    @SerializedName("data")
    public List<Datum> data = new ArrayList<>();

    public class Datum {

        @SerializedName("id")
        private Integer id;
        @SerializedName("name")
        private String name;
        @SerializedName("year")
        Integer year;
        @SerializedName("pantone_value")
        String pantoneValue;

        @NotNull
        @Override
        public String toString() {
            return id+" "+name+" "+year+" "+pantoneValue;
        }
    }

    @NotNull
    @Override
    public String toString() {
        return page+" "+perPage+""+totalPages+" "+data.size();
    }
}
