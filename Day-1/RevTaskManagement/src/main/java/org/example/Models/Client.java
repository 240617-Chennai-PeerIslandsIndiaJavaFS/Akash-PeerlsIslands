package org.example.Models;

public class Client {
    private int clientId;
    private String clientName;
    private String clientDetails;

    // Constructors
    public Client() {
    }

    public Client(int clientId, String clientName, String clientDetails) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientDetails = clientDetails;
    }


    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(String clientDetails) {
        this.clientDetails = clientDetails;
    }
}
