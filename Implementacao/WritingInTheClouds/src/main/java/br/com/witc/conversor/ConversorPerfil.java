/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.conversor;

import br.com.witc.modelo.Perfil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author marcelo.lima
 */
@FacesConverter(value="conversorPerfil")
public class ConversorPerfil implements Converter {
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if ((string != null) && (!string.isEmpty())) {
            return (Perfil) uic.getAttributes().get(string);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o instanceof Perfil) {
            Perfil perfil = (Perfil) o;            
            if (perfil.getTipoPerfil() != null) {
                uic.getAttributes().put(String.valueOf(perfil.getId()), perfil);
                return String.valueOf(perfil.getId());            
            }
        }
        return "";
    }
}