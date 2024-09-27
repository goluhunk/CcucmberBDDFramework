package org.gaurav.utils;

public class Configuration {

     public static String baseURL;
     public static String authLoginStub;



    public  void setBaseURL(String baseURL) {
        Configuration.baseURL = baseURL;
    }



    public  void setAuthLoginStub(String authLoginStub) {
        Configuration.authLoginStub = authLoginStub;
    }


    public  void setSignInPage(String signInPage) {
        Configuration.signInPage = signInPage;
    }

    public static String signInPage;


    public void  setting( String environment){
        switch(environment){
            case "local": {

                this.setBaseURL("http://localhost");
                this.setAuthLoginStub("http://localhost:9949/auth-login-stub");
                this.setSignInPage("http://localhost:9025/gg/sign-in");
                break;
            }
            case "dev": {
                this.setBaseURL("https://www.development.tax.service.gov.uk");
                this.setAuthLoginStub("https://www.development.tax.service.gov.uk/auth-login-stub");
                this.setSignInPage("https://www.development.tax.service.gov.uk/gg/sign-in");
                break;
            }
            case "staging": {
                this.setBaseURL("https://www.staging.tax.service.gov.uk");
                this.setAuthLoginStub("https://www.staging.tax.service.gov.uk/auth-login-stub");
                this.setSignInPage("https://www.staging.tax.service.gov.uk/gg/sign-in");
                break;
            }
            case "qa": {
                this.setBaseURL("https://www.qa.tax.service.gov.uk");
                this.setAuthLoginStub("https://www.qa.tax.service.gov.uk/auth-login-stub");
                this.setSignInPage("https://www.qa.tax.service.gov.uk/gg/sign-in");
                break;
            }

            default:{

            }
        }

    }

}
