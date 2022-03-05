package Weapon;

public class Armor {
    
    private int id;
    private String name;
    //block is used for the effect of armor which causes to block the force of stroke
    private int block;
    private int price;

    public Armor(int newId, String newName, int newBlock, int newPrice){
        
        this.id = newId;
        this.name = newName;
        this.block =newBlock;
        this.price = newPrice;
    }
    
    //There are three types of armor for now
    //the armors's attributes wont change that's why
    //different types of armor is not created as class
    public static Armor[] armors (){
    
        Armor[] armors = new Armor[3];
        armors[0] = new Armor(1, "light", 1, 15);
        armors[1] = new Armor(2, "middle", 3, 25);
        armors[2] = new Armor(3, "heavy", 5, 40);
        
        return armors;
    }
    
    //this function returns the object of related armor
    public static Armor getArmorObjById(int id){
    
        for(Armor a : armors()){
            
            if(a.getId() == id){
            
                return a;
            }
        }
        return null;
    }
    
    
    //getter and setter methos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    
}
