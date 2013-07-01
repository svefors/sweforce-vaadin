package sweforce.gui.activity;

import com.google.inject.AbstractModule;
import com.google.inject.PrivateModule;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;
import sweforce.gui.place.Place;
import sweforce.gui.place.PlaceTokenizer;
import sweforce.gui.place.PlaceTokenizerUtil;

import java.lang.annotation.Annotation;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 6/30/13
 * Time: 1:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class ActivityManagerModule extends AbstractModule {

    @Override
    protected final void configure() {
        bind(ActivityManager.class).to(SingleThreadedActivityManager.class);
    }

}
