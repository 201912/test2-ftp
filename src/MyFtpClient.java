

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;

public class MyFtpClient implements AutoCloseable {


    // FTP instance
    private FTPClient ftpClient = null;

    /**
     * Open an FTP connection
     *
     * @param user, username of an FTP
     * @param host, host of an FTP
     * @param pass, password of an FTP
     * @param port, port number of an FTP
     * @param keyFilePath, null in this case
     */
    public void connect(String username, String password, String host, int port) {

    	try {

    		if(this.ftpClient == null) {
    			this.ftpClient = new FTPClient();
    			this.ftpClient.connect(host, port);
    			this.ftpClient.login(username, password);
    			this.ftpClient.enterLocalPassiveMode();
    		}

		} catch(Throwable e) {
			throw new InternalError("Failed to connect to FTP server." + e);
		}

    	Logger.logInfo("FTP: Connected to " + host);
    }


    /**
     * Close an FTP connection
     */
    public void close() {

		try {
			Logger.logInfo("FTP: try closing connection");
			if(this.ftpClient != null && this.ftpClient.isConnected()) {
				this.ftpClient.disconnect();
				Logger.logInfo("FTP: connection is closed");
			}
			else {
				Logger.logInfo("FTP: no open connection to close");
			}
		} catch(Throwable e) {
			throw new InternalError("Failed to close the connection to FTP server." + e);
		}
    }

    /**
     * Produce to an FTP server
     *
     * @param path, the destination where to produce
     * @param files, a list of files need to be produced
     */
    public boolean transferFile(String path, File file) {

    	try(InputStream inputStream = new FileInputStream(file)) {
			boolean pathChanged = this.ftpClient.changeWorkingDirectory(path);
			Logger.logInfo("FTP: path changed status: " + pathChanged);
			Logger.logInfo("FTP: start writing file: " + file.getName());
			this.ftpClient.storeFile(file.getName(), inputStream);

			String aaa = this.ftpClient.getStatus();

			Logger.logInfo("FTP: completed storing file - " + file.getName());
			return true;

		} catch(Throwable e) {
			Logger.logInfo("FTP: Error - " + e.getMessage());
			e.printStackTrace();
			return false;
		}

    }
}
