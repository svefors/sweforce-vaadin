package sweforce.vaadin.table.editor;

import sweforce.event.EventNotifier;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 11/1/12
 * Time: 2:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class EditorViewModel {

//    public EventNotifier<?>
//    public ItemSetChangeNotifier
    /*
    class EditableGridFieldFactory(val cellsBeingEdited : Set[EditableCellModel], val inner: TableFieldFactory) extends TableFieldFactory {

  def createField(container: Container, itemId: Any, propertyId: Any, uiContext: Component) = {
    if (cellsBeingEdited.contains(EditableCellModel(itemId, propertyId))){
      inner.createField(container, itemId, propertyId, uiContext)
    } else {
      null
    }
  }
}

how to build the component

table needs a container.


 something(table?) should listen to changes in the open cells.

 change in cells that are being edited should trigger a redraw (table.refreshRowCache)

 changes in which cell is being focus() should change the keyboard


decorate(table)



     */
}
