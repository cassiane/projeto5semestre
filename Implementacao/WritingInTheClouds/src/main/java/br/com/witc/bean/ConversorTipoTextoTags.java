/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.excessao.TipoTextoException;
import br.com.witc.modelo.TipoTexto;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Cassiane
 */
@FacesConverter("conversorTipoTextoTags")
public class ConversorTipoTextoTags  implements Converter  {
    
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
            uic.getAttributes().put(tipoTexto.getTipoTexto(), tipoTexto);
            return tipoTexto.getTipoTexto();            
        }
        return "";
    }
}
    