package webapp;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

public class JettyServer 
{
	public static void main(String[] args) throws Exception 
	{
		Server server = new Server();
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(8080);
		server.setConnectors(new Connector[] { connector });
		WebAppContext context = new WebAppContext();
		
		context.setContextPath("/");

		ResourceCollection resourceCollection = new ResourceCollection(
				new String[] { "src/main/webapp" });

		context.setBaseResource(resourceCollection);

		context.setExtraClasspath("C:\\Users\\Chris\\git\\configurator\\target\\classes");

		HandlerCollection handlers = new HandlerCollection();
		handlers.setHandlers(new Handler[] { context, new DefaultHandler() });
		server.setHandler(handlers);

		// Initialize javax.websocket layer
		WebSocketServerContainerInitializer.configureContext(context);
		server.start();
		server.join();
	}
}
