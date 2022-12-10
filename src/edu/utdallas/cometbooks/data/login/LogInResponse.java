package edu.utdallas.cometbooks.data.login;

public final class LogInResponse {
    public static LogInResponse create(String netId, String name, LogInResponseType type) {
        return new LogInResponse(netId, name, type);
    }

    private final String netId;
    private final String name;
    private final LogInResponseType type;

    private LogInResponse(String netId, String name, LogInResponseType type) {
        this.netId = netId;
        this.name = name;
        this.type = type;
    }

    public String getNetId() {
        return netId;
    }

    public String getName() {
        return name;
    }

    public LogInResponseType getType() {
        return type;
    }
}
