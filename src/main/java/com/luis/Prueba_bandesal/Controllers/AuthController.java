package com.luis.Prueba_bandesal.Controllers;

import com.luis.Prueba_bandesal.Entity.User;
import com.luis.Prueba_bandesal.Services.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Controller
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/login")
    public String login(Authentication auth) {
        if (auth == null) {

            return "login";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping(value = "/save_register")
    public String saveRegister(@ModelAttribute("user") @Valid User user, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", user);

            return "redirect:/register";
        }

        try {
            Optional<User> userByUsername = this.userService.findUserByUsername(user.getUsername());

            if (userByUsername.isPresent()) {
                redirectAttributes.addFlashAttribute("user", user);

                redirectAttributes.addFlashAttribute("msg", "Usuario ya existe");

                return "redirect:/register";
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            this.userService.saveUser(user);

            User newUser = this.userService.saveUser(user);

            if (Objects.equals(newUser, null)) {
                redirectAttributes.addAttribute("user", user);

                redirectAttributes.addFlashAttribute("msg", "Error al guardar");

                return "redirect:/register";
            }

        } catch (Exception ex) {
            log.error(String.valueOf(ex.getCause()));
        }

        redirectAttributes.addFlashAttribute("msg", "Registro exitoso. Ahora puedes iniciar sesión.");

        return "redirect:/login";
    }
}