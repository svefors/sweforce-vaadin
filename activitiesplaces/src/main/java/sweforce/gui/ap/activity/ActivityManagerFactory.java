package sweforce.gui.ap.activity;

import sweforce.event.EventBus;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 2/20/13
 * Time: 9:04 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ActivityManagerFactory  {

//    private final EventBus eventBus;

//    @Inject
//    public ActivityManagerFactory(EventBus eventBus) {
//        this.eventBus = eventBus;
//    }

    public SingleThreadedActivityManager createActivityManager(ActivityMapper mapper);


//    {
//        return new SingleThreadedActivityManager(mapper, eventBus);
//    }


}
