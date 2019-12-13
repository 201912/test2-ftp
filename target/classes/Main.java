import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

	public static void main(String[] args) {

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

		Logger.logInfo("Maximum threads inside pool: " + executor.getMaximumPoolSize());

		for (int i = 0; i < 10; i++)
        {
			File newFile = new File("resource/testFile copy "+i);
            FtpWriteFileTask task = new FtpWriteFileTask(newFile);
            executor.execute(task);
        }

        executor.shutdown();
	}

}
