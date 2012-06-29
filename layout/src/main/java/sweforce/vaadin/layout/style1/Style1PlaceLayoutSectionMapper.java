package sweforce.vaadin.layout.style1;

import sweforce.gui.ap.place.Place;
import sweforce.vaadin.layout.LayoutSection;
import sweforce.vaadin.layout.activity.LayoutSectionActivityMapper;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 4/20/12
 * Time: 4:35 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Style1PlaceLayoutSectionMapper implements LayoutSectionActivityMapper.PlaceLayoutSectionMapper {

    @Override
    public abstract boolean isLayoutSectionInUseByPlace(LayoutSection layoutSection, Place place);

}
