import java.util.Random;

public class Protomoal extends Animal {
	Organism lastReproducedWith = null;

	public Protomoal(int age, int ageDeath, double energy, double height, double mass, Space location,
			double maxEnergy) {
		super(age, ageDeath, energy, height, mass, location, maxEnergy);
		// TODO Auto-generated constructor stub
	}

	@Override
	boolean move(Random r) {
		// TODO Auto-generated method stub
		int n = Grid.n;
		Coord c = this.getLocation().getCoord();
		int x = c.getx();
		int y = c.gety();
		Space cur = this.getLocation().getGrid().getSpace(new Coord(x, y));
		Space s = this.getLocation().getGrid().getSpace(new Coord(x, y));
		int ct = 0;
		while (!s.isEmpty()) {
			int sign = 0;
			if (r.nextGaussian() > 0) {
				sign++;
			} else {
				sign--;
			}

			if (r.nextGaussian() > 0) {
				x += sign;
				if (x > n - 1)
					x = 0;
				if (x < 0)
					x = n - 1;
			} else {
				y += sign;
				if (y > n - 1)
					y = 0;
				if (y < 0)
					y = n - 1;
			}
			s = this.getLocation().getGrid().getSpace(new Coord(x, y));
			ct++;
			if (ct > 10)
				return false;
		}
		cur.setOrganism(null);
		s.setOrganism(this);
		this.updateLocation(s);
		this.updateEnergy(-1);
		return true;
	}

	@Override
	void die() {
		// TODO Auto-generated method stub

	}

	@Override
	Organism reproduceWith(Organism o, Random r) {
		Protomoal org = (Protomoal) o;
		// if too little energy, then don't reproduce
		if (this.getEnergy() < 130 || o.getEnergy() < 130 || org == lastReproducedWith)
			return null;
		else {
			Space s1 = this.getLocation();
			boolean didMove = this.move(r);
			if (didMove) {
				this.updateEnergy(-100);
				org.updateEnergy(-100);
				this.lastReproducedWith = org;
				org.lastReproducedWith = this;
				double meanHeight = (this.getHeight() + org.getHeight()) / 2;
				double diffHeight = Math.abs(this.getHeight() - org.getHeight()) / 2;
				if (diffHeight > 8) {
					return null;
				}
				double meanMass = (this.getMass() + org.getMass()) / 2;
				double diffMass = Math.abs(this.getMass() + org.getMass()) / 2;

				Protomoal newProtomoal = new Protomoal(0, 250, 100, r.nextGaussian() * diffHeight + meanHeight,
						r.nextGaussian() * diffMass + meanMass, s1, 1000);

				return newProtomoal;
			} else {
				return null;
			}
		}
	}

	@Override
	boolean eat(Fruit f) {
		// TODO Auto-generated method stub
		if (f.height < this.getHeight()) {
			this.updateEnergy(f.energy);
			return true;
		} else {
			return false;
		}
	}

}
