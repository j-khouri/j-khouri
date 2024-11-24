package org.cs362.escaperoom;

public class Room {
    private final String name;
    private Item item1;
    private final String clue;
    private final Puzzle puzzle;
    private final int roomNumber;


    public Room(int roomNumber, Item item1, String name, String clue, Puzzle puzzle) {
        this.item1 = item1;
        this.clue = clue;
        this.name = name;
        this.puzzle = puzzle;
        this.roomNumber = roomNumber;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public String getName() {
        return name;
    }

    public Item getItem() {
        return item1;
    }

    public Item setItem(Item newItem) {
        item1 = newItem;
        return item1;
    }

    public String getDescription() {
        return clue;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
}
