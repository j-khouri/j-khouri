package org.cs362.escaperoom;

import java.util.LinkedList;
import java.util.Scanner;


public class EscapeRoom {
//CS362_FinalProject
/*
 * Room Order: Entrance -> Living Room -> Family Room -> Kitchen -> Basement -> Garage -> Library -> Master Bedroom -> Broom Closet -> Attic -> Entrance (when finished) -> Storm Door
 * Puzzle Order in Rooms: Entrance -> Library -> Master Bedroom -> Kitchen -> Living Room -> Basement -> Garage -> Attic -> Family Room -> Broom Closet -> Entrance (when finished) -> Storm Door
 */
    public Room entrance;
        public Item entrance_key;
        public Puzzle puzzle5;
        public boolean puzzle10_boardRemoved = false;
        public boolean puzzle10_doorOpened = false;
    public Room library;
        public Item library_book;
        public Puzzle puzzle1;
    public Room master_bedroom;
        public Item master_bedroom_painting;
        public Item lighter;
        public Puzzle puzzle2;
    public Room kitchen;
        public Item broomcloset_keys;
        public Puzzle puzzle3;
    public Room living_room;
        public Item entrance_crowbar;
        public Puzzle puzzle4;
    public Room garage;
        public Item garage_toolbox;
        public Item flashlight;
        public Puzzle puzzle8;
    public Room attic;
        public Item attic_decorations_box;
        public Item cryptex;
            public int cryptex_code = (int)(Math.random() * 9999); // random 4 digit code
        public Item entrance_angel_keys;
        public Puzzle puzzle10;
    public Room basement;
        public Item mancave_deskDrawer;
        public Puzzle puzzle7;
        public Item garage_toolbox_lock;
    public Room broomCloset;
        public Item broomcloset_cloth;
        public Puzzle puzzle6;
    public Room familyRoom;
        public Item familyRoom_basket;
        public Puzzle puzzle9;
    
    public Room currRoom;
    public Puzzle currPuzzle;
    public Room test = null;
    public LinkedList<Room> roomList; // List of rooms in the house

    public int choice = 0;
    public Player player;
    public long startTime;
    public long endTime;
    public boolean gameWon = false;


    public Scanner scan = new Scanner(System.in);
    public String certificate = "Congrats! You have successfully completed the game and have found our certificate of completion!";

 
    public static void main(String[] args) {
        EscapeRoom escaperoom = new EscapeRoom();
        escaperoom.play();
    }

    public EscapeRoom() { // Connect rooms with Marsha, back and forth so change items and puzzles and such...
    // Everything we will need for the escaperoom to run
    // Order of Room (Entrance -> Living Room -> Family Room -> Kitchen -> Basement -> Garage -> Library -> Master Bedroom -> Broom Closet -> Attic -> Entrance)
        /* Room 1: Entrance
        Puzzle #: 10
        Items / Interactions: Collect Entrance_Key for Library */
        entrance_key = new Item("Knowledge Key", "The key to the home of knowledge.");
        puzzle10 = new Puzzle(10, "You need two things to open this door. Angels Keys and a Crowbar. You need both to escape.");
        entrance = new Room(1, entrance_key, "The Entrance", "The door to freedom is locked. There is a wooden board blocking the door. On top of that, the door is locked behind a lock in the shape of angel wings.", puzzle10);
        
        /* Room 2: Living Room
        Puzzle #: 8
        Items / Interactions: Move floorboards to collect entrance_crowbar */
        entrance_crowbar = new Item("A Cool Crowbar", "Can be used for prying and removal.");
        puzzle8 = new Puzzle(8, "A set of floorboards are uneven. Maybe you can move it.");
        living_room = new Room(2, entrance_crowbar, "The Living Room", "A set of floorboards are uneven. Maybe you can move it.", puzzle8);

        /* Room 3: Family Room
        Puzzle #: 7
        Items / Interactions: Use UV Flashlight from Garage to read code to cryptex */
        familyRoom_basket = new Item("Basket", "An empty basket, with seemingly non-random scratches displayed inside.");
        puzzle7 = new Puzzle(7, "Scratches aren't so random. Use the UV Flashlight in the room where you wouldn't expect it.");
        familyRoom = new Room(3, familyRoom_basket, "The Family Room", "A basket rests in the room, darkened. Perhaps a different kind of light will solve this puzzle.", puzzle7);

        /* Room 4: Kitchen
        Puzzle #: 3
        Items / Interactions: Unfreeze cupboards with lighter from Master Bedroom to retrieve broom closet key */
        broomcloset_keys = new Item("Broom Closet Key", "Must be dirty in here. Maybe you can use this key to clean up your melted ice mess...");
        puzzle3 = new Puzzle(3, "Where would a lighter be needed the most?");
        kitchen = new Room(4, broomcloset_keys, "The Kitchen", "The cupboard is frozen shut.", puzzle3);
        
        /* Room 5: Basement
        Puzzle #: 5
        Items / Interactions: Repair old chair with cloth from Broom Closet to retrieve garage_toolbox Key */
        mancave_deskDrawer = new Item("Desk Drawer", "A old ripped & cracked leather brown recliner chair.");
        puzzle5 = new Puzzle(5, "A broken thing lays in the house. Fix its pain for a gain.");
        basement = new Room(5, mancave_deskDrawer, "The Basement", "There is a recliner chair that has a small rip in it. Next to the rip, in marker, it reads; 'fix me and your clue will be revealed'. ", puzzle5);
        garage_toolbox_lock = new Item("Rusty Key", "A key to the home of your favorite mechanic...");
        
        /* Room 6: Garage
        Puzzle #: 6
        Items / Interactions: Unlock garage_toolbox with key from Basement to retrieve UV Flashlight */
        garage_toolbox = new Item("Toolbox", "Contains important tools.");
        puzzle6 = new Puzzle(6, "Use the key to unlock the toolbox. You'll find use of its contents."); 
        garage = new Room(6, garage_toolbox, "The Garage", "It requires the opening of the envelope inside.", puzzle6);
        flashlight = new Item("An UltraViolet Flashlight", "Can be used to reveal hidden messages in places unexpected.");
        
        /* Room 7: Library
        Puzzle #: 1
        Items / Interactions: With Entrance Key, unlock locked book */
        library_book = new Item("Book", "A book with a keyhole on the cover.");
        puzzle1 = new Puzzle(1, "Knowledge is the key to the next step.");
        library = new Room(7, library_book, "The Library", "A bookshelf rests on the wall.", puzzle1);

        /* Room 8: Master Bedroom
        Puzzle #: 2
        Items / Interactions: With information from locked book, move painting to retrieve light for Kitchen */
        master_bedroom_painting = new Item("Painting", "A beautiful painting of the Starry Night.");
        puzzle2 = new Puzzle(2, "A beautiful painting hides a secret. Where could it be?");
        master_bedroom = new Room(8, master_bedroom_painting, "The Master Bedroom", "A painting of the Starry Night hangs on the wall.", puzzle2);
        lighter = new Item("Lighter", "A small lighter with a flame design. Maybe it can be used to melt something.");

        /* Room 9: Broom Closet
        Puzzle #: 4
        Items / Interactions: Unlock door with key from Kitchen to get odd piece of cloth for basement*/
        broomcloset_cloth = new Item("A piece of a coat pocket.", "A piece of cloth. It's a bit odd. It seems... familiar.");
        puzzle4 = new Puzzle(4, "You feel a shiver down your spine. You might need some more layers.");
        broomCloset = new Room(9, broomcloset_cloth, "The Broom Closet", "A lock rests on the front of the closet.", puzzle4);
        
        /* Room 10: Attic
        Puzzle #: 9
        Items / Interactions: Unlock Cryptex to get Angel Keys from Code in the Family Room familyRoom_basket */
        attic_decorations_box = new Item("Decorations Box", "A massively taped up box marked Christmas decorations");
        puzzle9 = new Puzzle(9, "This code must be used somewhere. The answer can be found in the stars.");
        attic = new Room(10, null, "The Attic", "The handle of an old rake is leaning against the box.", puzzle9);
        cryptex = new Item("Cryptex", "A cryptex; when shaken, you hear a small metal item inside.");
        entrance_angel_keys = new Item("Angel Keys", "A set of keys in the shape of angel wings.");

        roomList = new LinkedList<>();
        roomList.add(entrance);
        roomList.add(living_room);
        roomList.add(familyRoom);
        roomList.add(kitchen);
        roomList.add(basement);
        roomList.add(garage);
        roomList.add(library);
        roomList.add(master_bedroom);
        roomList.add(broomCloset);
        roomList.add(attic);
    }

    public void play() { 
        System.out.println("Welcome to the Escape Room game! You are trapped in a house and you must find your way out. Good luck!");
        

        //Get Player info
        System.out.println("What is your name?");
        String name = scan.nextLine();
        player = new Player(name);
        
        //Start Time
        System.out.println("Let's see how long it'll take for you to escape the house! Don't be last.");
        startTime = System.currentTimeMillis();
        System.out.println("Your only hint: Grab the key in the Entrance.");
        //escaperoom logic
        currRoom = entrance;
        currPuzzle = puzzle1;
        while (!gameWon) { // whole game loop
            System.out.println("You are in '" + currRoom.getName() + "'. What would you like to do?");
            printOptions();
            choice = getUserChoice();
            switch (choice) { // paths to choose to make an action
                case 1:
                    System.out.println(currRoom.getDescription()); // prints out the description of the room, or more so the clue that was written
                    break;
                case 2:
                    printLayout();
                    currRoom = changeRoom(); // moves to a room the user moves to
                    break;
                case 3:
                    interactRoom(); // interactions available for the player in the rooms. if no interaction, says so.
                    break;
                case 4:
                    player.printItemList(); // prints out all items the player has, if none, prints out they have none
                    break;
                case 5:
                    printLayout(); // print the layout of the house from current room
                    break;
                case 8:
                    System.out.println(currPuzzle.getQuestion()); // prints out the hint of the puzzle
                    break;
                case 9:
                    System.out.println("Are you sure?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    choice = getUserChoice();
                    if (choice == 1) {
                        System.out.println("You have quit the game. You are a quitter."); // player quits
                        System.exit(0);
                    }
                    break;
                default:
                    System.out.println("Invalid command. Please try again."); // backup invalid command
                    break;
            }
                }
        System.out.println(certificate); // prints Certificate
        endTime = System.currentTimeMillis(); // gets end time
        long playerTime = (endTime - startTime) / 1000; // calculates time it took to escape to seconds instead of milliseconds
        System.out.println("Your timed escape was: " + playerTime + " seconds!"); // time of how long it took to escape
        DatabaseManager.insertPlayerData(player.getName(), playerTime); // inserts player data into the database
        DatabaseManager.printLeaderboard(); // prints out the leaderboard
        scan.close();
    }

    public static void printOptions() { // List of commands available for the player
        System.out.println("=========================");
        System.out.println("-= Available Commands =-");
        System.out.println("1. Look around the room.");
        System.out.println("2. Move to another room.");
        System.out.println("3. Interact with the room.");
        System.out.println("4. Check your inventory.");
        System.out.println("5. Check house layout.");
        System.out.println("8. Get current puzzle hint.");
        System.out.println("9. Quit the game.");
        System.out.println("=========================");
    }

    public Room changeRoom() { // Moves player to the next room
        int newRoom = 0;
        while (true) { // Makes sure the input is a valid room number
            System.out.println("What room would you like to move to?:"); 
            newRoom = getUserChoice();
            if (newRoom <= 10 && newRoom > 0) {
                break;
            } else {
                System.out.println("Invalid room number. Please try again.");
            }
        }
        switch (newRoom) {
            case 1:
                return entrance;
            case 2:
                return living_room;
            case 3:
                return familyRoom;
            case 4:
                return kitchen;
            case 5:
                return basement;
            case 6:
                return garage;
            case 7:
                return library;
            case 8:
                return master_bedroom;
            case 9:
                return broomCloset;
            case 10:
                return attic;
            default:
                return test;
        } // Moves player to the room #
    }
    
    public void interactRoom() { // Interacts with the room the player is in
        if (currRoom == entrance && (player.getStockPileList().contains(entrance_angel_keys) || player.getStockPileList().contains(entrance_crowbar)) && (!puzzle10_doorOpened || !puzzle10_boardRemoved) && currPuzzle == puzzle10) { 
            // Puzzle 10: If player has both items, they can escape
            // They can also have just one item and progress through the escaperoom at a time
            if (player.getStockPileList().contains(entrance_angel_keys) && player.getStockPileList().contains(entrance_crowbar) && !puzzle10_doorOpened && !puzzle10_boardRemoved) { 
                // If player has both items, they can do actions simultaneously
                System.out.println("1. Use the entrance crowbar on the door and the angel keys on the lock.");
                choice = getUserChoice();
                if (choice == 1) {
                        System.out.println("You have unlocked the door and you have pried off the wooden board.");
                        currPuzzle = null;
                        puzzle10_doorOpened = true;
                        puzzle10_boardRemoved = true;
                }
            }
            else if (player.getStockPileList().contains(entrance_angel_keys) && !(puzzle10_doorOpened) ) { 
                // If player has angel keys, they can unlock the door
                System.out.println("1. Use the keys on the door.");  
                choice = getUserChoice();
                if (choice == 1) {
                        System.out.println("You have unlocked the door.");
                        puzzle10_doorOpened = true;
                }
            }
            else if (player.getStockPileList().contains(entrance_crowbar) && !(puzzle10_boardRemoved)) { 
                // If player has entrance_crowbar, they can remove the board
                System.out.println("1. Use the entrance crowbar on the wooden board.");
                choice = getUserChoice();
                if (choice == 1) {
                        System.out.println("You have pried off the wooden board.");
                        puzzle10_boardRemoved = true;
                }
            } 
            else {
                System.out.println("There are no more actions in this room. Try another room!"); 
                // If player has neither item, they can't do anything
            }
        } else if (currRoom == entrance && currPuzzle == puzzle1 && !player.getStockPileList().contains(entrance_key)) {
            // If the player doesn't have the key already, he can get it
            System.out.println("1. Collect the key with a symbol of a book on the side.");
            choice = getUserChoice();
            if (choice == 1) {
                player.collectItem(entrance_key);
            }
        } else if (currRoom == library && player.getStockPileList().contains(entrance_key) && currPuzzle == puzzle1) {
            // Puzzle 1: If player has the key from the entrance, they can unlock the book to get a hint on the next puzzle (painting in Master Bedroom)
            System.out.println("1. Use the key on the book."); 
            choice = getUserChoice();
            if (choice == 1) {
                System.out.println("You have unlocked the book. It reads: 'Turn the painting in the Master Bedroom.'");
                currPuzzle = puzzle2;
            }
        } else if (currRoom == master_bedroom && currPuzzle == puzzle2) { 
            // Puzzle 2: With the advice from unlocked book, player can turn the painting to find a lighter behind it (for Kitchen frozen cupboard)
            System.out.println("1. Turn the painting");
            choice = getUserChoice();
            if (choice == 1) {
                System.out.println("You have turned the painting. You have found a lighter.");
                player.collectItem(lighter);
                currPuzzle = puzzle3;
            }
        } else if (currRoom == kitchen && player.getStockPileList().contains(lighter) && currPuzzle == puzzle3) {
            // Puzzle 3: With the lighter, player can melt the ice on the cupboard to find a key to the broom closet
            System.out.println("1. Use the lighter on the cupboard.");
            choice = getUserChoice();
            if (choice == 1) {
                System.out.println("You have unlocked the cupboard. Inside you find a set of keys.");
                player.collectItem(broomcloset_keys);
                currPuzzle = puzzle4;
            }
        } else if (currRoom == living_room && !player.getStockPileList().contains(entrance_crowbar)) {
            // Puzzle 8: Player can move the floorboard to find a crowbar
            System.out.println("1. Move the floorboard");
            choice = getUserChoice();
            if (choice == 1) {
                System.out.println("You have pried off the floorboard. Inside rests a crowbar.");
                player.collectItem(entrance_crowbar);
            }
        } else if (currRoom == garage && currPuzzle == puzzle6) {
            // Puzzle 6: Player can open the garage_toolbox with a key from the broken chair (in Basement) to find a UV flashlight
            System.out.println("1. Open the toolbox.");
            choice = getUserChoice();
            if (choice == 1) {
                System.out.println("You have opened the toolbox. Inside you find a flashlight.");
                player.collectItem(flashlight);
                currPuzzle = puzzle7;
            }
        } else if (currRoom == attic) {
            // Puzzle 9: Player can open the decorations box to find a cryptex with a 4 digit code, which houses the Angel Keys
            System.out.println("1. Open the decorations box.");
            choice = getUserChoice();
            if (choice == 1) {
                System.out.println("You have opened the decorations box. Inside you find a stationary cryptex.");
                System.out.println("The cryptex has a 4 PIN code. You must find the answer to open it. Enter the code: ");
                int code = getUserChoice();
                    if (code == cryptex_code && !player.getStockPileList().contains(entrance_angel_keys)) {
                        System.out.println("You have opened the cryptex. Inside you find a set of keys marked with a halo.");
                        player.collectItem(entrance_angel_keys);
                        currPuzzle = puzzle10;
                    }
                    else if (code == cryptex_code && player.getStockPileList().contains(entrance_angel_keys)) {
                        System.out.println("The cryptex is already open. You have already collected the keys.");
                    }
                    else {
                        System.out.println("The code is incorrect.");
                }
            }
        } else if (currRoom == basement && currPuzzle == puzzle5) {
            // Puzzle 5: Player can repair the chair with the broken cloth (from Broom Closet) to find a key to the garage garage_toolbox
            System.out.println("1. Repair the chair with the broken cloth.");
            choice = getUserChoice();
            if (choice == 1) {
                System.out.println("You have repaired the chair. Inside you find a key.");
                player.collectItem(garage_toolbox_lock);
                currPuzzle = puzzle6;
            }
        } else if (currRoom == broomCloset && currPuzzle == puzzle4) {
            // Puzzle 4: Player can unlock the closet (from Kitchen puzzle) to find a piece of the chair cloth (for Basement chair)
            System.out.println("1. Unlock the closet. Check through the coats.");
            choice = getUserChoice();
            if (choice == 1) {
                System.out.println("You have unlocked the closet. You check through the coats, and find an odd piece of cloth.");
                player.collectItem(broomcloset_cloth);
                currPuzzle = puzzle5;
            }
        } else if (currRoom == familyRoom) { 
            // Puzzle 9: Lets player interact with the familyRoom_basket, and if they have UV flashlight, they get the code to the cryptex
            System.out.println("1. Look inside the basket.");
            choice = getUserChoice();
            if (choice == 1) {
                System.out.println("You have opened the basket. You find nothing but scratches inside.");
                if (player.getStockPileList().contains(flashlight)) {
                    System.out.println("However, you shine the UV flashlight on the scratches and find a 4 PIN code. ");
                    System.out.println("The code is: " + cryptex_code);
                    currPuzzle = puzzle10;
                }
            }
        } else if (currRoom == entrance && puzzle10_boardRemoved && puzzle10_doorOpened) { 
            // LAST CHECK. If the player has the angel keys and entrance_crowbar interactions done, they can escape
            System.out.println("1. Open the door.");
            choice = getUserChoice();
            if (choice == 1) {
                System.out.println("You have opened the door. You have escaped the house!");
                gameWon = true;
                
            }
        } else {
            // If player has no items or has already completed the actions, they can't do anything
            System.out.println("There are no actions in this room. Try another room!");
        }
    }

    public void printLayout() { // Prints map layout for player to see from current room
        for (Room room : roomList) {
            if (room == currRoom) {
                System.out.println("Room " + room.getRoomNumber() + ": " + room.getName() + " (You are here)");
            } else
            System.out.println("Room " + room.getRoomNumber() + ": " + room.getName());
        }

    }

    private int getUserChoice() { // Makes sure that the input is valid
        int decision = 0;
        boolean validInput = false;
        while (!validInput) {
            String input = scan.nextLine(); 
            try {
                decision = Integer.parseInt(input.trim()); 
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return decision;
    }
}


