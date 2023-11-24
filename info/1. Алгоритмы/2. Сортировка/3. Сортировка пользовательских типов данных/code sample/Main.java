class Cat {
	private String name;
	private int age;

	public Cat(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Cat [name=" + name + ", age=" + age + "]";
	}
}

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Cat cat1 = new Cat("Vaska", 6);
		Cat cat2 = new Cat("Barsik", 2);
		Cat cat3 = new Cat("Umka", 12);
		Cat cat4 = new Cat("Kuzia", 4);

		Cat[] cats = new Cat[] { cat1, cat2, null, cat3, cat4 };

		for (int i = 0; i < cats.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < cats.length; j++) {
				if (compareCat(cats[minIndex], cats[j]) > 0) {
					minIndex = j;
				}
			}
			if (minIndex != i) {
				Cat temp = cats[i];
				cats[i] = cats[minIndex];
				cats[minIndex] = temp;
			}
		}

		for (Cat cat : cats) {
			System.out.println(cat);
		}

	}

	public static int compareCat(Cat a, Cat b) {
		if (a != null && b == null) {
			return 1;
		}
		if (a == null && b != null) {
			return -1;
		}
		if (a == null && b == null) {
			return 0;
		}
		if (a.getAge() > b.getAge()) {
			return 1;
		}
		if (a.getAge() < b.getAge()) {
			return -1;
		}
		return 0;
	}

}
