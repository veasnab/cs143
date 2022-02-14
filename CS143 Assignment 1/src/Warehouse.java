//**Veasna Bun
//**CS143
//**09/28/2021

import java.util.Arrays;

public class Warehouse {
	// instance variables
	private int size, limitPerItem;
	private int storageUnit[];

	// constructors
	public Warehouse(int size, int limitPerItem) {
		this.size = size;
		this.limitPerItem = limitPerItem;
		this.storageUnit = new int[size];
	}
	
	// getter methods()
	public int getSize() {
		return size;
	}

	public int getLimitPerItem() {
		return limitPerItem;
	}
	
	// additional methods()
	public int receive(int itemCode, int itemCount) {
		int itemInStock = warehouseInventory(itemCode);
		int received = 0; 
		for(int i = 0; i < storageUnit.length; i++) {
			if(itemInStock + received < getLimitPerItem() && itemCount != received && storageUnit[i] == 0) {
				storageUnit[i] = itemCode;
				received++;
			}			 
		}
		return itemCount - received;
	}
	
	public int ship(int itemCode, int itemCount) {
		int shipped = 0;
		for(int i = 0; i < storageUnit.length; i++) {
			if(shipStock(itemCode) && shipped != itemCount && storageUnit[i] == itemCode) {
				storageUnit[i] = 0; 
				shipped++;
			}
		}
		return shipped;
	}
	
	// count the number of item currently in storage
	public int warehouseInventory(int itemCode) {
		int count = 0; 
		for(int i = 0; i < storageUnit.length; i++) {
			if(storageUnit[i] == itemCode) {
				count++;
			}
		}
		return count; 
	}
	
	// return true if item is in stock
	public boolean shipStock(int itemCode) {
		for(int i = 0; i < storageUnit.length; i++) {
			if(storageUnit[i] == itemCode) {
				return true;
			}
		}
		return false;	
	}
	
	public String toString() {
		return "number of storageUnit = " + size + ", current itemInArray " + Arrays.toString(storageUnit);
	}
	
	public static void main(String[] args) {

	}
	
}
