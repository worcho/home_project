package root.demo.services;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import root.demo.entities.Orders;
import root.demo.entities.User;
import root.demo.models.binding.UserEditBindingModel;
import root.demo.models.binding.UserRegisterBindingModel;
import root.demo.models.service.UserServiceModel;
import root.demo.repositories.OrderRepository;
import root.demo.repositories.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final OrderRepository orderRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.orderRepository = orderRepository;
    }

    @Override
    public void register(UserRegisterBindingModel model) {
        User user = modelMapper.map(model, User.class);
        user.setPassword(passwordEncoder.encode(model.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) {
        List<Orders> ids = orderRepository.findByEmail(id);
        ArrayList<Long> list = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            list.add(ids.get(i).getId());
        }
        for (int i = 0; i < list.size(); i++) {
            orderRepository.deleteFromOrderDish(list.get(i));
        }
        for (int i = 0; i < list.size(); i++) {
            orderRepository.deleteFromOrders(list.get(i));
        }
        userRepository.deleteById(id);
    }

    @Override
    public boolean isPasswordsMatch(UserRegisterBindingModel model) {
        if (model.getPassword().equals(model.getConfirmPassword())){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<UserServiceModel> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user,UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void editUser(UserEditBindingModel userEditBindingModel, String email) {
        User user = userRepository.getById(email);
        user.setFirstName(userEditBindingModel.getFirstName());
        user.setLastName(userEditBindingModel.getLastName());
        user.setTelephone(userEditBindingModel.getTelephone());
        user.setAddress(userEditBindingModel.getAddress());
        userRepository.save(user);
    }

    @Override
    public UserServiceModel getUserById(String email) {
        return modelMapper.map(userRepository.findByEmail(email),UserServiceModel.class);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("user not found");
        }
        Set<GrantedAuthority> roles = new HashSet<>();
        if (user.getRole().equals("ADMIN")){
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        if (user.getRole().equals("USER")){
            roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                roles
        );
        return userDetails;
    }
}
