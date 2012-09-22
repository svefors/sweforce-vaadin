package sweforce.vaadin.layout.display;

import com.vaadin.server.Sizeable;

/**
* Created by IntelliJ IDEA.
* User: sveffa
* Date: 7/16/12
* Time: 10:16 PM
* To change this template use File | Settings | File Templates.
*/
public class Size {

    private final float width;
    private final Sizeable.Unit widthUnit;
    private final float height;
    private final Sizeable.Unit heightUnit;

    public Size(float width, Sizeable.Unit widthUnit, float height, Sizeable.Unit heightUnit) {
        this.width = width;
        this.widthUnit = widthUnit;
        this.height = height;
        this.heightUnit = heightUnit;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Sizeable.Unit getWidthUnit() {
        return widthUnit;
    }

    public Sizeable.Unit getHeightUnit() {
        return heightUnit;
    }

    public static Size getSize(Sizeable sizeable){
        return new Size(sizeable.getWidth(), sizeable.getWidthUnits(), sizeable.getHeight(), sizeable.getHeightUnits());
    }

    public void setSizeOn(Sizeable sizeable){
        sizeable.setHeight(this.height, this.heightUnit);
        sizeable.setWidth(this.width, this.widthUnit);
    }

    @Override
    public String toString() {
        return "Size{" +
                "width=" + width +
                ", widthUnit=" + widthUnit +
                ", height=" + height +
                ", heightUnit=" + heightUnit +
                '}';
    }
}
