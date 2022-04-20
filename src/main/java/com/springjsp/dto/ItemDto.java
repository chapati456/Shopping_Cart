package com.springjsp.dto;


public class ItemDto {

    private Long id;
    private String itemName;
    private String imageBase64;
    private Long amount;


    public ItemDto(){}

    public ItemDto(Long id,String itemName, String imageBase64,Long amount) {
        this.itemName = itemName;
        this.imageBase64 = imageBase64;
        this.id = id;
        this.amount = amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getItemName() {
        return itemName;
    }

    public Long getId() {
        return id;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}
