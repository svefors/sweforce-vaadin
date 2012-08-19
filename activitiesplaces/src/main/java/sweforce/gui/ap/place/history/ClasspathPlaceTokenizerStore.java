package sweforce.gui.ap.place.history;

import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 7/31/12
 * Time: 8:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClasspathPlaceTokenizerStore implements PlaceTokenizerStore{

    private final String[] packages;

    public ClasspathPlaceTokenizerStore(String[] packages) {
        this.packages = packages;
    }



    @Override
    public sweforce.gui.ap.place.token.PlaceTokenizer getTokenizer(String prefix) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


}
