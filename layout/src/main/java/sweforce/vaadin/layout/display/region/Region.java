package sweforce.vaadin.layout.display.region;


import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker interface for region
 */
public interface Region {

    /**
     * some functions may rely on a jvm unique String. Don't override and you will be fine.
     * @return
     */
    public String toString();


}
