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

    @GetMapping ("/roll-dice/guess")
    public String guessResults() {

        return "dice-results";
    }

    @PostMapping("/roll-dice/guess")
    public String showGuessResults(@RequestParam(name = "userGuess") String guess, Model model) {
        System.out.println(guess);
        Random rand = new Random();
        int diceRoll1 = rand.nextInt(5) + 1;
        int diceRoll2 = rand.nextInt(5) + 1;
        int diceRoll3 = rand.nextInt(5) + 1;
        int diceRoll4 = rand.nextInt(5) + 1;
        int diceTotal = diceRoll1 + diceRoll2 + diceRoll3 + diceRoll4;
        model.addAttribute("userGuess", guess);
        model.addAttribute("diceTotal", diceTotal);

        return "dice-results";
    }
}
