package sweforce.vaadin.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 10/17/12
 * Time: 10:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class HierarchicalMessageResolver implements MessageResolver{

    /**
     * first look to see if there is a message bundle on the local level. I.e. if the hasI18NKey is a class
     * called MyButton, if there is a MyButton.properties
     * @param hasI18nKey
     * @param locale
     * @return
     */
    @Override
    public String getMessage(HasI18nKey hasI18nKey, Locale locale) {
        if (hasI18nKey == null){
            return null;
        }else{

//            ResourceBundle.getBundle(hasI18nKey.getClass().getSimpleName())
        }
//        ResourceBundle
        return null;
    }
}
