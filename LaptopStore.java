package FirstLessons.HOMEWORK.FINALWORK;

import java.util.*;

class Laptop {
	private String model;
	private int ramSize;
	private int storageSize;
	private String operatingSystem;
	private String color;

	public Laptop(String model, int ramSize, int storageSize, String operatingSystem, String color) {
		this.model = model;
		this.ramSize = ramSize;
		this.storageSize = storageSize;
		this.operatingSystem = operatingSystem;
		this.color = color;
	}

	public String getModel() {
		return model;
	}

	public int getRamSize() {
		return ramSize;
	}

	public int getStorageSize() {
		return storageSize;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public String getColor() {
		return color;
	}

	@Override
	public String toString() {
		return "Laptop{" +
				"model='" + model + '\'' +
				", ramSize=" + ramSize +
				", storageSize=" + storageSize +
				", operatingSystem='" + operatingSystem + '\'' +
				", color='" + color + '\'' +
				'}';
	}
}

public class LaptopStore {
	private Set<Laptop> laptops = new HashSet<>();

	public void addLaptop(Laptop laptop) {
		laptops.add(laptop);
	}

	public List<Laptop> filterLaptops(Map<String, Object> criteria) {
		List<Laptop> result = new ArrayList<>();

		for (Laptop laptop : laptops) {
			boolean matches = true;

			for (Map.Entry<String, Object> entry : criteria.entrySet()) {
				String criterion = entry.getKey();
				Object value = entry.getValue();

				switch (criterion) {
					case "model":
						matches &= laptop.getModel().equals(value);
						break;
					case "ramSize":
						matches &= laptop.getRamSize() >= (int) value;
						break;
					case "storageSize":
						matches &= laptop.getStorageSize() >= (int) value;
						break;
					case "operatingSystem":
						matches &= laptop.getOperatingSystem().equals(value);
						break;
					case "color":
						matches &= laptop.getColor().equals(value);
						break;
				}
			}

			if (matches) {
				result.add(laptop);
			}
		}

		return result;
	}

	public static void main(String[] args) {
		LaptopStore store = new LaptopStore();

		store.addLaptop(new Laptop("Dell XPS", 16, 512, "Windows", "silver"));
		store.addLaptop(new Laptop("MacBook Air", 8, 256, "macOS", "gold"));
		store.addLaptop(new Laptop("Lenovo ThinkPad", 32, 1, "Windows", "black"));

		Scanner scanner = new Scanner(System.in);
		Map<String, Object> criteria = new HashMap<>();

		System.out.println("Enter filtering criteria (and their values):");
		System.out.print("Model: ");
		String model = scanner.nextLine();
		criteria.put("model", model);

		System.out.print("Minimum RAM size: ");
		int minRamSize = scanner.nextInt();
		criteria.put("ramSize", minRamSize);

		List<Laptop> result = store.filterLaptops(criteria);

		System.out.println("Filtering result:");
		for (Laptop laptop : result) {
			System.out.println(laptop);
		}
	}
}
