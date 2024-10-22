package org.itson.aplicacionesweb.themusichub.entidades;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.itson.aplicacionesweb.themusichub.entidades.Comentario;
import org.itson.aplicacionesweb.themusichub.entidades.Usuario;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-10-21T19:49:42", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Comun.class)
public class Comun_ extends Post_ {

    public static volatile SingularAttribute<Comun, Usuario> usuario;
    public static volatile ListAttribute<Comun, Comentario> comentarios;

}