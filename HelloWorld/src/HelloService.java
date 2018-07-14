import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class HelloService {
	
	Socket socket;
	File file;
	private static final String SERVER_IP = "210.89.191.44";	
	private static final int SERVER_PORT = 9190;
	
	
	//File metaInfo transfer by socket to ML Server
	public String transferInfo(String title, String user, String ext) {
		byte[] dataPacket;
		// 프로토콜 정의 필요
		try {
			List<String> packetDatas = new ArrayList<String>();
			
			
			
			socket = new Socket();//1.Socket 생성
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			OutputStream os = socket.getOutputStream();			
			
			//os.flush();
			InputStream is = socket.getInputStream();
			//String message = new String(echoData, 0, readByteCount, "UTF-8");


			os.close();
			is.close();

		} catch( ConnectException e ) {
			System.out.println( "[client] not connect" );
			System.out.println( e.toString());
		} catch( SocketTimeoutException e) {
		System.out.println( "[client] read timeout" );
		} catch( IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
		 	System.out.println(e.toString());
		} finally {
			try {
				if( socket != null && socket.isClosed() == false ) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	
	//Image file transver by scp command to ML Server
	public String transferFile(String fileName) {
		//리눅스 명령어
		String command = "sshpass -p320164 scp -o StrictHostKeyChecking=no ";
		// 웹서버 경로(현재는 로컬경로), 서버에 올릴때 바꾸기
		String pathFrom = "/home/hongseok5/upload/";
		// ML 서버 경로
		String pathTo = " break@210.89.191.44:/home/break/hsOh/";
		command = command + pathFrom + fileName + pathTo + fileName;
		StringBuffer output = new StringBuffer();
		Process process = null;
		BufferedReader br = null;
		Runtime runtime = Runtime.getRuntime();
		String osName = System.getProperty("os.name");
		//파일전송
		try {
			process = runtime.exec(command);
			
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
		return output.toString();
	}
	
	
}
