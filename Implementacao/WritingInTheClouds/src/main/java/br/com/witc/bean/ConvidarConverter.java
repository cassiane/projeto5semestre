/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import br.com.witc.modelo.Usuario;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author cstads
 */
@FacesConverter("convidarConverter")
public class ConvidarConverter implements Converter{
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                //ThemeService service = (ThemeService) fc.getExternalContext().getApplicationMap().get("themeService");
                CadastrarBean cadastrar = (CadastrarBean) fc.getExternalContext().getApplicationMap().get("cadastrarBean");
                //return service.getThemes().get(Integer.parseInt(value));
                return cadastrar.getSolicitacao().get(Integer.parseInt(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
    }
 
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Usuario) object).getId());
        }
        else {
            return null;
        }
    }
}
