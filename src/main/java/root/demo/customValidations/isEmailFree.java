package root.demo.customValidations;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = isEmailFreeValidator.class)
public @interface isEmailFree {
    String message() default "Invalid Email.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
