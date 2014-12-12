package com.baristamatic.domain
/**
 * <p> Title: InventoryEntry.java </p>
 * <p> Description:   
 *
 * </p>
 * <p> Oct 3, 2010</p>
 * @author RHOLLAND
 * DRS Technologies Inc.
 *
 *
 */
class InventoryEntry {
	String displayName;
	String name;
	def cost;
	def count;
	
	public consumeIngredient(int decrCount) {
		if (count > decrCount) {
			count -= decrCount
		} else {
			count = 0
		}
	}
}
