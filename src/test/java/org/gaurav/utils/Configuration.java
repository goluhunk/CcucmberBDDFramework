package org.gaurav.utils;

public class Configuration {

    static String baseURL;
    static String authLoginStub;
    static String signInPage;

    public static String getBaseURL() {
        return baseURL;
    }

    public static String getAuthLoginStub() {
        return authLoginStub;
    }

    public static String getSignInPage() {
        return signInPage;
    }

    public  static void setBaseURL(String URL) {
        baseURL = URL;
    }

    public static  void setAuthLoginStub(String LoginStub) {
        authLoginStub =  LoginStub;
    }

    public static void setSignInPage(String Page) {
        signInPage = Page;
    }


    public static void settings( String environment){
        switch(environment){
            case "local": {
                Configuration.setBaseURL("http://localhost");
                Configuration.setAuthLoginStub("http://localhost:9949/auth-login-stub");
                Configuration.setSignInPage("http://localhost:9025/gg/sign-in");
                break;
            }
            case "dev": {
                Configuration.setBaseURL("https://www.development.tax.service.gov.uk");
                Configuration.setAuthLoginStub("https://www.development.tax.service.gov.uk/auth-login-stub");
                Configuration.setSignInPage("https://www.development.tax.service.gov.uk/gg/sign-in");
                break;
            }
            case "staging": {
                Configuration.setBaseURL("https://www.staging.tax.service.gov.uk");
                Configuration.setAuthLoginStub("https://www.staging.tax.service.gov.uk/auth-login-stub");
                Configuration.setSignInPage("https://www.staging.tax.service.gov.uk/gg/sign-in");
                break;
            }
            case "qa": {
                Configuration.setBaseURL("https://www.qa.tax.service.gov.uk");
                Configuration.setAuthLoginStub("https://www.qa.tax.service.gov.uk/auth-login-stub");
                Configuration.setSignInPage("https://www.qa.tax.service.gov.uk/gg/sign-in");
                break;
            }

            default:{

            }
        }
    }

}
