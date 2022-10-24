/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nacho
 */
public class Cliente extends Persona {
    private String codigo;
    private int id_genero;
    private Conexion cn;
    
    public Cliente() {}

    public Cliente(String codigo, int id_genero, int id, String nombres, String apellidos, String nit, String telefono, String correo_electronico, String fecha_ingreso) {
        super(id, nombres, apellidos, nit, telefono, correo_electronico, fecha_ingreso);
        this.codigo = codigo;
        this.id_genero = id_genero;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getId_genero() {
        return id_genero;
    }

    public void setId_genero(int id_genero) {
        this.id_genero = id_genero;
    }
     
    public DefaultTableModel leer(){
    DefaultTableModel tabla  = new DefaultTableModel();
    try{
        cn = new Conexion();
        cn.abrir_conexion();
        String query = "SELECT e.id_cliente as id, e.codigo, e.nombres, e.apellidos, e.nit, e.telefono, e.correo_electronico, e.fecha_ingreso, g.genero, g.id_genero FROM clientes as e inner join genero as g on e.id_genero = g.id_genero;";
        ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
        String encabezado[] =  {"id","codigo","nombres", "apellidos", "nit", "telefono", "correo_electronico", "fecha_ingreso", "genero", "id_genero"};
        tabla.setColumnIdentifiers(encabezado);
        String datos []= new String [10];
        while (consulta.next()){
        datos[0] = consulta.getString("id");
        datos[1] = consulta.getString("codigo");
        datos[2] = consulta.getString("nombres");
        datos[3] = consulta.getString("apellidos");
        datos[4] = consulta.getString("nit");
        datos[5] = consulta.getString("telefono");
        datos[6] = consulta.getString("correo_electronico");
        datos[7] = consulta.getString("fecha_ingreso");
        datos[8] = consulta.getString("genero");
        datos[9] = consulta.getString("id_genero");
        tabla.addRow(datos);
        }
        cn.cerrar_conexion();
    }catch(SQLException ex){
       System.out.println(ex.getMessage());
    }    
    return tabla;
    }
    
    @Override
    public int agregar(){
        int retorno = 0;
    try{
        PreparedStatement parametro;    
        cn=new Conexion();
        String query="INSERT INTO clientes(codigo,nombres,apellidos,nit,telefono,correo_electronico,fecha_ingreso,id_genero) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        cn.abrir_conexion();
        parametro = (PreparedStatement)cn.conexionDB.prepareStatement(query);
        parametro.setString(1, getCodigo());
        parametro.setString(2, getNombres());
        parametro.setString(3, getApellidos());
        parametro.setString(4, getNit());
        parametro.setString(5, getTelefono());
        parametro.setString(6, getCorreo_electronico());
        parametro.setString(7, getFecha_ingreso());
        parametro.setInt(8, getId_genero());
        retorno = parametro.executeUpdate();
        cn.cerrar_conexion();
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
        retorno = 0;
    }   
    return retorno;
    }
    
    @Override
    public int modificar(){
        int retorno = 0;
    try{
        PreparedStatement parametro;    
        cn=new Conexion();
        String query="UPDATE clientes set codigo=?, nombres=?, apellidos=?, nit=?, telefono=?, correo_electronico=?, fecha_ingreso=?, id_genero=? where id_cliente=?;";
        cn.abrir_conexion();
        parametro = (PreparedStatement)cn.conexionDB.prepareStatement(query);
        parametro.setString(1, getCodigo());
        parametro.setString(2, getNombres());
        parametro.setString(3, getApellidos());
        parametro.setString(4, getNit());
        parametro.setString(5, getTelefono());
        parametro.setString(6, getCorreo_electronico());
        parametro.setString(7, getFecha_ingreso());
        parametro.setInt(8, getId_genero());
        parametro.setInt(9, getId());
        retorno = parametro.executeUpdate();
        cn.cerrar_conexion();
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
        retorno = 0;
    }   
    return retorno;
    }
    
    @Override
    public int eliminar(){
        int retorno = 0;
    try{
        PreparedStatement parametro;    
        cn=new Conexion();
        String query="DELETE FROM clientes where id_cliente=?;";
        cn.abrir_conexion();
        parametro = (PreparedStatement)cn.conexionDB.prepareStatement(query);
        parametro.setInt(1, getId());
        retorno = parametro.executeUpdate();
        cn.cerrar_conexion();
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
        retorno = 0;
    }   
    return retorno;
    }
    
    
}
