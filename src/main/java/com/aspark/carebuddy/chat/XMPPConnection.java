package com.aspark.carebuddy.chat;

import lombok.AllArgsConstructor;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class XMPPConnection {

 //   private XMPPTCPConnectionConfiguration config;


//    public XMPPConnection() {
//        AbstractXMPPConnection connection = new XMPPTCPConnection(config);
//        try {
//            connection.connect();
//            connection.login();
//
//        } catch (SmackException | IOException | XMPPException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
