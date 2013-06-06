package sweforce.gui.place;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 3/4/13
 * Time: 2:36 AM
 * To change this template use File | Settings | File Templates.
 */
@BindingAnnotation
@Target({FIELD, PARAMETER, METHOD})
@Retention(RetentionPolicy.RUNTIME)

public @interface DefaultPlace {
}
