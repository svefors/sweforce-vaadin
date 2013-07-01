package sweforce.gui.region;


import com.google.inject.BindingAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * Marker interface for region
 */
public interface Region {

    /**
     * some functions may rely on a jvm unique String. Don't override and you will be fine.
     *
     * @return
     */
    public String toString();

    @BindingAnnotation
    @Target({FIELD, PARAMETER, METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Regional {
        Class<? extends Region> value();
    }

}
