import java.util.Random;

public class PearTree extends Angiosperm {

	public PearTree(int age, int ageDeath, double energy, double height, double mass, Space location, int maxNumFruit,
			int fruitPeriod, double maxEnergy) {
		super(age, ageDeath, energy, height, mass, location, maxNumFruit, fruitPeriod, maxEnergy);
		// TODO Auto-generated constructor stub
	}

	@Override
	void die() {
		// TODO Auto-generated method stub

	}

	@Override
	Organism reproduceWith(Organism o, Random r) {
		return o;
		// TODO Auto-generated method stub

	}
}
