package root.demo.customValidations;

import root.demo.repositories.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class isEmailFreeValidator implements ConstraintValidator<isEmailFree, String> {

    private final UserRepository userRepository;

    public isEmailFreeValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(isEmailFree constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if (userRepository.findByEmail(email) == null){
            return true;
        }else {
            return false;
        }
    }
}
