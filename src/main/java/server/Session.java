package server;

public interface Session {
    void setAttribute(String key, Object value);
    Object getAttribute(String key);
}
