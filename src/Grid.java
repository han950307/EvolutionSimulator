import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

public class Grid {
	public static int n;
	private Grid g;
	private Space[][] s;

	/**
	 * creates an n x n grid
	 * 
	 * @param n
	 * @return
	 */
	public Grid(int n) {
		makeGrid(n);
	}

	public void makeGrid(int n) {
		if (g == null) {
			this.s = new Space[n][n];
			Grid.n = n;
			this.g = this;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					s[i][j] = new Space(new Coord(i, j), g);
				}
			}
		}
	}

	public Space getSpace(Coord c) {
		return s[c.getx()][c.gety()];
	}

	public void putOrganism(Organism o, Space s) {
		if (s.isEmpty()) {
			s.setOrganism(o);
		}
	}

	public void removeOragnism(Space s) {
		s.setOrganism(null);
	}

	/**
	 * returns all organisms within radius r.
	 * 
	 * @param r
	 * @return
	 */
	public Set<Organism> getOrgsWithin(Space s, int r) {
		Set<Organism> output = new HashSet<Organism>();

		ArrayDeque<Space> toExplore = new ArrayDeque<Space>();
		Set<Space> visited = new HashSet<Space>();
		visited.add(s);
		toExplore.addAll(this.getNeighboringSpaces(s));

		// Do a DFS (to save memory) and search the neighbors.
		while (!toExplore.isEmpty()) {
			Space cur = toExplore.remove();
			visited.add(cur);
			if (cur.getOrganism() != null) {
				output.add(cur.getOrganism());
			}
			Set<Space> neighbors = this.getNeighboringSpaces(cur);

			// for each neighbor, limit search to s within radius.
			for (Space neighbor : neighbors) {
				if (!visited.contains(neighbor) && s.distTo(neighbor) <= r) {
					toExplore.add(neighbor);
				}
			}
		}

		return output;
	}

	/**
	 * Given a space, returns all neighboring spaces.
	 * 
	 * @param s
	 * @return
	 */
	public Set<Space> getNeighboringSpaces(Space s) {
		Set<Space> output = new HashSet<Space>();
		Coord c = s.getCoord();
		int x = c.getx();
		int y = c.gety();

		if (x < n - 1)
			output.add(this.s[x + 1][y]);
		else
			output.add(this.s[0][y]);
		if (x > 0)
			output.add(this.s[x - 1][y]);
		else
			output.add(this.s[n - 1][y]);
		if (y < n - 1)
			output.add(this.s[x][y + 1]);
		else
			output.add(this.s[x][0]);
		if (y > 0)
			output.add(this.s[x][y - 1]);
		else
			output.add(this.s[x][n - 1]);

		return output;
	}
}
