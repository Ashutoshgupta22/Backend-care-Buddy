package com.aspark.carebuddy.chat;

import lombok.AllArgsConstructor;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
public class StanzaLoggingListener implements StanzaListener {

    private final XMPPTCPConnection connection;
    private final Logger logger = LoggerFactory.getLogger(StanzaLoggingListener.class);

    @Override
    public void processStanza(Stanza packet) {

        System.out.println("stanza listener");
        logger.info("Sent packet: {}", packet.toXML());

        if (packet.getFrom().equals(connection.getUser())) {
        }
    }
}
