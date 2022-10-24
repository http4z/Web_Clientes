/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
/**
 *
 * @author nacho
 */
public class Genero {
    private int id_genero;
    private String genero;
    private Conexion cn;
    
    public Genero(){}
    public Genero(int id_genero, String genero) {
        this.id_genero = id_genero;
        this.genero = genero;
    }

    public int getId_genero() {
        return id_genero;
    }

    public void setId_genero(int id_genero) {
        this.id_genero = id_genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public HashMap drop_genero(){
        HashMap<String, String> drop=new HashMap();
        try{
            cn=new Conexion();
            String query = "SELECT id_genero as id, genero FROM genero;";
             cn.abrir_conexion();
             ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
             while (consulta.next()){
                 drop.put(consulta.getString("id"), consulta.getString("genero"));
             }
             cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return drop;
    }  
    
}
