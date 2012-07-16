package sweforce.gui.display;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/9/12
 * Time: 6:17 PM
 * To change this template use File | Settings | File Templates.
 */
public interface VaadinView extends View{

    public Component asComponent();

    public static class Util{
        public static Component convertViewToComponent(View view){
            if (view instanceof VaadinView){
                return ((VaadinView) view).asComponent();
            }else if (NullView.getInstance().equals(view)){
                return new Label();
            }else{
                return null;
            }
        }
    }
}
