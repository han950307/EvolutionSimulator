
public class Coord {
	private final int x;
	private final int y;

	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int[] getCoord() {
		return new int[] { this.x, this.y };
	}

	public int getx() {
		return this.x;
	}

	public int gety() {
		return this.y;
	}

	/**
	 * Returns distance from this coord to c
	 * 
	 * @param c
	 * @return
	 */
	public int distTo(Coord c) {
		int xdist = Math.abs(c.x - this.x);
		if (xdist > Grid.n / 2) {
			xdist /= 2;
		}
		int ydist = Math.abs(c.y - this.y);
		if (ydist > Grid.n / 2) {
			ydist /= 2;
		}
		return xdist + ydist;
	}
}
