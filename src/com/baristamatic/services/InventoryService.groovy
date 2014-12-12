package com.baristamatic.services

import java.util.Map;



import com.baristamatic.dao.IInventoryDao;
import com.baristamatic.dao.MemoryInventoryDao;
import com.baristamatic.domain.Drink;

/**
 * <p> Title: InventoryService.java </p>
 * <p> Description:   
 *
 * </p>
 * <p> Oct 3, 2010</p>
 * @author RHOLLAND
 * DRS Technologies Inc.
 *
 *
 */
class InventoryService {
	IInventoryDao inventoryDao;
	
	/**
	 * 
	 */
	public InventoryService() {
		super();
		inventoryDao = new MemoryInventoryDao();
	}
	
	List getInventoryAvailable() {
		return inventoryDao.getIngredientsAvailable()
	}
	
	def areIngredientsAvailable(Drink drink) {
		inventoryDao.areIngredientsAvailable(drink)
	}
	
	def removeIngredients(Drink drink) {
		inventoryDao.removeIngredients(drink.recipe)
	}
}
