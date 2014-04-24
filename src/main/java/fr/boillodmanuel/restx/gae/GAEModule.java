package fr.boillodmanuel.restx.gae;

import com.google.appengine.api.users.UserServiceFactory;
import com.google.common.collect.ImmutableSet;
import restx.admin.AdminModule;
import restx.factory.Module;
import restx.factory.Provides;
import restx.security.RestxPrincipal;
import restx.servlet.ServletPrincipalConverter;

import javax.inject.Named;
import java.security.Principal;

import static restx.servlet.ServletModule.SERVLET_PRINCIPAL_CONVERTER;

/**
 * Date: 23/4/14
 * Time: 21:06
 */
@Module
public class GAEModule {
    @Provides
    @Named("restx.activation::restx.security.RestxSessionCookieFilter::RestxSessionCookieFilter")
    public String deactivateCookieSessionFilter() {
        return "false";
    }

    @Provides
    @Named("restx.activation::restx.security.RestxSessionBareFilter::RestxSessionBareFilter")
    public String activateBareSessionFilter() {
        return "true";
    }

    @Provides @Named(SERVLET_PRINCIPAL_CONVERTER)
    public ServletPrincipalConverter gaeServletPrincipalConverter() {
        return new ServletPrincipalConverter() {
            @Override
            public RestxPrincipal toRestxPrincipal(final Principal principal) {
                return new RestxPrincipal() {
                    @Override
                    public ImmutableSet<String> getPrincipalRoles() {
                        if (UserServiceFactory.getUserService().isUserAdmin()) {
                            return ImmutableSet.of(AdminModule.RESTX_ADMIN_ROLE);
                        } else {
                            return ImmutableSet.of();
                        }
                    }

                    @Override
                    public String getName() {
                        return principal.getName();
                    }
                };
            }
        };
    }
}
