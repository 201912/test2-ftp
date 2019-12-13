
public class Logger {
	public static void logInfo(String msg) {
		System.out.println(String.format("[T%d] %s", Thread.currentThread().getId(), msg));
	}
}
