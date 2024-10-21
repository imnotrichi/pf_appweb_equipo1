package org.itson.aplicacionesweb.themusichub.entidades;

import java.util.Calendar;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-10-21T00:39:30", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> apellidoPaterno;
    public static volatile SingularAttribute<Usuario, Calendar> fechaNacimiento;
    public static volatile SingularAttribute<Usuario, String> ciudad;
    public static volatile SingularAttribute<Usuario, String> correo;
    public static volatile SingularAttribute<Usuario, String> genero;
    public static volatile SingularAttribute<Usuario, byte[]> contrasenia;
    public static volatile SingularAttribute<Usuario, Long> id;
    public static volatile SingularAttribute<Usuario, String> avatar;
    public static volatile SingularAttribute<Usuario, byte[]> telefono;
    public static volatile SingularAttribute<Usuario, String> nombres;
    public static volatile SingularAttribute<Usuario, String> apellidoMaterno;

}