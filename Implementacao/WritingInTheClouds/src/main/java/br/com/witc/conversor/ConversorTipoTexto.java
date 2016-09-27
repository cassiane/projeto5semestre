/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.conversor;

import br.com.witc.modelo.TipoTexto;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author marcelo.lima
 */
@FacesConverter(forClass = TipoTexto.class)
public class ConversorTipoTexto implements Converter {
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if ((string != null) && (!string.isEmpty())) {
            return (TipoTexto) uic.getAttributes().get(string);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o instanceof TipoTexto) {
            TipoTexto tipoTexto = (TipoTexto) o;            
            uic.getAttributes().put(String.valueOf(tipoTexto.getId()), tipoTexto);
            return String.valueOf(tipoTexto.getId());            
        }
        return "";
    }
}
