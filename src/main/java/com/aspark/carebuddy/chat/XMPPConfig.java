package com.aspark.carebuddy.chat;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.stringprep.XmppStringprepException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

@Configuration
public class XMPPConfig {

    @Bean
    public XMPPTCPConnectionConfiguration xmpptcpConnectionConfiguration() {

        try {
            return XMPPTCPConnectionConfiguration.builder()
                    .setUsernameAndPassword("user1","user1")
                    .setXmppDomain("aspark-care-buddy.ap-south-1.elasticbeanstalk.com")
                    .setHost("localhost")
                    .setSecurityMode(ConnectionConfiguration.SecurityMode.ifpossible)
                    .setConnectTimeout(300)
                    .setPort(5222)
                    .build();
        } catch (XmppStringprepException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public XMPPTCPConnection xmpptcpConnection(XMPPTCPConnectionConfiguration configuration) {
        return new XMPPTCPConnection(configuration);
    }

}
