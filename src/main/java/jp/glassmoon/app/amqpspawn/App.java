package jp.glassmoon.app.amqpspawn;

import java.io.IOException;

import com.beust.jcommander.JCommander;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class App 
{
	static final String EXECUTABLE_NAME = "amqpspawn";
	
    public static void main( String[] args )
    {
    	Option opt = new Option();
    	JCommander jc = new JCommander(opt, args);
    	jc.setProgramName(EXECUTABLE_NAME);
    	
    	if(opt.isHelp()) {
    		jc.usage();
    		System.exit(0);
    	}
    	
        ConnectionFactory factory = null;
        Connection conn = null;
        
        try {
            factory = new ConnectionFactory();
            factory.setUri(opt.getUri());
            conn = factory.newConnection();
            final Channel channel = conn.createChannel();
        
            String queueName = opt.getQueue();
            String exchangeName = opt.getExchange();
            String routingKey = opt.getRoutingKey();
            String consumerTag = opt.getConsumerTag();

            if(!opt.isPassive()) {
            	channel.queueDeclare(queueName, true, false, false, null);
            	channel.queueBind(queueName,  exchangeName, routingKey);
              System.out.println("Queue created: " + queueName);
            }

            if(opt.isNoSpawn()) {
              return;
            }
            
            channel.basicConsume(queueName, false, consumerTag,
            		new DefaultConsumer(channel) {
						@Override
						public void handleDelivery(String consumerTag,
								Envelope envelope, BasicProperties properties,
								byte[] body) throws IOException {
							// TODO Auto-generated method stub
							super.handleDelivery(consumerTag, envelope, properties, body);
							
							String routingKey = envelope.getRoutingKey();
							long deliveryTag = envelope.getDeliveryTag();
							String eventBody = new String(body, "UTF-8");
							
							System.out.println("Received: [" + routingKey + "] " + eventBody);
							
							channel.basicAck(deliveryTag, false);
						}
            });
            
            if(opt.isPassive()) {
            	System.out.printf("Connect: Queue[%s]\n", opt.getQueue());
            }
            else {
            	System.out.printf("Connect: Queue[%s] binding to Exchange[%s], Routingkey[%s]\n",
            			opt.getQueue(), opt.getExchange(),opt.getRoutingKey());
            }
            
        } catch(Exception e) {
            System.out.println();
        }
    }
}
