import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Agent extends Thread {
	private final String agentName;
	private final Table table;
	ArrayList<String> materials;

	public Agent(Table table,ArrayList<String> materials, String name) {
		this.materials = materials;
		this.table = table;
		this.agentName = name;
	}

	@Override
	public void run() {
		try {
			System.err.println(agentName + " is at the table");

			while (!interrupted()) {
				ArrayList<String> items = getTwoRandomMaterials();
				table.agentPutMaterial(items);
				waitForTheSmoker();
			}
		} catch (InterruptedException e) {
			System.err.println(agentName + " went away");
		}
	}

	public ArrayList<String> getTwoRandomMaterials() {
		Collections.shuffle(materials);
		ArrayList<String> randomItems = new ArrayList<>();
		randomItems.add(materials.get(0));
		randomItems.add(materials.get(1));
		return randomItems;
	}

	public void waitForTheSmoker() throws InterruptedException {
		System.err.println(agentName + " is waiting for a smoker to roll a cigarette");
		int smokeTime = ThreadLocalRandom.current().nextInt(0,101);
		sleep(smokeTime);
	}
}
