import java.util.ArrayList;

public class Table {
	ArrayList<String> materialsOnTheTable = new ArrayList<>();
	boolean materialsThere = false;

	//  Agent puts smth on the table
	public synchronized void agentPutMaterial(ArrayList<String> materials) throws InterruptedException {

		while (materialsThere) {
			wait();
		}
		materialsOnTheTable.addAll(materials);
		materialsThere = true;
		System.err
				.println(Thread.currentThread().getName()
						+ " Has put items on the table: "
						+ materialsOnTheTable);
		notifyAll(); //ToDo: what exactly does it do
	}

	// Smoker takes materials from the table
	public synchronized void smokerTakeMaterial(String material) throws InterruptedException {
		while (!materialsThere || materialsOnTheTable.get(0).equals(material) || materialsOnTheTable.get(1).equals(material)) {
			wait();
		}
		System.err.println(materialsOnTheTable.get(0) + " and " + materialsOnTheTable.get(1) + " were taken");
		materialsOnTheTable.clear();
		materialsThere = false;
		notifyAll();
	}
}