package jp.glassmoon.app.amqpspawn;

import java.util.ArrayList;
import java.util.List;
import com.beust.jcommander.Parameter;

public class Option {
	static final String DEFAULT_URI = "amqp://localhost";
	static final String DEFAULT_QUEUE = "ger";
	static final String DEFAULT_EXCHANGE = "gerrit.event";
	static final String DEFAULT_ROUTINGKEY = "gerrit.event.*";
	static final String DEFAULT_TAG = "rabbitmq-client";
	
	@Parameter
	private List<String> parameter = new ArrayList<String>();

	@Parameter(names = { "-h", "--help" }, description = "Display this help")
	private boolean help;
	@Parameter(names = { "-u", "--uri" }, description = "URI to AMQP")
	private String uri = DEFAULT_URI;
	@Parameter(names = { "-q", "--queue" }, description = "Queue name")
	private String queue = DEFAULT_QUEUE;
	@Parameter(names = { "-e", "--exchange" }, description = "Exchange name")
	private String exchange = DEFAULT_EXCHANGE;
	@Parameter(names = { "-k", "--routingkey" }, description = "Routing key")
	private String routingkey = DEFAULT_ROUTINGKEY;
	@Parameter(names = { "-t", "--tag" }, description = "Consumer tag")
	private String consumerTag = DEFAULT_TAG;
	@Parameter(names = { "-p", "--passive" }, description = "Do not create the queue if it doesn't exist")
	private boolean passive;
	@Parameter(names = { "-n", "--nospawn" }, description = "Create queue only then exit")
	private boolean nospawn;
	
	public boolean isHelp() {
		return this.help;
	}
	
	public String getUri() {
		return this.uri;
	}
	
	public String getQueue() {
		return this.queue;
	}
	
	public String getExchange() {
		return this.exchange;
	}
	
	public String getRoutingKey() {
		return this.routingkey;
	}
	
	public String getConsumerTag() {
		return this.consumerTag;
	}
	public boolean isPassive() {
		return this.passive;
	}

  public boolean isNoSpawn() {
    return this.nospawn;
  }
}
