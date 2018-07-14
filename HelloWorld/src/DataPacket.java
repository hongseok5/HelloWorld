

import java.io.UnsupportedEncodingException;
import java.util.List;

public class DataPacket {
	
	private byte[] dataArray;
	private int index = 0;
	
	private final static byte STX = 0x02;
	private final static byte ETX = 0x03;
	private final static byte NVL = 0x04;
	private final static byte DATA_HD_SIZE = 64;
	private final static byte PARAM_SIZE = 32;
	
	
	public DataPacket(byte flag, List<String> params) throws UnsupportedEncodingException {

		for( String param : params) {
			if(param.getBytes().length > 32) {
				System.out.println("A param is too big");
			}			
		}
		this.dataArray = new byte[66+params.size()*32];
		this.dataArray[index++] = STX;
		setDataSize(params);
		this.dataArray[index++] = flag;
		for(int i = index; i <= DATA_HD_SIZE; i++) {
			this.dataArray[index++] = NVL;			
		}
		makeParamByte(params);
		this.dataArray[index] = ETX;
		
	}
	
	
	
	
	public void setDataSize(List<String> params) {
		Integer size = params.size() * 32 + DATA_HD_SIZE;
		String packetSize = size.toString();
		byte[] tempArray = 	packetSize.getBytes();
		int gap = 8 - tempArray.length;
		
		for(int i=0; i < gap; i++ ) {
			this.dataArray[index++] = NVL;			
		}
		for(int i=0; i < tempArray.length; i++) {
			this.dataArray[index++] = tempArray[i];
	 	}
	}
	
	public void makeParamByte(List<String> params) throws UnsupportedEncodingException {
		for(String param : params) {
			byte[] tempArray = param.getBytes("UTF-8");
			int gap = PARAM_SIZE - tempArray.length;
			for(int i = 0; i < tempArray.length; i++) {
				this.dataArray[index++] = tempArray[i];
			}
			for(int i = 0; i < gap; i++) {
				this.dataArray[index++] = NVL;
			}
		}		
	}
	
	public byte[] getDataArray() {
		return dataArray;
	}

}
