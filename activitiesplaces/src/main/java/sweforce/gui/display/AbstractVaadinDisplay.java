package sweforce.gui.display;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/9/12
 * Time: 6:13 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractVaadinDisplay implements Display {

    @Override
    public void setView(View view) {
        if (NullView.getInstance() == view) {
            setVaadinView(null);
        }
        if(view ==null){
            setVaadinView(null);
        }
        if (! (view instanceof VaadinView)) {
            throw new IllegalArgumentException("view class: " + view.getClass() + " is not intanceof " + VaadinView.class);
        }
        setVaadinView((VaadinView) view);
    }

    protected abstract void setVaadinView(VaadinView view);
}
