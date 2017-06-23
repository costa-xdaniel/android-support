package st.zudamue.support.android.sql.annnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * Created by dchost on 12/02/17.
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( { ElementType.TYPE })
public @interface Table {

    /**
     * The value
     * @return the table name
     */
    String value();

}
