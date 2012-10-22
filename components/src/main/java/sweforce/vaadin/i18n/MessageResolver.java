package sweforce.vaadin.i18n;

import java.util.Locale;

/**
 * TODO come up with a better name?
 */
public interface MessageResolver {

    public String getMessage(HasI18nKey hasI18nKey, Locale locale);
}
