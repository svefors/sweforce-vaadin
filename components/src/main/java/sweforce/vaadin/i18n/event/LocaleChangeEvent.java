package sweforce.vaadin.i18n.event;

import sweforce.event.Event;
import sweforce.event.EventHandler;

import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 10/17/12
 * Time: 10:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class LocaleChangeEvent implements Event<LocaleChangeEvent.Handler> {

    private final Locale newLocale;

    public LocaleChangeEvent(Locale newLocale) {
        this.newLocale = newLocale;
    }

    @Override
    public void dispatch(Handler handler) {
        handler.onLocaleChanged(newLocale);
    }

    public static interface Handler extends EventHandler {

        public void onLocaleChanged(Locale newLocale);

    }
}
