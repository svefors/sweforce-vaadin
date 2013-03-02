package sweforce.gui.ap.place;

import com.google.inject.AbstractModule;
import sweforce.gui.ap.place.history.PlaceTokenizerStore;
import sweforce.gui.ap.place.token.PlaceTokenizer;
import sweforce.gui.ap.place.token.Prefix;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/8/12
 * Time: 3:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlacesModule extends AbstractModule {

    private Map<String, PlaceTokenizer<? extends Place>> tokenizerMap =
            new HashMap<String, PlaceTokenizer<? extends Place>>();

    public PlaceTokenizerStore providePlaceTokenizerStore() {
        return new PlaceTokenizerStore() {
            @Override
            public PlaceTokenizer getTokenizer(String prefix) {
                return tokenizerMap.get(prefix);
            }
        };
    }


    protected void registerPlaceTokenizer(String prefix, PlaceTokenizer<? extends Place> tokenizer) {
        tokenizerMap.put(prefix, (PlaceTokenizer<? extends Place>) tokenizer);
    }

    protected void registerPlaceTokenizer(String prefix, Class<? extends PlaceTokenizer<? extends Place>> tokenizer) {
        try {
            registerPlaceTokenizer(prefix, tokenizer.newInstance());
        } catch (InstantiationException ie) {
            throw new IllegalArgumentException(ie);
        } catch (IllegalAccessException ie) {
            throw new IllegalArgumentException(ie);
        }
    }

    protected <P extends Place> void addPlaceTokenizer(Class<? extends PlaceTokenizer<P>> placeTokenizerClass){
        try {
            Method method = placeTokenizerClass.getMethod("getPlace", String.class);
            if (method.getReturnType().isAnnotationPresent(Prefix.class)) {
                Prefix prefixAnnotation = method.getReturnType().getAnnotation(Prefix.class);
                String prefix = prefixAnnotation.value();
                registerPlaceTokenizer(prefix, placeTokenizerClass);
            } else {
                throw new IllegalArgumentException("can't find prefix annotation: " + placeTokenizerClass);
            }

        } catch (NoSuchMethodException nsme) {
            throw new IllegalArgumentException("can't find method getPlace on Tokenizer: " + placeTokenizerClass);
        }
    }

    /**
     * @param placeClass
     */
    protected void registerPlaceTokenizer(Class<? extends Place> placeClass) {
        Class declaredClasses[] = placeClass.getDeclaredClasses();

        for (Class declaredClass : declaredClasses) {
            if (PlaceTokenizer.class.isAssignableFrom(declaredClass)) {
                addPlaceTokenizer(declaredClass);
            }
        }
    }



    @Override
    protected void configure() {
        bind(PlacesRunner.class);

    }



}
