package sweforce.vaadin.layout.activity;

import sweforce.gui.ap.activity.Activity;
import sweforce.gui.ap.activity.ActivityMapper;
import sweforce.gui.ap.place.Place;
import sweforce.vaadin.layout.LayoutSection;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 4/20/12
 * Time: 12:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class LayoutSectionActivityMapper<LS extends LayoutSection> implements ActivityMapper{

    private LS layoutSection;

    private PlaceLayoutSectionMapper placeLayoutSectionMapper;

    private ActivityMapper activityMapper;

    public LayoutSectionActivityMapper(LS layoutSection, PlaceLayoutSectionMapper placeLayoutSectionMapper, ActivityMapper activityMapper) {
        this.layoutSection = layoutSection;
        this.placeLayoutSectionMapper = placeLayoutSectionMapper;
        this.activityMapper = activityMapper;
    }

    /**
     * Can not return null!
     *
     * @param place a Place object
     * @return
     */
    @Override
    public Activity getActivity(Place place) {
        if (!shouldHaveActivityForPlace(place))
            return null;
        return activityMapper.getActivity(place);
    }

    /**
     *
     * @param place
     * @return
     */
    protected boolean shouldHaveActivityForPlace(Place place){
        return placeLayoutSectionMapper.isLayoutSectionInUseByPlace(layoutSection, place);
    }


    public static interface PlaceLayoutSectionMapper {
        public boolean isLayoutSectionInUseByPlace(LayoutSection layoutSection, Place place);
    }

}
