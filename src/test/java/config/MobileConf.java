package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:mobile.properties"})
public interface MobileConf extends Config {

    String user();

    String key();

    String remoteURL();

    String appURL();

    String videoURL();

}
