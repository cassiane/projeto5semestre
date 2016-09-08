/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.bean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author cstads
 */
@FacesConverter
public class ConvidarConverter {
//    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
//        if(value != null && value.trim().length() > 0) {
//            try {
//                ThemeService service = (ThemeService) fc.getExternalContext().getApplicationMap().get("themeService");
//                return service.getThemes().get(Integer.parseInt(value));
//            } catch(NumberFormatException e) {
//                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
//            }
//        }
//        else {
//            return null;
//        }
//    }
// 
//    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
//        if(object != null) {
//            return String.valueOf(((Theme) object).getId());
//        }
//        else {
//            return null;
//        }
//    }
}
