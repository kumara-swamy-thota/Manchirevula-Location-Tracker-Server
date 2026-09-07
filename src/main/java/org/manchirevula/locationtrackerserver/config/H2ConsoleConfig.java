package org.manchirevula.locationtrackerserver.config;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

import java.sql.SQLException;

@Configuration
public class H2ConsoleConfig {

    private static final Logger logger = LoggerFactory.getLogger(H2ConsoleConfig.class);
    private Server webServer;
    private Server tcpServer;

    @EventListener(ApplicationReadyEvent.class)
    public void startH2Servers() {
        try {
            // start TCP server so external tools can connect: jdbc:h2:tcp://localhost/./data/locationdb
            tcpServer = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092").start();
            // start H2's built-in web console on port 8082
            webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
            logger.info("Started H2 TCP server at {} and web console at {}", tcpServer.getURL(), webServer.getURL());
        } catch (SQLException e) {
            logger.warn("Failed to start H2 servers: {}", e.getMessage());
        }
    }

    @EventListener(ContextClosedEvent.class)
    public void stopH2Servers() {
        if (webServer != null) {
            webServer.stop();
        }
        if (tcpServer != null) {
            tcpServer.stop();
        }
    }
}
