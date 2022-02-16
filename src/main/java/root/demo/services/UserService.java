package root.demo.services;

import root.demo.models.binding.UserEditBindingModel;
import root.demo.models.binding.UserRegisterBindingModel;
import root.demo.models.service.UserServiceModel;

import java.util.List;

public interface UserService {

    void register(UserRegisterBindingModel model);

    void deleteUser(String id);

    boolean isPasswordsMatch(UserRegisterBindingModel model);

    List<UserServiceModel> getAllUsers();

    void editUser(UserEditBindingModel userEditBindingModel, String email);

    UserServiceModel getUserById(String email);


}
