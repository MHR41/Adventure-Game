
package inventorygame;

import Location.Cave;
import Location.Forest;
import Location.Location;
import Location.Mine;
import Location.River;
import Location.SafeHouse;
import Location.Store;
import java.util.Scanner;

public class Game {
    
    private Scanner input = new Scanner(System.in);
    
    // the game starts by the start function
    public void start(){
       
        System.out.println("------------------------Welcome to Adventure Game------------------------");
        System.out.print("Please enter your name: ");
        String name = input.nextLine();
        Player player = new Player(name);
        System.out.println("Welcome <"+ player.getName()+">");
        System.out.println("Please select one ID of the following character to start the game:");
        //selecting Game Character
        player.selectChar();
        //showing the player info
        player.printPlayerInfo();
        
        //Selecting location 
        Location[] location ={new SafeHouse(player), new Store(player), new Cave(player), new River(player), new Forest(player), new Mine(player)}; 
        
        //while exit is false the game will continue
        boolean exit = false; 
        while(!exit){
            
            //before selecting any location by user the initial location is set to null
            Location selectedLocation = null;                          
            System.out.println("***************************");
            System.out.println("Locations:");
            for(Location loc:location){
            
                System.out.println("ID:"+loc.getId()+" Location: "+loc.getName());
            }
            System.out.println("0 for Exit");
            System.out.println("***************************");
            System.out.println("<If you want to try your chance for earning money or getting Armor or getting Weapon, you are recommended to go to Mine location.>");
            System.out.print("Please select your location : ");
            int selectedId = input.nextInt();
            switch(selectedId){
                
                // case 0 is uesd for exit
                case 0:
                    exit = true;
                    System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
                    System.out.println("You are leaving the game...");
                    System.out.println("Hope to see you again:)");
                    System.out.println("_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
                    break;
                    
                case 1:
                    selectedLocation = new SafeHouse(player);
                   break;
                   
                case 2:
                    selectedLocation = new Store(player);
                   break;
                   
                case 3:
                    // Checking if the player have finished his/her task in this area or not if yes, then he/she can't go there again
                    // is isFood method returns true means player have finished successfully his/her task in this location and
                    //recieved its prize which is food
                    if(player.getInventory().isFood()){
                    
                        System.out.println("You have finished your task in this location and you can't go there again!!!");
                        break;
                    }
                    selectedLocation = new Cave(player);
                    break;
                    
                case 4:
                    // Checking if the player have finished his/her task in this area or not if yer he/she can't go there again
                    // is isFirewood method returns true means player have finished successfully his/her task in this location and
                    //recieved its prize which is firewood
                    if(player.getInventory().isFirewood()){
                    
                        System.out.println("You have finished your task in this location and you can't go there again!!!");
                        break;
                    }
                    selectedLocation = new River(player);
                    break;
                    
                case 5:
                    // Checking if the player have finished his/her task in this area or not if yer he/she can't go there again
                    // is isWater method returns true means player have finished successfully his/her task in this location and
                    //recieved its prize which is water
                    if(player.getInventory().isWater()){
                    
                        System.out.println("You have finished your task in this location and you can't go there again!!!");
                        break;
                    }
                    selectedLocation = new Forest(player);
                    break;
                    
                case 6:
                    //this location's aime is just for earning money or armor or weapon 
                    //but it is also possible that player doesn't recieve anything for killing the monstors in this location
                    selectedLocation = new Mine(player);
                    break;
                    
                    // if the player does not select any of the mentioned locatoins then he/she will go to SafeHouse as default
                default:
                    selectedLocation = new SafeHouse(player);
                    break;
            }
            
            
            if(selectedLocation != null){
                
                //onLocation method in the related location returns false in two situations
                //1->when the player died
                //2->when the player have finished all his task in the related locations.
                if(!selectedLocation.onLocation()){
            
                    if(player.getHealth()== 0){
                    
                        System.out.println("----GAME OVER----");
                        break;
                    }
                    else{
                    
                        break;
                    }
                }
                
            }
        }  
    }
}