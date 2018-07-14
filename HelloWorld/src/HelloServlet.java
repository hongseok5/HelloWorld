import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	String title;
	String user;
	String fileName;
	String ext;
	HelloService service;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		String savePath = "/home/hongseok5/upload";
		//서버에 올릴때 바꾸기
		int fileSize = 5 * 1024 * 1024;
		String encType = "UTF-8";
		
		try {
			MultipartRequest multi = 
					new MultipartRequest(req, savePath, fileSize, encType, new DefaultFileRenamePolicy());
			fileName = multi.getFilesystemName("uploadFile");
			title = multi.getParameter("title");
			user = multi.getParameter("user");
			//int index = fileName.length();
			//int loc = fileName.lastIndexOf(".");
			//String substr = fileName.substring(2,4);
			ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
			//확장자 가져오기
			if(fileName == null) {
				System.out.println("not uploaded");
			} else {
				out.println("uploaded successfully!");
			}
						
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		service = new HelloService();
		//service.transferFile(fileName);
		service.transferInfo(title, user, ext);
	}
	
	

}
