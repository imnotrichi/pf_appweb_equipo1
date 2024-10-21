package org.itson.aplicacionesweb.themusichub.entidades;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.itson.aplicacionesweb.themusichub.entidades.Municipio;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-10-21T01:20:15", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Estado.class)
public class Estado_ { 

    public static volatile ListAttribute<Estado, Municipio> municipio;
    public static volatile SingularAttribute<Estado, Long> id;
    public static volatile SingularAttribute<Estado, String> nombre;

}