import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class World {
	private Grid g;
	public Random r = new Random();
	public static long time;
	public Set<Organism> myOrganisms;
	public Set<PearTree> myPearTrees;
	public Set<Protomoal> myProtomoals;

	public World(int n) {
		g = new Grid(n);
		time = 0;
		myOrganisms = new HashSet<Organism>();
	}

	/**
	 * Randomly put n pear trees with randomized parameters.
	 * 
	 * @param n
	 */
	public void populatePearTrees(int n) {
		myPearTrees = new HashSet<PearTree>();

		// create n pear trees and add them.
		for (int i = 0; i < n; i++) {
			int x = r.nextInt(Grid.n);
			int y = r.nextInt(Grid.n);
			Coord c = new Coord(x, y);
			while (!this.g.getSpace(c).isEmpty()) {
				x = r.nextInt(Grid.n);
				y = r.nextInt(Grid.n);
				c = new Coord(x, y);
			}
			Space s = this.g.getSpace(c);
			PearTree pt = new PearTree(0, 250000000, 0, r.nextGaussian() * 15 + 65, 0, s, 13, 10, 10);
			s.setOrganism(pt);
			myPearTrees.add(pt);
		}
	}

	/**
	 * Populate the animal Protomal!
	 * 
	 * @param n
	 */
	public void populateProtomal(int n) {
		myProtomoals = new HashSet<Protomoal>();

		// create n pear trees and add them.
		for (int i = 0; i < n; i++) {
			int x = r.nextInt(Grid.n);
			int y = r.nextInt(Grid.n);
			Coord c = new Coord(x, y);
			while (!this.g.getSpace(c).isEmpty()) {
				x = r.nextInt(Grid.n);
				y = r.nextInt(Grid.n);
				c = new Coord(x, y);
			}
			Space s = this.g.getSpace(c);
			Protomoal pt = new Protomoal(0, 250, 100, r.nextGaussian() * 3 + 55, 10, s, 1000);
			s.setOrganism(pt);
			myProtomoals.add(pt);
		}
	}

	public void updateOrganisms() {
		if (time % 20 == 0) {
			for (PearTree pt : myPearTrees) {
				if (pt.myFruits.size() < pt.maxNumFruit)
					pt.myFruits.add(new Pear(pt.getHeight(), 12));
			}
		}
		HashSet<Protomoal> toRemove = new HashSet<Protomoal>();
		HashSet<Protomoal> toAdd = new HashSet<Protomoal>();
		for (Protomoal p : myProtomoals) {
			Space s = p.getLocation();
			if (p.getAge() >= p.getDeathAge() || p.getEnergy() <= 0) {
				s.setOrganism(null);
				toRemove.add(p);
				continue;
			}
			Set<Organism> orgs = g.getOrgsWithin(s, 3);
			boolean ate = false;
			for (Organism o : orgs) {
				if (o instanceof PearTree && !ate) {
					PearTree that = (PearTree) o;
					if (that.myFruits.size() > 0) {
						for (Fruit k : that.myFruits) {
							ate = p.eat(k);
							that.myFruits.remove(k);
							break;
						}
					}
				}
				if (o instanceof Protomoal && !ate) {
					Protomoal that = (Protomoal) o;
					Protomoal offspring = (Protomoal) p.reproduceWith(that, r);
					if (offspring != null) {
						toAdd.add(offspring);
					}
				}
			}
			if (!ate) {
				p.move(r);
			}
			p.updateAge();
		}
		for (Protomoal p : toRemove) {
			myProtomoals.remove(p);
		}
		for (Protomoal p : toAdd) {
			myProtomoals.add(p);
		}
	}

	public void run() throws IOException {
		while (true) {
			updateOrganisms();
			time++;

			Double thing = getHeightMean();
			if (time % 50 == 0 && thing != null) {
				System.out.println(thing + "\t" + time);
				BufferedWriter writer = new BufferedWriter(new FileWriter("output/run_" + time + ".txt"));
				for (Protomoal p : myProtomoals) {
					writer.write(p.getHeight() + "\n");
				}
				writer.close();
			}

			if (time == 1500) {
				for (PearTree pt : myPearTrees) {
					if (r.nextGaussian() > 0) {
						pt.updateHeight(18);
					}
				}
			}
		}
	}

	public Double getHeightMean() {
		double outval = 0;
		for (Protomoal p : myProtomoals) {
			outval += p.getHeight();
		}
		if (myProtomoals.size() == 0)
			return null;
		return (double) outval / myProtomoals.size();
	}
}
