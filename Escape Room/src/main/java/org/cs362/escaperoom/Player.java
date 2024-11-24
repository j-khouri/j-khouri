package org.cs362.escaperoom;
import java.io.Serializable;
import java.util.ArrayList;


public class Player implements Serializable {
   private String name;
   private long time;
   private final ArrayList<Item> stockPileList;

   public String getName() {
       return name;
   }


   public void setName(String name) {
       this.name = name;
   }

    public long getTime() {
         return time;
    }

    public long setTime(long time) {
        this.time = time;
        return time;
    }

   public ArrayList<Item> getStockPileList() {
       return stockPileList;
    }

   public Player() {
       name = "";
       stockPileList = null;
   }


   public Player(String name) {
       this.name = name;
       this.stockPileList = new ArrayList<>(20);    
   }

   public String collectItem(Item item) {
       stockPileList.add(item);
       System.out.println(name + " has newly collected: " + item.getName());
       System.out.println(item.getName() + " description: " + item.getDescription());
       return item.getName();
   }

   public void printItemList() {
       System.out.println("Items in stockpile: ");
       if (stockPileList.isEmpty()) {
           System.out.println("No items in stockpile.");
       }
       for (Item item : stockPileList) {
           System.out.println(item.getName());
       }
   }
}