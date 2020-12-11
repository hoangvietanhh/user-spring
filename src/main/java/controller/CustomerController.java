package controller;

import entities.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import services.CustomerServiceImpl;

import java.util.List;

@Controller
public class CustomerController {
    private CustomerServiceImpl customerService = new CustomerServiceImpl();

    @GetMapping("/")
    public String index(Model model) {

        List customerList = customerService.findAll();
        model.addAttribute("customers", customerList);
        return "/index";
    }

    @GetMapping("/customers/create")
    public String create(Model model) {
        model.addAttribute("customer", new Customer());
        return "/create";
    }

    @PostMapping("/customers/create")
    public String create(Customer customer) {
        customer.setId((int)(Math.random() * 10000));
        customerService.save(customer);
        return "redirect:/";
    }

    @GetMapping("/customers/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/edit";
    }

    @PostMapping("/customers/update")
    public String update(Customer customer) {
        customerService.update(customer.getId(), customer);
        return "redirect:/";
    }

    @GetMapping("/customers/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/delete";
    }

    @PostMapping("/customers/delete")
    public String delete(Customer customer, RedirectAttributes redirect) {
        customerService.remove(customer.getId());
        redirect.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/";
    }

    @GetMapping("/customers/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/view";
    }
}
