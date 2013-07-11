package sweforce.vaadin.sample.secure;

import sweforce.gui.place.PrefixPlaceTokenizerRegistry;
import sweforce.vaadin.sample.secure.norole.NorolePlace;
import sweforce.vaadin.sample.secure.role1.Role1Place;
import sweforce.vaadin.sample.secure.role2.Role2Place;
import sweforce.vaadin.security.login.LoginPlace;
import sweforce.vaadin.security.logout.LogoutPlace;

/**
 * Created with IntelliJ IDEA.
 * User: msvefors
 * Date: 7/11/13
 * Time: 1:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceConfiguration implements PrefixPlaceTokenizerRegistry.Contributor {

    public PlaceConfiguration() {
    }

    @Override
    public void configure(PrefixPlaceTokenizerRegistry.PrefixPlaceTokenizerConfiguration<? extends PrefixPlaceTokenizerRegistry.PrefixPlaceTokenizerConfiguration> configuration) {
        configuration.prefix("role1").useTokenizer(new Role1Place.Tokenizer())
                .prefix("role2").useTokenizer(new Role2Place.Tokenizer())
                .prefix("norole").useTokenizer(new NorolePlace.Tokenizer())
                .prefix("login").useTokenizer(new LoginPlace.Tokenizer())
                .prefix("logout").useTokenizer(new LogoutPlace.Tokenizer());
    }
}
