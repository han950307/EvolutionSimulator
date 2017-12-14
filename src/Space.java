/**
 * Space in a grid.
 * 
 * @author han95
 *
 */
public class Space {
	private final Coord c;
	private Organism o;
	private Grid g;

	Space(Coord c, Grid g) {
		this.c = c;
		this.g = g;
	}

	public boolean isEmpty() {
		if (o != null) {
			return false;
		} else {
			return true;
		}
	}

	public Coord getCoord() {
		return this.c;
	}

	public Organism getOrganism() {
		return this.o;
	}

	public int distTo(Space s) {
		return this.c.distTo(s.c);
	}

	public void setOrganism(Organism o) {
		this.o = o;
	}

	public Grid getGrid() {
		return g;
	}
	
}
