/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.conversor;

import br.com.witc.modelo.DesafiosPalavras;
import br.com.witc.modelo.TipoTexto;
import br.com.witc.modelo.Usuario;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author marcelo.lima
 */
@FacesConverter(value="conversorDesafiosPalavras")
public class ConversorDesafiosPalavras implements Converter {
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if ((string != null) && (!string.isEmpty())) {
            return string;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o != null) {
            return String.valueOf(((DesafiosPalavras) o).getPalavra());
        }
        else {
            return null;
        }
    }
}