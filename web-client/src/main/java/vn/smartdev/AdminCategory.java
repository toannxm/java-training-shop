package vn.smartdev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.smartdev.category.dao.entity.Category;
import vn.smartdev.category.manager.CategoryServices;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by bink on 29-Nov-16.
 */
@Controller
@RequestMapping(value = "/admin", method = RequestMethod.GET)
public class AdminCategory {

    @Autowired
    private CategoryServices categoryServices;

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String showAll(Model model)
    {
        List<Category> categoryList = categoryServices.getListCategory();
        for(Category category : categoryList){
            System.out.println(category.getId());
        }
        model.addAttribute("categoryList", categoryList);
        return "category";
    }

    @RequestMapping(value = "/newCategory", method = RequestMethod.GET)
    public String newCategory(Model model)
    {
        Category category = new Category();
        model.addAttribute("category", category);

        return "category";
    }

    @RequestMapping(value = "/saveCategory", method = RequestMethod.POST)
    public String saveCategory(@RequestParam("categoryName") String categoryName){

        Category category = new Category();
        category.setCategoryName(categoryName);
        categoryServices.saveCategory(category);

        return "redirect:category";
    }

    @RequestMapping(value = "/deleteCategory", method = RequestMethod.GET)
    public String deleteCategory(@RequestParam("id") String categoryId){
        categoryServices.deleteCategory(categoryId);
        return "redirect:category";
    }

}
