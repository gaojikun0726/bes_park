package com.core.common.ppcl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TestClient {
	public static void main(String[] args) throws Exception {
		String IP = "172.16.12.103";
		Process proc;
		String line = null;
		List<String> lines = new ArrayList<String>();
		try {
			String[] args1 = new String[] { "python" ,"src/main/java/com/core/common/ppcl/client.py",IP};
//			proc = Runtime.getRuntime().exec("python src/main/java/com/core/common/ppcl/client.py");// 执行py文件	
			proc = Runtime.getRuntime().exec(args1);// 执行py文件	
			// 用输入输出流来截取结果
			BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			while ((line = in.readLine()) != null) {
				System.out.println(line);
				lines.add(line);
			}
			in.close();
			int exitVal = proc.waitFor();
			System.out.println("Process exitValue: " + exitVal);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String lineData = lines.toString();
		System.out.println("Java调Python脚本结束："+lineData);
//		

//		Socket socket = null;
//		OutputStream oo = null;
//		FileInputStream fis = null;
//		InputStream io = null;
//		try {
//		    //1.建立socket对象实例
//			socket = new Socket("172.16.12.103", 1204);
//			//2.调用getOutputStream()方法，目的是建立数据缓冲区，将数据发送到服务器端
//			oo = socket.getOutputStream();
//			//3.创建FileInputStream对象实例，将要发送的文件写入到输出流数据缓冲区中
//			File file=new File("programJND.txt");
//			fis = new FileInputStream(file);
//			byte[] b=new byte[1024];
//			int len;
//			while((len=fis.read(b))!=-1){
//				oo.write(b, 0, len);
//			}
//			//这行代码的意思是告诉服务器端我发送的数据完毕。
//			socket.shutdownOutput();
//			//4.调用getInputStream()方法：目的是接收从服务器端发送的数据
//			io = socket.getInputStream();
//			byte[] b1=new byte[1024];
//			int len1;
//			while((len1=io.read(b1))!=-1){
//				String str=new String(b1, 0, len1);
//				System.out.println(str);
//			}
//		}  catch (Exception e) {
//			// 关闭各个连接
//			e.printStackTrace();
//		}finally{
//			if(io != null){
//				try {
//					io.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			if(fis !=null){
//				try {
//					fis.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			if(oo != null){
//				try {
//					oo.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			if(socket !=null){
//				try {
//					socket.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
	}
}
