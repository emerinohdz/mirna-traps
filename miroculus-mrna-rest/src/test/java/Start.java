

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Properties;
import org.eclipse.jetty.http.ssl.SslContextFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.server.ssl.SslSocketConnector;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.xml.XmlConfiguration;

public class Start {

    public static void main(String[] args) throws Exception {

        //int timeout = (int) Duration.ONE_HOUR.getMilliseconds();
        int timeout = 3600 * 1000; // 1 hour

        Server server = new Server();

        SocketConnector connector = new SocketConnector();

        // Set some timeout options to make debugging easier.
        connector.setMaxIdleTime(timeout);
        connector.setSoLingerTime(-1);
        connector.setPort(9191);
        server.addConnector(connector);

        // check if a keystore for a SSL certificate is available, and
        // if so, start a SSL connector on port 8443. By default, the
        // quickstart comes with a Apache Wicket Quickstart Certificate
        // that expires about half way september 2021. Do not use this
        // certificate anywhere important as the passwords are available
        // in the source.
        Resource keystore = Resource.newClassPathResource("/keystore");
        keystore = null;
        if (keystore != null && keystore.exists()) {
            connector.setConfidentialPort(8443);

            SslContextFactory factory = new SslContextFactory();
            factory.setKeyStoreResource(keystore);
            factory.setKeyStorePassword("wicket");
            factory.setTrustStoreResource(keystore);
            factory.setKeyManagerPassword("wicket");
            SslSocketConnector sslConnector = new SslSocketConnector(factory);
            sslConnector.setMaxIdleTime(timeout);
            sslConnector.setPort(8443);
            sslConnector.setAcceptors(4);
            server.addConnector(sslConnector);

            System.out.println("SSL access to the quickstart has been enabled on port 8443");
            System.out.println("You can access the application using SSL on https://localhost:8443");
            System.out.println();
        }

        WebAppContext bb = new WebAppContext();
        bb.setMaxFormContentSize(1000000);
        bb.setServer(server);
        bb.setContextPath("/");
        bb.setAliases(true);
        bb.setWar("src/main/webapp");
        bb.setDescriptor("src/main/webapp/WEB-INF/web.xml");

        Properties props = new Properties();
        //props.load(Start.class.getResourceAsStream("config.properties"));

//        String webXmlFile = props.getProperty("webXml");
        String[] configFiles = {"src/main/webapp/WEB-INF/jetty-env.xml"};
        for (String configFile : configFiles) {
            XmlConfiguration configuration = new XmlConfiguration(
                    new File(configFile).toURI().toURL());

            configuration.configure(bb);
        }

        /*
        rHandler.setDirectoriesListed(true);
        rHandler.setWelcomeFiles(new String[]{"index.html"});
        Path docroot = FileSystems.getDefault().getPath("src/main/webapp");
        rHandler.setResourceBase(docroot.toString());
        LRWebSocketHandler2 wsHandler = new LRWebSocketHandler2();
        wsHandler.setHandler(rHandler);

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{bb, wsHandler});
                */

        // START JMX SERVER
        // MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        // MBeanContainer mBeanContainer = new MBeanContainer(mBeanServer);
        // server.getContainer().addEventListener(mBeanContainer);
        // mBeanContainer.start();
        server.setHandler(bb);

        try {
            // initialize LiveReload server
            int port = 5000;
            Path docroot = FileSystems.getDefault().getPath("target/classes");
            //LRServer liveServer = new LRServer(port, docroot);
            //liveServer.start();

            System.out.println(">>> STARTING EMBEDDED JETTY SERVER, PRESS ANY KEY TO STOP");
            server.start();
            System.in.read();
            System.out.println(">>> STOPPING EMBEDDED JETTY SERVER");
            server.stop();
            server.join();

            // stop LiveReload server
            //liveServer.stop();
            //liveServer.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
