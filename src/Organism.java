import java.util.Random;

public abstract class Organism {
	private static double ENERGY_CAPACITY = 1000;

	private int age;
	private int ageDeath;
	private double energy;
	private double height;
	private double mass;
	private double maxEnergy;
	Space location;

	public Organism(int age, int ageDeath, double energy, double height, double mass, Space location,
			double maxEnergy) {
		this.age = age;
		this.ageDeath = ageDeath;
		this.energy = energy;
		this.height = height;
		this.mass = mass;
		this.location = location;
		this.maxEnergy = maxEnergy;
	}

	abstract void die();

	abstract Organism reproduceWith(Organism o, Random r);

	public int getAge() {
		return this.age;
	}

	public void updateAge() {
		this.age++;
		if (this.age >= ageDeath) {
			this.die();
		}
	}
	
	public void updateHeight(double d) {
		height += d;
	}

	public double getMass() {
		return mass;
	}
	
	public int getDeathAge() {
		return ageDeath;
	}

	public double getHeight() {
		return height;
	}

	public double getEnergy() {
		return energy;
	}

	/**
	 * adds e to current energy
	 * 
	 * @param e
	 * @return
	 */
	public void updateEnergy(double e) {
		energy += e;
	}

	public void updateLocation(Space loc) {
		this.location = loc;
	}

	public Space getLocation() {
		return this.location;
	}

	public double getEnergyCapacity() {
		return ENERGY_CAPACITY;
	}

	public double getEnergyDensity() {
		return energy / mass;
	}

	public boolean timeToDie() {
		if (this.age >= this.ageDeath) {
			return true;
		} else {
			return false;
		}
	}

}
