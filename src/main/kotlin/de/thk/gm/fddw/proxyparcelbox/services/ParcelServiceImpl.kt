package de.thk.gm.fddw.proxyparcelbox.services

import org.json.JSONObject
import org.springframework.stereotype.Service
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpResponse
import java.net.http.HttpRequest

@Service
class ParcelServiceImpl (private val client : HttpClient = HttpClient.newBuilder().build()) : ParcelService {
    override fun getEmailByTrackingNumber(trackingNumber: String): String {
        val request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create("https://proxyparcelbox-mia-henrichsmeyer.free.beeceptor.com/parcels/${trackingNumber}"))
            .build();
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        val json : JSONObject = JSONObject(response.body())
        return json.getString("email")
    }
}