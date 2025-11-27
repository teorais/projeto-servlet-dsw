package br.com.edensgarden.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

    public SecurityInitializer() {
        // Informa ao Spring onde está nossa classe de configuração
        super(SecurityConfig.class);
    }
}