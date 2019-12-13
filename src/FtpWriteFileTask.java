import java.io.File;

public class FtpWriteFileTask implements Runnable {

	private File inputFile;

	public FtpWriteFileTask(File inputFile) {
		this.inputFile = inputFile;
	}

	@Override
	public void run() {
		Logger.logInfo("TASK: Task started for writing file: " + inputFile.getName());

		// scoping MyFtpClient's AutoCloseable implementation. in this try-catch
		// block
		try (MyFtpClient ftClient = SharedFtpClient.getConnection()) {

			String filePath = "/files";

			// writes to remote path with fileName
			boolean transferResult = ftClient.transferFile(filePath, inputFile);

			if (transferResult) {
				Logger.logInfo("TASK: File " + inputFile + " has been transfered");
			} else {
				Logger.logInfo("TASK: Error - File " + inputFile + " can NOT been transfered");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		Logger.logInfo("TASK: Task stopped for writing file: " + inputFile.getName());

	}

}
