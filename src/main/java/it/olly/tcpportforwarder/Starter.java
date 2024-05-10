package it.olly.tcpportforwarder;

import java.io.IOException;

import javax.annotation.PreDestroy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class Starter implements CommandLineRunner {
	private TCPForwardServer sf;

	@Override
	public void run(String... args) throws IOException {
		try {
			int inPort = Integer.parseInt(args[0]);
			String outIp = args[1];
			int outPort = Integer.parseInt(args[2]);

			sf = new TCPForwardServer(inPort, outIp, outPort);
			sf.start();
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException eee) {
			System.err.println("Parameters must be [sourcePort] [destinationHost] [destinationPort]");
		}

	}

	@PreDestroy
	public void stop() throws IOException {
		sf.stop();
	}

}
