import java.util.ArrayList;

public class Simulation {

	public static void main(String[] args) {

		 final int AGENTS = 2; // Number of Agents
		 final int SMOKERS = 3; // Number of Smokers
		 final int TIME = 10000; // Run time in ms

		Table table = new Table();
		ArrayList<Agent> agents = new ArrayList<>();
		ArrayList<Smoker> smokers = new ArrayList<>();

		//add Materials
		ArrayList<String> materials = new ArrayList<>();
		materials.add("Tabak");
		materials.add("Streichhölzer");
		materials.add("Papier");

		System.err.println("-------------------- START -------------------");

			//3 Smokers are at the table:
		for (int i = 0; i < SMOKERS; i++) {
			Smoker smoker = new Smoker(table, materials.get(i), "Smoker "+i);
			smokers.add(smoker);
			smoker.start();
		}
			//2 Agents are at the table:
		for (int i = 0; i < AGENTS; i++) {
			Agent agent = new Agent(table, materials, "Agent"+i);
			agents.add(agent);
			agent.start();
		}

		// wait
		try {
			Thread.sleep(TIME);

			System.err.println("-------------------- END OF SMOKING TIME -------------------");
			// Kill agents
			for (Agent current : agents) {
				current.interrupt();
			}
			// Kill smokers
			for (Smoker current : smokers) {
				current.interrupt();
			}
		} catch (InterruptedException e) {
		}
	}
}
