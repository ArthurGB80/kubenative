package org.acme;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Path("/message")
public class MessageSenderResource {

    private final Emitter<String> powerEmitter;

    // Constructor for constructor injection
    public MessageSenderResource(@Channel("power") Emitter<String> powerEmitter) {
        this.powerEmitter = powerEmitter;
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response sendMessage(String message) {
        powerEmitter.send(message);
        return Response.ok("Message sent to Kafka").build();
    }
}