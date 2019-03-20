package VerteilterTerminKalender.view.model;

import javafx.beans.property.SimpleStringProperty;

public class EvenSimplerStringProperty extends SimpleStringProperty {


    public EvenSimplerStringProperty(String s){
        super(s);
    }

    @Override
    public String toString(){
        return this.getValue();
    }
}
