package com.springjsp.dto;


import com.springjsp.dto.ItemDto;
import com.springjsp.model.Item;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartDto {
    private List<ItemDto> cartItems;
    private Long totalAmount = 0l;

    public CartDto(){
        cartItems = new ArrayList<>();
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setCartItems(ItemDto itemDto){
        cartItems.add(itemDto);
    }

    public void removeAllItemsInCart(){
        cartItems.clear();
    }

    public void removeItem(String item){
        ItemDto removeItem = new ItemDto();
        for(ItemDto cartItem : cartItems) {
            if (cartItem.toString().equals(item)) {
                removeItem = cartItem;
            }
        }
        this.totalAmount = this.totalAmount-removeItem.getAmount();
        cartItems.remove(cartItems.indexOf(removeItem));
    }

    public List<ItemDto> getArrayList(){
        return cartItems;
    }

    public int getLength(){
        return cartItems.size();
    }


}
