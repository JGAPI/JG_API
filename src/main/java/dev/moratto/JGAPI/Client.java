package dev.moratto.JGAPI;

import dev.moratto.JGAPI.Exceptions.ClientBuildException;
import dev.moratto.JGAPI.Websocket.WebSocketManager;
import dev.moratto.JGAPI.Rest.RestClient;
import dev.moratto.JGAPI.Entities.User;

public class Client {
    private String parentServerId;
    private String clientToken;
    private String clientId;
    private User clientUser;

    private WebSocketManager webSocketManager;
    private RestClient restClient;

    private Client(ClientBuilder clientBuilder) {
        this.parentServerId = clientBuilder.parentServerId;
        this.clientToken = clientBuilder.clientToken;
        this.clientId = clientBuilder.clientId;
    }

    public void login() {
        webSocketManager = new WebSocketManager(this.clientToken);
        webSocketManager.connect();
    }

    public static class ClientBuilder {
        private String parentServerId;
        private String clientToken;
        private String clientId;

        /**
         * Build the Client
         */
        public ClientBuilder() {
        }

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

        /**
         * Set the Client's ID.
         *
         * @param clientId The client's ID.
         * @return Returns the current instance of the Client Builder.
         * @implNote This will be deprecated once Guilded API payloads the ID of the Client that is logged in.
         */
        public ClientBuilder setClientId(String clientId) {
            this.clientId = clientId;

            return this;
        }

        public ClientBuilder setParentServerId(String serverId) {
            this.parentServerId = serverId;

            return this;
        }

        /**
         * Validates the Client Object.
         * TODO: Remove requirement for ClientID.
         * REVIEW: Is ServerID needed?
         *
         * @param client @{link Client} object.
         * @throws ClientBuildException If there was a missing property from the required props.
         */
        private void validateClientObject(ClientBuilder client) throws ClientBuildException {
            if (client.clientToken == null || client.clientToken.isBlank())
                throw new ClientBuildException("A Client Token was not provided to the Client Builder.");
            if (client.clientId == null || client.clientId.isBlank())
                throw new ClientBuildException("A Client ID was not provided to the Client Builder.");
            if (client.parentServerId == null || client.parentServerId.isBlank())
                throw new ClientBuildException("A Server ID was not provided to the Client Builder.");
        }

        /**
         * Builds the Client.
         *
         * @return {link Client} object.
         * @throws ClientBuildException If there was a missing property from the required props.
         */
        public Client build() throws ClientBuildException {
            validateClientObject(this);

            return new Client(this);
        }


    }
}