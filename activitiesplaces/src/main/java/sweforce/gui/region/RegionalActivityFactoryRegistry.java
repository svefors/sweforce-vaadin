package sweforce.gui.region;

import sweforce.gui.activity.registry.ActivityFactoryRegistry;
import sweforce.gui.activity.registry.ActivityFactoryRegistryImpl;
import sweforce.gui.activity.registry.ActivityFactoryRegistryWithConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * Should be application scoped.
 */
public class RegionalActivityFactoryRegistry {

    private final Map<Region, ActivityFactoryRegistryWithConfiguration<? extends ActivityFactoryRegistry.Configuration>> regionActivityFactoryRegistryMap;

    public RegionalActivityFactoryRegistry(Entry[] entries) {
        this();
        for (Entry entry: entries){
            plugin(entry.region, entry.plugin);
        }
    }

    public RegionalActivityFactoryRegistry() {
        this(new HashMap<Region, ActivityFactoryRegistryWithConfiguration<? extends ActivityFactoryRegistry.Configuration>>());
    }

    public RegionalActivityFactoryRegistry(Map<Region, ActivityFactoryRegistryWithConfiguration<? extends ActivityFactoryRegistry.Configuration>> regionActivityFactoryRegistryMap) {
        this.regionActivityFactoryRegistryMap = regionActivityFactoryRegistryMap;
    }

    /**
     * add plugin to the region
     * @param region
     * @param plugin
     */
    public void plugin(Region region, ActivityFactoryRegistry.Plugin plugin){
        plugin.configure(findOrCreateFor(region));
    }

    protected synchronized ActivityFactoryRegistry.Configuration findOrCreateFor(Region region){
        ActivityFactoryRegistryWithConfiguration configuration = regionActivityFactoryRegistryMap.get(region);
        if (configuration == null){
            configuration = createNewRegistryConfiguration();
            regionActivityFactoryRegistryMap.put(region, configuration);
        }
        return configuration;
    }

    protected ActivityFactoryRegistryWithConfiguration createNewRegistryConfiguration(){
        return new ActivityFactoryRegistryImpl();
    }

    public static class Entry {
        public final Region region;
        public final ActivityFactoryRegistry.Plugin plugin;

        public Entry(Region region, ActivityFactoryRegistry.Plugin plugin) {
            this.region = region;
            this.plugin = plugin;
        }
    }

}
