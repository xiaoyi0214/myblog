package com.shell845.myblog.web;

import com.shell845.myblog.po.Type;
import com.shell845.myblog.service.BlogService;
import com.shell845.myblog.service.TypeService;
import com.shell845.myblog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author ych
 * @date 21/4/2020 6:00 PM
 */

@Controller
public class TypeShowController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id, Model model) {
        List<Type> typeBlogs = typeService.listTypeTop(1000);
        if (id == -1) {
            id = typeBlogs.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types", typeBlogs);
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery, false));
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
