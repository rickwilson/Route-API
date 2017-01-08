package core.email;

public class ApiNewAccountEmailTemplate {
    public static String createBody(String name, long userId, String username, String password, String apiKey) {
        String title = "Hello "+name+"!";
        String content = "Welcome to Route! We are excited to show you everything we can do for you. Your new Route Dashboard is all set. Just click the 'Activate' button and use your email address and password to log in.<br><br> Email Address: "+username+"<br>Password: "+password+"<br><br>";
        String asterisk = "*If you did not sign up for Route, please disregard.";
        String actionUrl = "https://routeit.cloud/register/verify/"+userId+"/"+apiKey;
        String actionLabel = "Activate";
        return RouteDefaultTemplate.createBody(title,content,asterisk,actionUrl,actionLabel);
    }
}
