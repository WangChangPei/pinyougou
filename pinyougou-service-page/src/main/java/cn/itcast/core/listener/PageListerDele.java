package cn.itcast.core.listener;

import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.File;

public class PageListerDele implements MessageListener {
    @Override
    public void onMessage(Message message) {
        ActiveMQTextMessage atm = (ActiveMQTextMessage) message;

        try {
            String id = atm.getText();
            //首先获取静态页在磁盘上的位置
            String classpath = this.getClass().getResource("/").getPath().replaceFirst("/", "");
            String docRoot = classpath.replaceAll(
                    "WEB-INF/classes/", "");
            String filePath=docRoot+ "/" + id + ".html";
            System.out.println(filePath);
            new File(filePath).delete();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
