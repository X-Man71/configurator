package de.hs.furtwangen.bam.jee.configurator.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import de.hs.furtwangen.bam.jee.configurator.model.LoanRequest;

public class RequestService {

	public void sendRequest(LoanRequest loanRequest) {
		try {
			String request = "http://localhost:8081/loanbroker";
			URL url = new URL(request);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);

			String body = generateBody(loanRequest);

			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length",
					String.valueOf(body.length()));

			OutputStreamWriter writer = new OutputStreamWriter(
					connection.getOutputStream());

			writer.write(body);
			writer.flush();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			for (String line; (line = reader.readLine()) != null;) {
				System.out.println(line);
			}

			writer.close();
			reader.close();

		} catch (IOException ioex) {
			System.out.println(ioex);
		}
	}

	private String generateBody(LoanRequest loanRequest)
			throws UnsupportedEncodingException {
		String body = "requestId="
				+ URLEncoder.encode(loanRequest.getRequestId(), "UTF-8")
				+ "&"
				+ "userId="
				+ URLEncoder
						.encode(loanRequest.getUserId().toString(), "UTF-8")
				+ "&"
				+ "amount="
				+ URLEncoder
						.encode(loanRequest.getAmount().toString(), "UTF-8")
				+ "&" + "term="
				+ URLEncoder.encode(loanRequest.getTerm().toString(), "UTF-8")
				+ "&" + "ssn="
				+ URLEncoder.encode(loanRequest.getSsn().toString(), "UTF-8");
		return body;
	}

}
