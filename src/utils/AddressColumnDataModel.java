package utils;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AddressColumnDataModel
{
    private final StringProperty column1 = new SimpleStringProperty();
    private final StringProperty column2 = new SimpleStringProperty();
    private final StringProperty column3 = new SimpleStringProperty();
    private final StringProperty column4 = new SimpleStringProperty();

    public AddressColumnDataModel(String col1, String col2, String col3, String col4)
    {
        this.column1.setValue(col1);
        this.column2.setValue(col2);
        this.column3.setValue(col3);
        this.column4.setValue(col4);
    }
    public StringProperty column1Property() {return column1;}
    public StringProperty column2Property() {return column2;}
    public StringProperty column3Property() {return column3;}
    public StringProperty column4Property() {return column4;}

    public String[] toVetString()
    {
        String[] result = {column1.getValue(), column2.getValue(), column3.getValue(), column4.getValue()};
        return result;
    }
}
