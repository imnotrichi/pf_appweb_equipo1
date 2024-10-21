package org.itson.aplicacionesweb.themusichub.entidades;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.itson.aplicacionesweb.themusichub.entidades.Estado;
import org.itson.aplicacionesweb.themusichub.entidades.Usuario;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-10-21T01:20:15", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Municipio.class)
public class Municipio_ { 

    public static volatile SingularAttribute<Municipio, Estado> estado;
    public static volatile SingularAttribute<Municipio, Usuario> usuario;
    public static volatile SingularAttribute<Municipio, Long> id;
    public static volatile SingularAttribute<Municipio, String> nombre;

}