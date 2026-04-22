package api.config;

public class Config {

    public static String baseUrl() {
        return System.getProperty("baseUrl", "https://reqres.in/api");
    }

    public static String apiKey() {
        return System.getProperty("reqresKey",
                "pro_be36ad5c35adcc1709011dd81ec0a104027b9b2bd13aa58926a3086ff1c8c738");
    }

    public static String env() {
        return System.getProperty("env", "prod");
    }
}
