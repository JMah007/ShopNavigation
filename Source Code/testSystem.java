public class testSystem {
    public static void main(String args[]){
    ShopGraph shopGraph = new ShopGraph();

    shopGraph.addShop(1, "A", "Toys", "Floor 5, Aisle 3", 5);
    shopGraph.addShop(2, "B", "Bags", "Floor 2, Aisle 1", 1);
    shopGraph.addShop(3, "C", "Clothing", "Floor 2, Aisle 1", 1);
    shopGraph.addShop(4, "D", "Food", "Floor 2, Aisle 1", 1);
    shopGraph.addShop(5, "E", "Clothing", "Floor 2, Aisle 1", 1);
    shopGraph.addShop(6, "F", "Bags", "Floor 2, Aisle 1", 1);
    shopGraph.addShop(7, "G", "Clothing", "Floor 2, Aisle 1", 1);
    shopGraph.addShop(7, "H", "Food", "Floor 2, Aisle 1", 1);

    shopGraph.addPathway("A", "B");
    shopGraph.addPathway("B", "C");
    shopGraph.addPathway("C", "D");
    shopGraph.addPathway("D", "E");
    shopGraph.addPathway("E", "F");
    shopGraph.addPathway("F", "G");
    shopGraph.addPathway("E", "A");
    shopGraph.addPathway("G", "A");
    shopGraph.addPathway("G", "B");
    shopGraph.addPathway("G", "B"); // cannot add duplicate path
    shopGraph.addPathway("T", "R"); // Shops dont exist so cannot add path

    shopGraph.removePathway("A", "B"); 
    shopGraph.removePathway("T", "L"); // Shops dont exist so cant remove path
    shopGraph.removePathway("A", "D"); // Shops exist but pathway doesnt exist so cant remove


    shopGraph.searchByCategory("Clothing");
    shopGraph.removeShop("C");
    shopGraph.searchByCategory("Clothing"); // Clothing category shouldnt display shop C anymore
    shopGraph.searchByCategory("Garden"); // No shopa under this category


    shopGraph.searchPathway("G", "D");    
    shopGraph.searchPathway("R", "S"); // Cannot find pathway between non existent shops
    }
    
}
