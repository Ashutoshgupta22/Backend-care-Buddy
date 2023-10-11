package com.aspark.carebuddy.security;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.stringprep.XmppStringprepException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class XMPPConfig {


//    @Bean
//    public XMPPTCPConnectionConfiguration xmpptcpConnectionConfiguration() {
//
//        try {
//
//            return XMPPTCPConnectionConfiguration.builder()
//                    .setUsernameAndPassword("admin@localhost","password")
//                    .setXmppDomain("")
//                    .setHost("localhost")
//                    .build();
//
//        } catch (XmppStringprepException e) {
//            throw new RuntimeException(e);
//        }
//    }




}
