/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2017.web.controladores;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.definiciones.Categoria;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso.CategoriaFacadeLocal;


/**
 *
 * @author kevin
 */
@Named
@ViewScoped
public class CategoriaBean implements Serializable {

    public CategoriaBean() {
    }
    boolean activo;

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    @EJB
    CategoriaFacadeLocal categoria;
     List<Categoria> lista= new ArrayList<>();
    Categoria cat =new Categoria();

    public CategoriaFacadeLocal getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaFacadeLocal categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getLista() {
        return lista;
    }

    public void setLista(List<Categoria> lista) {
        this.lista = lista;
    }

    public Categoria getCat() {
        return cat;
    }

    public void setCat(Categoria cat) {
        this.cat = cat;
    }
 public void crear(){
     try {
         categoria.create(cat);
         llenar();
         showMessage("Registro agregado");
         cat = new Categoria();
     } catch (Exception e) {
         System.out.println("Error: "+ e);
         showMessage("Error registro invalido");
     }
      
    }
  public void chkCambio(){
        if(activo == true){
            this.lista = obtenerUtilizados();
            
        }else{
            llenar();
            
        }
    }
  public List<Categoria> obtenerUtilizados() {
        List salida;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("uesocc.edu.sv.ingenieria.prn335_webproject3_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        Query c = em.createNamedQuery("Categoria.NotUse");
        salida = c.getResultList();
        
        if(salida != null){
        return salida;
        }else{
            return Collections.EMPTY_LIST;
        }
    }
    
    public void showMessage(String Mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(Mensaje));
    }
    
    public void limpiarbtn(){
    cat = new Categoria();
    
    }
    
    @PostConstruct
    public void llenar(){
        if(lista!= null){
            this.lista=categoria.findAll();
        }else {
            this.lista=Collections.EMPTY_LIST;
        }
    }
    
}
