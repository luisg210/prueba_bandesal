package com.luis.Prueba_bandesal.Controllers;

import com.luis.Prueba_bandesal.Entity.Reader;
import com.luis.Prueba_bandesal.Services.Interface.ReaderServiceInterface;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;
import java.util.Optional;

@Log4j2
@Controller
@RequestMapping(value = "/readers")
public class ReaderController {

    @Autowired
    private ReaderServiceInterface readerService;

    @GetMapping(value = {"/", "list", "home"})
    public String listsReaders(Model model) {

        model.addAttribute("readers", this.readerService.findAllReaders());

        return "readers";
    }

    @GetMapping(value = "/add_reader")
    public String addReader(Model model) {

        model.addAttribute("reader", new Reader());

        return "add_reader";
    }

    @PostMapping(value = "/save_reader")
    public String saveReader(@ModelAttribute("reader") @Valid Reader reader, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("reader", reader);

            return "add_reader";
        }

        try {
            Reader newReader = this.readerService.saveReader(reader);

            if (Objects.equals(newReader, null)) {
                model.addAttribute("reader", reader);

                redirectAttributes.addFlashAttribute("msg", "Error al guardar");

                return "add_reader";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(String.valueOf(ex.getCause()));
        }

        redirectAttributes.addFlashAttribute("msg", "Guardado con exito");

        return "redirect:/readers/list";
    }

    @GetMapping(value = "/update_reader/{id}")
    public String updateReader(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {

        Optional<Reader> readerById = this.readerService.findReaderById(id);

        if (readerById.isEmpty()) {
            redirectAttributes.addFlashAttribute("msg", "Reader no encontrado");

            return "redirect:/reader/list";
        }

        model.addAttribute("reader", readerById.get());

        return "add_reader";
    }

    @GetMapping(value = "/delete_reader/{id}")
    public String deleteReader(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Reader> readerById = this.readerService.findReaderById(id);

        if (readerById.isEmpty()) {
            redirectAttributes.addFlashAttribute("msg", "Reader no encontrado");

        } else {

            try {
                this.readerService.deleteReaderById(id);

                redirectAttributes.addFlashAttribute("msg", "Reader eliminado con exito");

            } catch (Exception ex) {
                ex.printStackTrace();
                log.error(ex.getCause(), ex.getCause());

                redirectAttributes.addFlashAttribute("msg", "Ocurrio un error");
            }
        }

        return "redirect:/readers/list";
    }

    public static class BlogController {



    }
}
