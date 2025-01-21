package LoadBalancer;

public class Config {
    private String domain;
    private Integer port;

    public Config(String domain, Integer port) {
        this.domain = domain;
        this.port = port;
    }

    public String getDomain() {
        return domain;
    }

    public Integer getPort() {
        return port;
    }
}
