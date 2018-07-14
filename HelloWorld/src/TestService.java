import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TestService {
	
	Socket socket;
	File file;
	private static final String SERVER_IP = "210.89.191.44";	
	private static final int SERVER_PORT = 9190;

	public static void main(String[] args) {
		// http://soul0.tistory.com/399
		String command = "sshpass -p320164 scp -o StrictHostKeyChecking=no ";
		String pathFrom = "/home/hongseok5/upload/";
		String fileName = "byTestModule.txt";
		String pathTo = " break@210.89.191.44:/home/break/hsOh/" + fileName;
		
		String compare = "sshpass -p320164 scp -o StrictHostKeyChecking=no /home/hongseok5/upload/byTestModule.txt break@210.89.191.44:/home/break/hsOh/byTestModule.txt";
		
		String compare2 = command + pathFrom + fileName + pathTo;
		
		boolean a = compare.equals(compare2);
		
		/**
		 *sshpass -p320164 scp -o StrictHostKeyChecking=no /home/hongseok5/upload/cooper2.jpeg break@210.89.191.44:/home/break/hsOh/cooper2.jpeg

		 * 
		 */
		StringBuffer output = new StringBuffer();
		Process process = null;
		BufferedReader br = null;
		Runtime runtime = Runtime.getRuntime();
		String osName = System.getProperty("os.name");
		
		try {
			process = runtime.exec(compare2);
			
			br = new BufferedReader( new InputStreamReader(process.getInputStream()));
			String msg = null;
			while((msg=br.readLine()) != null) {
				output.append(msg + System.getProperty("line.separator"));				
			}
			br.close();			
			br = new BufferedReader( new InputStreamReader(process.getErrorStream()));
			while((msg=br.readLine()) != null) {
				output.append(msg + System.getProperty("line.separator"));
			}
			
			} catch(IOException e) {
				output.append("IOException : " + e.getMessage());
				e.printStackTrace();
			} finally {
				try {
					process.destroy();
						if(br != null) br.close();
				} catch( IOException e1) {
					e1.printStackTrace();
				}
			}
		System.out.println("file success!");

	}

}
