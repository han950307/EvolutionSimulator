
public abstract class Organism {
	private int currentAge;
	private int expirationAge;
	private int energyContent;
	public double[] location;

	abstract void die();
	abstract void reproduceWith(Organism o);
}
