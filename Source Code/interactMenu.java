import java.util.*;
public class interactMenu{
    public static void main(String[] args){
        Scanner kb = new Scanner(System.in);
        boolean exit = false;
        int answer =1;
        ShopGraph shopGraph = new ShopGraph();
        
        while(!exit){
            try{
                dispMenu();
                answer = kb.nextInt();
                valMenuChoice(answer, kb);

                if(answer != 8){
                    executeMenu(answer, shopGraph, kb);
                }
                else{
                    exit = true;
                }

                }catch(IllegalStateException e){
                System.out.println("Error, theres no shops in the list!");
            }
        }
        System.out.println("You quite. Bye!"); 
        kb.close();
    }




    /**
     * Displays the functions of the online shop system
     */
    public static void dispMenu(){
        System.out.println("\n\n1. Add a shop" + 
        "\n2. Remove a shop" + 
        "\n3. Update shop information" + 
        "\n4. Add pathway" +
        "\n5. Remove Pathway" +
        "\n6. Search pathway" +
        "\n7. Lookup shop by category" +
        "\n8. Exit Program\n\n");
    }


    /**
     * Displays the updatable attributes of each shop
     */
    public static void dispMenuAttribute(){
        System.out.println("1. Name" + 
        "\n2. Category" + 
        "\n3. Location" +  
        "\n4. Rating");
    }


    /**
     * @param choice
     * @param kb
     * @return
     * Makes sure a number selected is between 1 and 4 only for choosing which shop attribute to update
     */
    public static int valMenuAttributeChoice(int choice, Scanner kb){
        boolean validInput = false;
        
        while (!validInput)
        {  
            if((choice < 1) || (choice > 4)) 
            {
                System.out.println("Invalid input please enter a number between 1 and 4: ");
                choice = kb.nextInt();        
            }
            else
            {
                validInput = true;
            }
        }
    return choice;
    }


    /**
     * @param choice
     * @param kb
     * @return
     * Makes sure a number is between 1 and 8 only for choosing function of online system to execute
     */
    public static int valMenuChoice(int choice, Scanner kb){
        boolean validInput = false;
        
        while (!validInput)
        {  
            if((choice < 1) || (choice > 8)) 
            {
                System.out.println("Invalid input please enter a number between 1 and 9: ");
                choice = kb.nextInt();        
            }
            else
            {
                validInput = true;
            }
        }
    return choice;
    }


    /**
     * @param option
     * @param shopGraph
     * @param kb
     * Executes one function of the online system according to option chosen
     */
    public static void executeMenu(int option, ShopGraph shopGraph, Scanner kb){
        int num;
        String name, name2;
        String category, location;
        int rating;
        switch(option){ //rRememebr to make methods to validate all input!!!!!!!!!!
            case 1: // Adding a new shop
            System.out.println("Enter the shop number");
            num = kb.nextInt();
            kb.nextLine(); // This catches the nextLine character
            System.out.println("Enter the shop name");
            name = kb.nextLine();
            System.out.println("Enter the shop category");
            category = kb.nextLine();
            System.out.println("Enter shop location in format e.g Floor 3, Aisle 1");
            location = kb.nextLine();
            System.out.println("Enter shop rating between 1 and 5");
            rating = kb.nextInt();
            rating = ShopGraph.valRating(rating, kb);
            //Error check all these values
            shopGraph.addShop(num, name, category, location, rating);
            break;
            
            case 2:
            System.out.println("Enter the shop name");
            kb.nextLine(); //This catches nextLine character
            name = kb.nextLine();
            shopGraph.removeShop(name);
            break;

            case 3:
            System.out.println("Enter the name of the shop you want to update");
            kb.nextLine(); //This catches nextLine character
            name = kb.nextLine();
            System.out.println("What attribute for shop " + name + " would you like to update?");
            dispMenuAttribute();
            num = kb.nextInt();
            num = valMenuAttributeChoice(num, kb);
            kb.nextLine(); //This catches nextLine character
            shopGraph.updateShop(name, num, kb);
            break;

            case 4: 
            System.out.println("Enter the name of the first shop");
            kb.nextLine(); //This catches nextLine character
            name = kb.nextLine();
            System.out.println("Enter the name of the second shop");
            name2 = kb.nextLine();
            shopGraph.addPathway(name, name2);
            break;

            case 5:
            System.out.println("Enter the name of the first shop");
            kb.nextLine(); //This catches nextLine character
            name = kb.nextLine();
            System.out.println("Enter the name of the second shop");
            name2 = kb.nextLine();
            shopGraph.removePathway(name, name2);
            break;

            case 6: 
            System.out.println("Enter the name of the first shop");
            kb.nextLine(); //This catches nextLine character
            name = kb.nextLine();
            System.out.println("Enter the name of the second shop");
            name2 = kb.nextLine();
            shopGraph.searchPathway(name, name2);
            break;

            case 7: 
            System.out.println("Enter a category to search for");
            kb.nextLine(); //This catches nextLine character
            category = kb.nextLine();
            shopGraph.searchByCategory(category);
            break;

        }
    }
}