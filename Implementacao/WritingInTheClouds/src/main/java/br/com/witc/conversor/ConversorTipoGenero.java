/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.conversor;

import br.com.witc.modelo.TipoGenero;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author 10070129
 */
@FacesConverter(forClass=TipoGenero.class)
public class ConversorTipoGenero implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if((string!=null)&&(!string.isEmpty())){
            return (TipoGenero) uic.getAttributes().get(string);
        }
       return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o instanceof TipoGenero){
            TipoGenero tipoGenero = (TipoGenero) o;
            uic.getAttributes().put(String.valueOf(tipoGenero.getId()), tipoGenero);
            return String.valueOf(tipoGenero.getId());
        }
        return "";
    }
    
}
