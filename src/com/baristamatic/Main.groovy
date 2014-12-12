package com.baristamatic

import com.baristamatic.services.DrinkService
import com.baristamatic.services.InventoryService

/**
 * <p> Title: Main.java </p>
 * <p> Description:   
 *
 * </p>
 * <p> Oct 3, 2010</p>
 * @author RHOLLAND
 * 
 *
 *
 */ 
public class Main{
   InventoryService inventoryService
   DrinkService drinkService
   List validEntries

   static main(args) {
      Main main = new Main()
      main.init()
      main.mainLoop()
   }

   private init() {
      inventoryService = new InventoryService()
      drinkService = new DrinkService()
      validEntries = drinkService.getDrinks().collect {it.number.toString()} + ["q", "r"]
   }

   private mainLoop() {
      def stdin = new BufferedReader(new InputStreamReader(System.in))

      while(true) {
         dumpInventory(inventoryService)
         showDrinks(drinkService,inventoryService)
         String drinkNumber = stdin.readLine()
         if (!isValidEntry(drinkNumber) || drinkNumber.trim().length() == 0) {
            println "Invalid selection: " + drinkNumber + "\n"
            continue;
         }
         if (drinkNumber?.toLowerCase()?.equals("q")) {
            System.exit(0)
         } else if (drinkNumber?.toLowerCase()?.equals("r")) {
            //Restock
            inventoryService = new InventoryService();
         } else if (!inventoryService.areIngredientsAvailable(drinkService.getDrink(drinkNumber))) {
            println "Out of stock: " + drinkService.getDrink(drinkNumber).displayName
         } else {
            println "Dispensing: " + drinkService.getDrink(drinkNumber).displayName
            makeDrink(drinkNumber,  inventoryService, drinkService)
         }
      }
   }

   private isValidEntry(String entry) {
      if (entry == null || entry.length() == 0) {
         return false;
      }
      validEntries.contains(entry)
   }

   private dumpInventory(InventoryService inventoryService) {
      println "Inventory:"
      def inventoryList = inventoryService.getInventoryAvailable();
      for (i in inventoryList) {
         println i.displayName + ": " + i.count
      }
   }

   private showDrinks(DrinkService drinkService,InventoryService inventoryService) {
      println "\nMenu"
      for (d in drinkService.getDrinks()) {
         println d.number + ", " + d.displayName + ", \$" +
                  drinkService.getCost(d.name,inventoryService) +
                  ", " + inventoryService.areIngredientsAvailable(d)
      }
   }

   private makeDrink(String drinkNumber,InventoryService inventoryService,DrinkService drinkService) {
      if (drinkService.getDrink(drinkNumber) != null) {
         inventoryService.removeIngredients(drinkService.getDrink(drinkNumber))
      }
   }
}
