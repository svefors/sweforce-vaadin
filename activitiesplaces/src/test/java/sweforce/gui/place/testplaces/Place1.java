package sweforce.gui.place.testplaces;

import sweforce.gui.place.Place;
import sweforce.gui.place.PlaceTokenizer;
import sweforce.gui.place.Prefix;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 7/17/12
 * Time: 8:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class Place1 extends Place {
  public final String content;

  public Place1(String token) {
    this.content = token;
  }

  /**
   * Tokenizer.
   */
  @Prefix(Tokenizer.PREFIX)
  public static class Tokenizer implements PlaceTokenizer<Place1> {
    public static final String PREFIX = "T1";

    public Place1 getPlace(String token) {
      return new Place1(token);
    }

    public String getToken(Place1 place) {
      return place.content;
    }
  }
}
