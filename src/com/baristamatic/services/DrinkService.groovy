package com.baristamatic.services

import com.baristamatic.dao.IDrinkDao;
import com.baristamatic.dao.IInventoryDao;
import com.baristamatic.dao.MemoryDrinkDao;
import com.baristamatic.domain.Drink;

/**
 * <p> Title: DrinkService.java </p>
 * <p> Description:   
 *
 * </p>
 * <p> Oct 3, 2010</p>
 * @author RHOLLAND
 * DRS Technologies Inc.
 *
 *
 */
class DrinkService {
	def IDrinkDao drinkDao = new MemoryDrinkDao();
	
	def getCost(String name, InventoryService inventoryService) {
		drinkDao.getCost(name, inventoryService.inventoryDao)
	}
	
	def getDrink(String drinkNumber) {
		drinkDao.getDrink(drinkNumber)
	}
	
	def getDrinks() {
		return drinkDao.getDrinks();
	}
}
