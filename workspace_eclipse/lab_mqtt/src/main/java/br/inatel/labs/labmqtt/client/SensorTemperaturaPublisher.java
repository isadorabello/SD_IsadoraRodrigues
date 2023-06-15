package br.inatel.labs.labmqtt.client;

import java.util.Random;
import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SensorTemperaturaPublisher {
	
	public static void main(String[] args) throws MqttException {
		
		// CRIAR O PUBLISHER
		String publisherID = UUID.randomUUID().toString();
		IMqttClient publisher = new MqttClient(MyConstants.URI_BROKER, publisherID);
		
		// CRIAR MENSAGEM
		MqttMessage msg = getTempMessage();
		msg.setQos(0);
		msg.setRetained(true);
		
		// CONECTA
		MqttConnectOptions options = new MqttConnectOptions();
		options.setAutomaticReconnect(true);
		options.setCleanSession(true);
		options.setConnectionTimeout(10);
		publisher.connect(options);
		
		// PUBLICA
		publisher.publish(MyConstants.TOPIC_1, msg);
		
		// DESCONECTA
		publisher.disconnect();
		
	}
	
	private static MqttMessage getTempMessage() {
		Random r = new Random();
		double temp = 80 + r.nextDouble() * 20.0;
		byte[] payload = String.format("T:%04.2f", temp).getBytes();
		return new MqttMessage(payload);
	}
	
}
