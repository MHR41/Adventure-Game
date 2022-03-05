
package Weapon;

public class Weapon {
    
    private String name;
    private int id;
    private int damage;
    private int price;
    
    public Weapon(String newName, int newId, int newDamage, int newPrice){
    
        this.name = newName;
        this.id = newId;
        this.damage = newDamage;
        this.price = newPrice;
    }
    
    //There are three types of weapon for now
    //the weapon's attributes wont change,that's why
    //different types of weapon is not created as class
    public static Weapon[] weapons(){
        
        Weapon[] weapons = new Weapon[3];
        weapons[0] = new Weapon("Pistol", 1, 2, 5);
        weapons[1] = new Weapon("Sword", 2, 3, 35);
        weapons[2] = new Weapon("Rifle", 3, 7, 45);
        
        return weapons;
    }
    //this function returns the object of related weapon 
    public static Weapon getWeaponObjById(int id){
    
        for(Weapon w : weapons()){
        
            if(w.getId() == id){
            
                return w;
            }
        }
        return null;
    }
    
    //getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    
}
