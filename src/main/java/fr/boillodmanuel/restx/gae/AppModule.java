package fr.boillodmanuel.restx.gae;

import com.google.common.base.Charsets;
import restx.config.ConfigLoader;
import restx.config.ConfigSupplier;
import restx.factory.Module;
import restx.factory.Provides;
import restx.security.*;

@Module
public class AppModule {
    @Provides
    public SignatureKey signatureKey() {
         return new SignatureKey("074bce97-f9c2-47dc-9e6e-cbf55aa7b2db gae-restx-poc -233244246196069400 gae-restx-poc".getBytes(Charsets.UTF_8));
    }

    @Provides
    public ConfigSupplier appConfigSupplier(ConfigLoader configLoader) {
        // Load settings.properties in fr.boillodmanuel.restx.gae package as a set of config entries
        return configLoader.fromResource("fr/boillodmanuel/restx/gae/settings");
    }

}
