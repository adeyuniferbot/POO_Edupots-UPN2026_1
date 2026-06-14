/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edupots.controller;
import edupots.dao.ResultadoDAO; 
import edupots.dao.UsuarioDAO; 
import edupots.model.Insignia;
import edupots.model.Progreso; 
import edupots.model.Resultado; 
import edupots.model.Estudiante; 
import edupots.model.Usuario; 
import java.util.ArrayList;
/**
 *
 * @author camil
 */
public class ProgresoController {
    private ResultadoDAO resultadoDAO;
    private UsuarioDAO usuarioDAO;
    
    public ProgresoController() { 
        this.resultadoDAO = new ResultadoDAO(); 
        this.usuarioDAO = new UsuarioDAO(); 
    }
    public Progreso obtenerProgreso(String idEstudiante) { 
        Progreso progreso = new Progreso("PRG_" + idEstudiante, 
                idEstudiante);
        
        ArrayList<Resultado> resultados = 
                resultadoDAO.leerPorEstudiante(idEstudiante);
        
        int puntajeAcumulado = 0; 
        for (Resultado r : resultados) { 
            puntajeAcumulado += r.getPuntaje();
            progreso.registrarQuizPorTema(r.getTituloQuiz());
        }
        progreso.setQuizzesResueltos(resultados.size());
        progreso.setPuntajeAcumulado(puntajeAcumulado);
        
        if (!resultados.isEmpty()) { 
            progreso.setPromedio( 
                    (double) puntajeAcumulado / resultados.size());
        }
        progreso.actualizarProgreso();
        
        ArrayList<Insignia> insignias =
                verificarInsignias(idEstudiante, resultados,
                        puntajeAcumulado);
        progreso.setInsignias(insignias);
        return progreso; 
    }
    public ArrayList<Insignia> actualizarTrasQuiz(String idEstudiante, 
            int puntajeObtenido) { 
        ArrayList<Resultado> resultados = 
                resultadoDAO.leerPorEstudiante(idEstudiante);
        int puntajeAcumulado = 0; 
        for (Resultado r : resultados) {
            puntajeAcumulado += r.getPuntaje();
        }
        int quizzesCompletados = resultados.size();
        
        return verificarInsigniasNuevas(quizzesCompletados, 
                puntajeAcumulado, puntajeObtenido); 
    }
    private ArrayList<Insignia> verificarInsignias( 
            String idEstudiante, 
            ArrayList<Resultado> resultados, 
            int puntajeAcumulado) { 
        
        Insignia[] base = Insignia.crearInsigniasBase();
        ArrayList<Insignia> lista = new ArrayList<>();
        
        int quizzesCompletados = resultados.size();
        int mejorPuntaje = 0; 
        for (Resultado r : resultados) { 
            if (r.getPuntaje() > mejorPuntaje) { 
                mejorPuntaje = r.getPuntaje();
        } 
    }
        for (Insignia ins : base) { 
            ins.verificarLogro(quizzesCompletados, 
                    puntajeAcumulado, mejorPuntaje); 
            lista.add(ins); 
        } 
        return lista; 
    }
    private ArrayList<Insignia> verificarInsigniasNuevas( 
            int quizzesCompletados, 
            int puntajeAcumulado,
            int puntajeUltimoQuiz) {
        
        ArrayList<Insignia> nuevas = new ArrayList<>();
        Insignia[] base = Insignia.crearInsigniasBase(); 
        
        for (Insignia ins : base) { 
            boolean recienObtenida = ins.verificarLogro( 
                    quizzesCompletados,
                    puntajeAcumulado, 
                    puntajeUltimoQuiz);
            if (recienObtenida) { 
                nuevas.add(ins); 
            } 
        } 
        return nuevas;
    }
    public String[] obtenerEstadisticas(String idEstudiante) { 
        int quizzes = resultadoDAO.contarQuizzes(idEstudiante);
        double promedio = resultadoDAO.calcularPromedio(idEstudiante);
        int mayor = resultadoDAO.obtenerMayorPuntaje(idEstudiante);
        int menor = resultadoDAO.obtenerMenorPuntaje(idEstudiante);
        
        return new String[]{
            String.valueOf(quizzes),
            String.format("%.1f", promedio),
            String.valueOf(mayor),
            String.valueOf(menor) 
        }; 
    }
    public Object[][] obtenerDatosParaTabla(String idEstudiante) { 
        ArrayList<Resultado> resultados = 
                resultadoDAO.leerPorEstudiante(idEstudiante);
        
        Object[][] datos = new Object[resultados.size()][6];
        
        for (int i = 0; i < resultados.size(); i++) {
            Resultado r = resultados.get(i); 
            datos[i][0] = r.getTituloQuiz(); 
            datos[i][1] = r.getFecha();
            datos[i][2] = r.getPuntaje() + "/100";
            datos[i][3] = r.getCorrectas(); 
            datos[i][4] = r.getIncorrectas(); 
            datos[i][5] = r.getNivelAlcanzado(); 
        } 
        return datos; 
    }
    public ResultadoDAO getResultadoDAO() { return resultadoDAO; } 
    public UsuarioDAO getUsuarioDAO() { return usuarioDAO; }
}