package org.itson.aplicacionesweb.themusichub.entidades;

import java.util.GregorianCalendar;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-10-21T00:54:15", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Post.class)
public class Post_ { 

    public static volatile SingularAttribute<Post, GregorianCalendar> fechaHoraEdicion;
    public static volatile SingularAttribute<Post, String> contenido;
    public static volatile SingularAttribute<Post, GregorianCalendar> fechaHoraCreacion;
    public static volatile SingularAttribute<Post, String> titulo;
    public static volatile SingularAttribute<Post, Long> id;

}