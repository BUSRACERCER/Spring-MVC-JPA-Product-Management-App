package com.works.mvcdata.controllers;

import com.works.mvcdata.entities.Product;
import com.works.mvcdata.services.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {
    int status = -1;
    String message = "";
    int uid = 0;
    SearchService service = new SearchService();

    @GetMapping("/search")

    public String search(@RequestParam(defaultValue = "") String q, Model model, @RequestParam(defaultValue = "1") int p) {

        List<Product> ls = service.search(q);
        model.addAttribute("product", ls);

        model.addAttribute("q", q);
        model.addAttribute("product", service.products(p));
        model.addAttribute("p", p);
        int count = service.totalCount();
        model.addAttribute("count", count);
        int page = count % 3 == 0 ? count / 3 : (count / 3) + 1;
        model.addAttribute("page", page);


        return "search";
    }

    @GetMapping("/userInfo/{pid}")
    public String userInfo(@PathVariable int pid, Model model) {
        Product u = service.single(pid);
        model.addAttribute("product", u);
        return "userInfo";
    }

    @GetMapping("/imageDelete/{pid}")
    public String imageDelete(@PathVariable int pid) {
        System.out.println("useDelete Call" + pid);
        status = service.deleteImage(pid);
        if (status > 0) {
            message = "Delete Succsess - " + pid;
            this.uid = pid;

        } else {
            message = "Delete Fail - " + pid;
        }

        return "redirect:/search";
    }


}
