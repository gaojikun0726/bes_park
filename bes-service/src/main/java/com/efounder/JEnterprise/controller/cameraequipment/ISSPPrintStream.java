package com.efounder.JEnterprise.controller.cameraequipment;
public class ISSPPrintStream extends Thread {
	java.io.InputStream __is = null;

	public ISSPPrintStream(java.io.InputStream is) {
		__is = is;
	}

	public void run() {
		try {
			while (this != null) {
				int _ch = __is.read();
				if (_ch != -1)
					System.out.print((char) _ch);
				else
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}