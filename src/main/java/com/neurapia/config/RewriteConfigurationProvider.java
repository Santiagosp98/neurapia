package com.neurapia.config;

import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

import javax.servlet.ServletContext;

/**
 * This class has URL-rewriting purposes. For that, it uses
 * the Rewrite tool by OCPsoft.
 * You will find all rules for routing, rewriting and filtering of the project.
 *
 * @author Santiago Samper
 * @version %I%, %G%
 * @since 2018-13-03
 */
@RewriteConfiguration
public class RewriteConfigurationProvider extends HttpConfigurationProvider{
    @Override
    public Configuration getConfiguration(final ServletContext context) {
        return ConfigurationBuilder.begin()
                .addRule(Join.path("/").to("index.xhtml"));
    }

    @Override
    public int priority() {
        return 10;
    }
}
