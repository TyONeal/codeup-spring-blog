package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
public class DiceController {

    @GetMapping("/roll-dice")
    public String diceRoll() {
        return "roll-dice";
    }

    @GetMapping ("/roll-dice/{userGuess}")
    public String guessResults(@PathVariable int userGuess, Model model) {
        Random rand = new Random();
        int randomChoice = rand.nextInt(5) + 1;
        model.addAttribute("userGuess", userGuess);
        model.addAttribute("randomNumber", randomChoice);
        return "dice-results";
    }


}
