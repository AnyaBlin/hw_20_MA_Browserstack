package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.MobileConf;
import io.appium.java_client.android.AndroidDriver;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

    public class BrowserstackMobileDriver implements WebDriverProvider {

        public static MobileConf mobileConf = ConfigFactory.create(MobileConf.class);

        String user = mobileConf.user();
        String key = mobileConf.key();
        String appURL = mobileConf.appURL();
        static String remoteURL = mobileConf.remoteURL();

        public static URL getBrowserstackUrl() {
            try {
                return new URL(remoteURL);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
            // Set your access credentials
            desiredCapabilities.setCapability("browserstack.user", user);
            desiredCapabilities.setCapability("browserstack.key", key);

            // Set URL of the application under test
            desiredCapabilities.setCapability("app", appURL);

            // Specify device and os_version for testing
            desiredCapabilities.setCapability("device", "Google Pixel 3");
            desiredCapabilities.setCapability("os_version", "9.0");

            // Set other BrowserStack capabilities
            desiredCapabilities.setCapability("project", "First Java Project");
            desiredCapabilities.setCapability("build", "browserstack-build-1");
            desiredCapabilities.setCapability("name", "first_test");

            // Initialise the remote Webdriver using BrowserStack remote URL
            // and desired capabilities defined above
            return new AndroidDriver(getBrowserstackUrl(), desiredCapabilities);
        }

    }

