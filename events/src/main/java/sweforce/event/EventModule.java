package sweforce.event;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/8/12
 * Time: 4:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(EventBus.class).to(SimpleEventBus.class);
    }

    @Provides
    ResettableEventBus provideResettableEventBus(EventBus eventBus){
        return new ResettableEventBus(eventBus);
    }
}
