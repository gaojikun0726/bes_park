
import org.ace.websocket.handler.WebSocketConfig;
import org.ace.websocket.handler.WebSocketRunner;
import org.ace.websocket.handler.WebSocketService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author xiepufeng
 */
public class WebSocketStart
{
    public static void main(String[] args)
    {

		WebSocketRunner runner = new WebSocketRunner(new WebSocketConfig(){

			@Override
			public int getPort()
			{
				return 7788;
			}

			@Override
			public String getPath()
			{
				return "/comm";
			}

			@Override
			public String getPassword()
			{
				return "123456";
			}

			@Override
			public int getInterval()
			{
				return 30;
			}

			@Override
			public int getTimeoutIntervals()
			{
				return 5;
			}
		});
		Thread runThread = new Thread(runner);
		runThread.start();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		while (true) {
			try
			{
				WebSocketService.postEvent("sessionId1234565658", "test", br.readLine());
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}

	}
}
