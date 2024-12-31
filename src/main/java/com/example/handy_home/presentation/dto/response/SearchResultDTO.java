package com.example.handy_home.presentation.dto.response;

public class SearchResultDTO {

    private String complexNo;
    private String title;
    private String address;
    private String link;

    public SearchResultDTO(String complexNo, String title, String address, String link) {
        this.complexNo = complexNo;
        this.title = title;
        this.address = address;
        this.link = link;
    }

    public String getComplexNo() {
        return complexNo;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public String getLink() {
        return link;
    }
}
