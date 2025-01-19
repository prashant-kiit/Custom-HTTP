package test.Containerization;

public class Resource {
    private static Resource instance;

    private Resource() {
        // Private constructor to prevent instantiation
    }

    public static Resource getInstance() {
        if (instance == null) {
            instance = new Resource();
        }
        return instance;
    }
}
