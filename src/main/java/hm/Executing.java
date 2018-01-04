package hm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executing {

	private static ExecutorService executor = Executors.newFixedThreadPool(20);

	private Executing() {
	}

	public static void shutdown() {
		executor.shutdown();
	}

	public static void add(Runnable runnable) {
		executor.submit(runnable);
	}

}
