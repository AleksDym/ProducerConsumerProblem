import java.util.concurrent.ThreadLocalRandom;

public class Smoker extends Thread{
	private final Table table;
	private final String material;
	private final String name;

	public Smoker(Table table, String material, String smokerName){
		this.name = smokerName;
		this.table = table;
		this.material = material;
	}

	@Override
	public void run(){
		try {
			System.err.println(name + " is at the table");

			while (!interrupted()) {
			table.smokerTakeMaterial(material);
				makeASigarete();
				smoke();
			}
			System.err.println(name + " went away");
		}catch (InterruptedException e){
			System.err.println(name + " went away");
		}
	}


	private  void makeASigarete() throws InterruptedException {
		System.err.println(name + " is making a cigarete");
		sleep(ThreadLocalRandom.current().nextInt(500,2000));
	}

	public void smoke() throws InterruptedException{
		System.err.println(name + " is smoking a cigarete");
		int smokeTime = ThreadLocalRandom.current().nextInt(0,101);
		sleep(smokeTime);
	}
}
