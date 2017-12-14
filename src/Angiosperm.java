import java.util.HashSet;
import java.util.Set;

public abstract class Angiosperm extends Plant {
	public Set<Fruit> myFruits = new HashSet<Fruit>();
	private double energyPerFruit;
	public int maxNumFruit;
	private int fruitPeriod;

	public Angiosperm(int age, int ageDeath, double energy, double height, double mass, Space location,
			int maxNumFruit, int fruitPeriod, double maxEnergy) {
		super(age, ageDeath, energy, height, mass, location, maxEnergy);
		this.maxNumFruit = maxNumFruit;
		this.fruitPeriod = fruitPeriod;
		// TODO Auto-generated constructor stub
	}
}
