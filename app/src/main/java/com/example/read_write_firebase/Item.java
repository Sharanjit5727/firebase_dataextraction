package com.example.read_write_firebase;

public class Item {
    String itemid;
    String item;

 public  Item()
{

}

    public Item(String item,String itemid) {
        this.item = itemid;
        this.itemid = item;
    }

    public String getItem() {
        return item;
    }

    public String getItemid() {
        return itemid;
    }
}
