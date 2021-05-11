package academy.learnprogramming.linkedstack;

public class SimpleHashTable {
	private StoredEmployee[] hashtable;

	public SimpleHashTable() {
		hashtable = new StoredEmployee[10];
	}

	public void put(String key, EmployeeNew employee) { // check if there is collision and in-order to avoid collision, do linear probing
		int hashedKey = hashKey(key);
		if (occupied(hashedKey)) {
			int stopIndex = hashedKey;
			if (hashedKey == hashtable.length - 1) {
				hashedKey = 0;
			} else {
				hashedKey++;
			}
			while (occupied(hashedKey) && hashedKey != stopIndex) {
				hashedKey = (hashedKey + 1) % hashtable.length;
			}
		}
		if (occupied(hashedKey)) {
			System.out.println("sorry, there is already an employee at position " + hashedKey);
			StoredEmployee[] oldHashtable = hashtable;
			hashtable = new StoredEmployee[2 * oldHashtable.length];
			for (int i = 0; i < oldHashtable.length; i++) {
				if (oldHashtable[i] != null && oldHashtable[i].key != null) {
					put(oldHashtable[i].key, oldHashtable[i].employee);
				}
			}
			put(key, employee);
		} else {
			hashtable[hashedKey] = new StoredEmployee(key, employee);
		}
	}

	public EmployeeNew get(String key) {
		int hashedKey = findKey(key);
		if (hashedKey == -1) {
			return null;
		}
		return hashtable[hashedKey].employee;
	}

	public EmployeeNew remove(String key) {
		int hashedKey = findKey(key);
		if (hashedKey == -1) {
			return null;
		}
		EmployeeNew employee = hashtable[hashedKey].employee;
		hashtable[hashedKey] = null;
		StoredEmployee[] oldHashtable = hashtable;
		hashtable = new StoredEmployee[oldHashtable.length];
		for (int i = 0; i < oldHashtable.length; i++) {
			if (oldHashtable[i] != null && oldHashtable[i].key != null) {
				put(oldHashtable[i].key, oldHashtable[i].employee);
			}
		}
		return employee;
	}

	private int hashKey(String key) {
		return key.length() % hashtable.length;
	}

	private int findKey(String key) {
		int hashedKey = hashKey(key);
		if (hashtable[hashedKey] != null && hashtable[hashedKey].key.equals(key)) {
			return hashedKey;
		}
		int stopIndex = hashedKey;
		if (hashedKey == hashtable.length - 1) {
			hashedKey = 0;
		} else {
			hashedKey++;
		}
		while (hashedKey != stopIndex && hashtable[hashedKey] != null && !(hashtable[hashedKey].key.equals(key))) {
			hashedKey = (hashedKey + 1) % hashtable.length;
		}
		if (hashtable[hashedKey] != null && hashtable[hashedKey].key.equals(key)) {
			return hashedKey;
		} else {
			return -1;
		}
	}

	private boolean occupied(int index) {
		return hashtable[index] != null;
	}

	public void printHashtable() {
		for (int i = 0; i < hashtable.length; i++) {
			if (hashtable[i] == null) {
				System.out.println("empty");
			} else {
				System.out.println("Position " + i + ": " + hashtable[i].employee);
			}
		}
	}
}
