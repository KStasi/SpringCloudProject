package ua.akondaur.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
@ConfigurationProperties(prefix = "test")
public class ConfigClientAppConfiguration {
    private String property1;
    private String property2;
    private String property3;
    private String property4;
    private String property5;
    private String property6;

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property) {
        this.property1 = property;
    }

    public String getProperty2() {
        return property2;
    }

    public void setProperty2(String property) {
        this.property2 = property;
    }

    public String getProperty3() {
        return property3;
    }

    public void setProperty3(String property) {
        this.property3 = property;
    }

    public String getProperty4() {
        return property4;
    }

    public void setProperty4(String property) {
        this.property4 = property;
    }

    public String getProperty5() {
        return property5;
    }

    public void setProperty5(String property) {
        this.property5 = property;
    }

    public String getProperty6() {
        return property6;
    }

    public void setProperty6(String property) {
        this.property6 = property;
    }

}