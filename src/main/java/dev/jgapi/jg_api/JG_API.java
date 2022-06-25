package dev.jgapi.jg_api;

import dev.jgapi.jg_api.entities.members.User;
import dev.jgapi.jg_api.events.library.ReadyEvent;
import dev.jgapi.jg_api.exceptions.ClientBuildException;
import dev.jgapi.jg_api.exceptions.InvalidOperationException;
import dev.jgapi.jg_api.rest.RestAction;
import dev.jgapi.jg_api.rest.RestClient;
import dev.jgapi.jg_api.rest.RestQueue;
import dev.jgapi.jg_api.websocket.WebSocketManager;

import java.util.ArrayList;
import java.util.List;

public class JG_API extends Thread {
    private String clientToken;
    private User clientUser;
    private List<ListenerAdapter> listenerAdapters;

    private WebSocketManager webSocketManager;
    private RestClient restClient;
    private RestQueue restQueue;
    private boolean running = true;

    private JG_API(ClientBuilder clientBuilder) {
        this.clientToken = clientBuilder.clientToken;
        this.listenerAdapters = clientBuilder.listenerAdapters;
        this.restQueue = new RestQueue();
        this.restClient = new RestClient(this);
    }

    public long getNextSeqNumber() {
        return this.restQueue.getNextSequenceNumber();
    }

    public void queueRestAction(RestAction restAction) {
        this.restQueue.queue(restAction);
    }

    public RestClient getRestClient() {
        return this.restClient;
    }

    public void login() {
        webSocketManager = new WebSocketManager(this.clientToken, this);
        webSocketManager.connect();
    }

    public void setupClientUser(User clientUser) throws InvalidOperationException {
        if (this.clientUser == null) {
            this.clientUser = clientUser;

            for (ListenerAdapter adapter : this.getListenerAdapters()) {
                adapter.onReadyEvent(new ReadyEvent(this));
            }
        } else {
            throw new InvalidOperationException("Attempt to change Client User. Only the Library can do this.");
        }

    }

    @Override
    public void run() {
        // We want to send a heartbeat every so often
        while (this.running) {
            // keep alive
            // TODO: Need to send a heartbeat every so often
        }
    }

    public User getClientUser() {
        return clientUser;
    }

    public List<ListenerAdapter> getListenerAdapters() {
        return listenerAdapters;
    }

    public static class ClientBuilder {
        private String clientToken;
        private List<ListenerAdapter> listenerAdapters = new ArrayList<ListenerAdapter>();

        /**
         * Build the Client
         */
        public ClientBuilder() {}

        /**
         * Set the Client's Token.
         *
         * @param clientToken The client's token.
         * @return Returns the current instance of the Client Builder.
         */
        public ClientBuilder setToken(String clientToken) {
            this.clientToken = clientToken;

            return this;
        }

        public ClientBuilder addListenerAdapter(ListenerAdapter listenerAdapter) {
            this.listenerAdapters.add(listenerAdapter);

            return this;
        }

        /**
         * Validates the Client Object.
         * REVIEW: Is ServerID needed?
         *
         * @param client @{link Client} object.
         * @throws ClientBuildException If there was a missing property from the required props.
         */
        private void validateClientObject(ClientBuilder client) throws ClientBuildException {
            if (client.clientToken == null || client.clientToken.isBlank())
                throw new ClientBuildException("A Client Token was not provided to the Client Builder.");
            if (client.listenerAdapters.isEmpty()) {
                System.out.println("\u001B[33m[WARNING]: No Listener Adapters have been added.\u001B[0m");
            }
        }

        /**
         * Builds the Client.
         *
         * @return {link Client} object.
         * @throws ClientBuildException If there was a missing property from the required props.
         */
        public JG_API build() throws ClientBuildException {
            validateClientObject(this);

            return new JG_API(this);
        }


    }
}