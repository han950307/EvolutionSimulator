import java.io.IOException;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// Things to try:
		// 1. Two plants--tall fruit bearing, low shrubbery.
		// See if the animal species diverge and specialize.

		// 2. Start with low hanging fruits, and then raise the bar.
		// This should show why there are no intermediary species.

		// Another way is to start with shrubbery eating species
		// Then introduce tall fruit bearing trees into the environment
		// that outcompetes the shrubbery (due to blockage of sunlight)
		// and see if speciation occurs.
		int n = 100;
		World w = new World(n);
		w.populatePearTrees(2000);
		w.populateProtomal(100);
		w.run();
	}
}
