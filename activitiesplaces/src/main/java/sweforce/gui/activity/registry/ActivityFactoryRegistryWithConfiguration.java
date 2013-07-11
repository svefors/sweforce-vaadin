package sweforce.gui.activity.registry;

/**
 * Created with IntelliJ IDEA.
 * User: msvefors
 * Date: 7/11/13
 * Time: 3:17 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ActivityFactoryRegistryWithConfiguration<T extends  ActivityFactoryRegistry.Configuration>
        extends ActivityFactoryRegistry, ActivityFactoryRegistry.Configuration<T>{
}
