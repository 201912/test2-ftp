
public class SharedFtpClient {

	private static MyFtpClient sharedFtpClientInstance;

	static public MyFtpClient getConnection(){

		if (sharedFtpClientInstance == null) {
			//initialize ftp client (import org.apache.commons.net.ftp.FTPClient;)
			sharedFtpClientInstance = new MyFtpClient();
			//AutoCloseable connection initialized
//			sharedFtpClientInstance.connect("ezyro_20642594", "nn4qyksc98esiq", "185.27.134.11", 21);
			sharedFtpClientInstance.connect("ftpuser", "ftpuser", "15.222.124.34", 21);
		}
		return sharedFtpClientInstance;
	}
}
