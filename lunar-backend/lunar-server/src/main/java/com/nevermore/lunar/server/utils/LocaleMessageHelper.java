package com.nevermore.lunar.server.utils;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * @author nevermore
 */
@Lazy
@Service
public class LocaleMessageHelper {

    private final ResourceBundleMessageSource messageSource;

    public LocaleMessageHelper(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String code) {
        return getMessage(code, null, null, Locale.getDefault());
    }

    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }
}
