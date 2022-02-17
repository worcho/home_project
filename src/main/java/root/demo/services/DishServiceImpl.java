package root.demo.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import root.demo.entities.Dish;
import root.demo.models.binding.DishCreationBindingModel;
import root.demo.models.service.DishForUserServiceModel;
import root.demo.models.service.DishServiceModel;
import root.demo.repositories.DishRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl implements DishService{

    private final DishRepository dishRepository;
    private final ModelMapper modelMapper;

    public DishServiceImpl(DishRepository dishRepository, ModelMapper modelMapper) {
        this.dishRepository = dishRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createDish(DishCreationBindingModel model) {
        Dish dish = modelMapper.map(model,Dish.class);
        dishRepository.save(dish);
    }

    @Override
    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }

    @Override
    public void editDish(DishCreationBindingModel model, Long id) {
        Dish dish = dishRepository.getById(id);
        dish.setName(model.getName());
        dish.setDescription(model.getDescription());
        dish.setUrl(model.getUrl());
        dishRepository.save(dish);
    }

    @Override
    public List<DishForUserServiceModel> getAllDishesFromRestaurant(Long id) {
        return dishRepository.getAllDishesFromRestaurant(id)
                .stream()
                .map(dish -> modelMapper.map(dish, DishForUserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DishServiceModel> getAllDishes() {
        return this.dishRepository.findAll()
                .stream()
                .map(dish -> modelMapper.map(dish, DishServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Dish findDishById(Long id) {
        return this.dishRepository.getById(id);
    }
}
