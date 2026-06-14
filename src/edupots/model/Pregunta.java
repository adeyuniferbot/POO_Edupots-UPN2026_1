/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.model;

/**
 *
 * @author camil
 */
public class Pregunta {
// ========================================================= 
// ATRIBUTOS 
// =========================================================
    private String idPregunta; 
    private String enunciado; 
    private String opcionA; 
    private String opcionB; 
    private String opcionC; 
    private String opcionD;
    
    private String respuestaCorrecta;
    
    public Pregunta() { 
    } 
    public Pregunta(String idPregunta, String enunciado, 
            String opcionA, String opcionB, 
            String opcionC, String opcionD, 
            String respuestaCorrecta) { 
        this.idPregunta = idPregunta; 
        this.enunciado = enunciado; 
        this.opcionA = opcionA; 
        this.opcionB = opcionB; 
        this.opcionC = opcionC; 
        this.opcionD = opcionD; 
        this.respuestaCorrecta = respuestaCorrecta; 
    }
    public boolean validarRespuesta(String respuestaDada) { 
        if (respuestaDada == null || respuestaDada.isEmpty()) { 
            return false; 
        } 
        return this.respuestaCorrecta.equalsIgnoreCase(respuestaDada.trim()); 
    }
    public String getTextoRespuestaCorrecta() { 
        switch (respuestaCorrecta.toUpperCase()) { 
            case "A": return "A) " + opcionA; 
            case "B": return "B) " + opcionB; 
            case "C": return "C) " + opcionC; 
            case "D": return "D) " + opcionD; 
            default: return "Respuesta no definida"; 
        } 
    }
    @Override 
    public String toString() { 
        return "Pregunta{" 
                + "id='" + idPregunta + '\'' 
                + ", enunciado='" + enunciado + '\'' 
                + ", correcta='" + respuestaCorrecta + '\'' 
                + '}'; 
    }
    public String getIdPregunta() { return idPregunta; } 
    public void setIdPregunta(String idPregunta) { 
        this.idPregunta = idPregunta; 
    } 
    public String getEnunciado() { return enunciado; } 
    public void setEnunciado(String enunciado) { 
        this.enunciado = enunciado; 
    } 
    public String getOpcionA() { return opcionA; } 
    public void setOpcionA(String opcionA) { this.opcionA = opcionA; } 
    
    public String getOpcionB() { return opcionB; } 
    public void setOpcionB(String opcionB) { this.opcionB = opcionB; } 
    
    public String getOpcionC() { return opcionC; } 
    public void setOpcionC(String opcionC) { this.opcionC = opcionC; } 
    
    public String getOpcionD() { return opcionD; } 
    public void setOpcionD(String opcionD) { this.opcionD = opcionD; } 
    
    public String getRespuestaCorrecta() { return respuestaCorrecta; } 
    public void setRespuestaCorrecta(String respuestaCorrecta) { 
        this.respuestaCorrecta = respuestaCorrecta; 
    }
}
