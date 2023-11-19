package com.aspark.carebuddy.chat;

import lombok.AllArgsConstructor;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.jid.parts.Localpart;
import org.jxmpp.stringprep.XmppStringprepException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@AllArgsConstructor
@Service
public class XMPPService {

    private final XMPPTCPConnection connection;

    public void connect() {
        try {
            //XMPPConnection.DEBUG_ENABLED = true;
            System.out.println("chat connecting");
            connection.connect();
            StanzaLoggingListener loggingListener = new StanzaLoggingListener(connection);
            connection.addStanzaListener(loggingListener, null);
            //sendMessage();
        } catch (SmackException | IOException | XMPPException | InterruptedException e) {
            System.out.println("Connect exception");
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
    }

    public void sendMessage() {
        ChatManager chatManager = ChatManager.getInstanceFor(connection);
        try {
            EntityBareJid jid = JidCreate.entityBareFrom("user2@localhost");
            Chat chat = chatManager.chatWith(jid);
            chat.send("Hello World again");
            System.out.println("Message sent");
            //receiveMessage(chatManager);
        } catch (XmppStringprepException | SmackException.NotConnectedException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void receiveMessage(ChatManager chatManager) {

        chatManager.addIncomingListener((from, message, chat) -> {

            System.out.println("New message from " + from + ": " + message.getBody());
        });
    }

    public void createJid(String id) {

        connect();
        AccountManager accountManager = AccountManager.getInstance(connection);
        accountManager.sensitiveOperationOverInsecureConnection(true);
        try {
            accountManager.createAccount(Localpart.from(id), id);
            System.out.println("User registered in ejabberd- "+id);

        } catch (XmppStringprepException | SmackException.NoResponseException | XMPPException.XMPPErrorException |
                 SmackException.NotConnectedException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
