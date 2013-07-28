package sweforce.gui.place;


import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 2/25/13
 * Time: 8:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceTokenizerUtil {

    /**
     * @param placeTokenizerClass
     * @param <P>                 extends Place
     * @return the value of @Prefix("asfd") or null if there is no annotaion present.
     * @throws IllegalArgumentException if there is no method getPlace
     */
    public static <P extends Place> String getPrefixAnnotationValue(Class<? extends PlaceTokenizer<P>> placeTokenizerClass) {
        try {
            Method method = placeTokenizerClass.getMethod("getPlace", String.class);
            if (method.getReturnType().isAnnotationPresent(Prefix.class)) {
                Prefix prefixAnnotation = method.getReturnType().getAnnotation(Prefix.class);
                String prefix = prefixAnnotation.value();
                return prefix;
            } else {
                return null;
//                throw new IllegalArgumentException("can't find prefix annotation: " + placeTokenizerClass);
            }
        } catch (NoSuchMethodException nsme) {
            //I don't think this can happen, providing the class extends/implements PlaceTokenizer
            throw new IllegalArgumentException("can't find method getPlace on Tokenizer: " + placeTokenizerClass);
        }
    }

    /**
     * @param placeTokenizerClass
     * @param <P>                 extends Place
     * @return the value of @Prefix("asfd") or null if there is no annotaion present.
     * @throws IllegalArgumentException if there is no method getPlace
     */
    public static <P extends Place> String getPrefixAnnotationValueFromPlaceClass(Class<P> placeTokenizerClass) {
            if (placeTokenizerClass.isAnnotationPresent(Prefix.class)) {
                Prefix prefixAnnotation = placeTokenizerClass.getAnnotation(Prefix.class);
                String prefix = prefixAnnotation.value();
                return prefix;
            } else {
                throw new IllegalArgumentException("no prefix annotation present!");
            }
    }

    /**
     * @param placeClass
     * @param <P>
     * @return the first place tokenizer class that is declared in placeClass, or null if there is none.
     */
    public static <P extends Place> Class<? extends PlaceTokenizer<P>> getDeclaredPlaceTokenizerClass(Class<P> placeClass) {
        Class declaredClasses[] = placeClass.getDeclaredClasses();
        for (Class declaredClass : declaredClasses) {
            if (PlaceTokenizer.class.isAssignableFrom(declaredClass)) {
                return declaredClass;
            }
        }
        return null;
    }


//    //MapBinder<String, Snack>  Map<String, PlaceTokenizer<? extends Place>>
//
//    public static <P extends Place> void bindPrefixMapping(Class<P> placeClass, MapBinder<String, PlaceTokenizer<? extends Place>> binder) {
//        Class<? extends PlaceTokenizer<P>> placeTokenizerClass = getDeclaredPlaceTokenizerClass(placeClass);
//        if (placeTokenizerClass != null) {
//            String prefix = getPrefixAnnotationValue(placeTokenizerClass);
////            try {
////                PlaceTokenizer placeTokenizer = placeTokenizerClass.newInstance();
//                //bindPrefixMapping to the instance
//                binder.addBinding(prefix).to(placeTokenizerClass);
////            } catch (InstantiationException ie) {
////                //bindPrefixMapping to the class. assume there is binding somewhere
////                binder.addBinding(prefix).to(placeTokenizerClass);
////            } catch (IllegalAccessException iae) {
////                //bindPrefixMapping to the class. assume there is binding somewhere
////                binder.addBinding(prefix).to(placeTokenizerClass);
////            }
//        }
//    }

}
