public class Main {

	public static void main(String[] args) {
		Cat cat1 = new Cat("Vaska", 2);
		Cat cat2 = new Cat("Umka", 12);
		Cat cat3 = new Cat("Luska", 6);
		Cat cat4 = new Cat("Kuzia", 4);
		Cat cat5 = new Cat("Murka", 5);
		Cat cat6 = new Cat("Barsik", 6);
		Cat[] cats = new Cat[] { cat1, cat2, cat3, cat4, cat5, cat6 };

		Cat[] result = countingSort(cats);
		for (Cat cat : result) {
			System.out.println(cat);
		}

	}

	public static int[] findMinMaxKey(Cat[] cats) {
		int minKey = cats[0].getAge();
		int maxKey = cats[0].getAge();
		for (Cat cat : cats) {
			if (cat.getAge() < minKey) {
				minKey = cat.getAge();
			}
			if (cat.getAge() > maxKey) {
				maxKey = cat.getAge();
			}
		}
		return new int[] { minKey, maxKey };
	}

	public static Cat[] countingSort(Cat[] cats) {
		int[] minMaxKey = findMinMaxKey(cats);
		int minKey = minMaxKey[0];
		int maxKey = minMaxKey[1];
		int n = maxKey - minKey + 1;
		int[] support = new int[n];
		for (Cat element : cats) {
			support[element.getAge() - minKey] += 1;
		}
		int size = cats.length;
		for (int i = support.length - 1; i >= 0; i--) {
			size -= support[i];
			support[i] = size;
		}
		Cat[] result = new Cat[cats.length];
		for (Cat cat : cats) {
			result[support[cat.getAge() - minKey]] = cat;
			support[cat.getAge() - minKey] += 1;
		}
		return result;
	}

}
