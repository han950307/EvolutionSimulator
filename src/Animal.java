import java.util.Random;

public abstract class Animal extends Organism {
	public Animal(int age, int ageDeath, double energy, double height, double mass, Space location, double maxEnergy) {
		super(age, ageDeath, energy, height, mass, location, maxEnergy);
		// TODO Auto-generated constructor stub
	}

	private double[] maxSpeed;

	abstract boolean move(Random r);
	
	abstract boolean eat(Fruit o);
}
