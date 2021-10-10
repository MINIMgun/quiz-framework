package net.minimgun.quizframework.models.play;

public class ClientResponse {

    private Client client;
    private String textResponse;
    private double numberResponse;

    public ClientResponse() {
        super();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getTextResponse() {
        return textResponse;
    }

    public void setTextResponse(String textResponse) {
        this.textResponse = textResponse;
    }

    public double getNumberResponse() {
        return numberResponse;
    }

    public void setNumberResponse(double numberResponse) {
        this.numberResponse = numberResponse;
    }
}
