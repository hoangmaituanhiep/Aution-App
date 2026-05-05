package app.packets;

public enum Message {
    WELCOME("Welcome"),
    SEND_AUCTION("Send auction"),
    SEND_AUCTION_ID("Send auction id"),
    CANCLE_AUCTION("Cancel auction"),
    DISCONNECT("disconnect"),
    ERROR("Error"),
    JOIN_AUCTION("Join auction");

    private String type;
    Message(String type) {this.type = type;}
}
