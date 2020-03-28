package hue.edu.xiong.volunteer_travel.controller;

import hue.edu.xiong.volunteer_travel.model.RecommedRoute;
import hue.edu.xiong.volunteer_travel.model.TravelRoute;
import hue.edu.xiong.volunteer_travel.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/recommend")
public class commend {
    @Autowired
    private RouteService routeService;

    @RequestMapping("/recommendRoute")
    public String recommendRoute(Model model, @ModelAttribute("searchName") String searchName, @PageableDefault(size = 10) Pageable pageable) {
        return "recommend/recommend_route";
    }

    @RequestMapping("/recommendForm")
    public String recommendForm(@ModelAttribute("sex") String sex,@ModelAttribute("age") String age, @ModelAttribute("job") String job, @ModelAttribute("income") String income,Model model, @PageableDefault(size = 10) Pageable pageable)
    {
        RecommedRoute route=new RecommedRoute();
        route.setAge(age);
        route.setIncome(income);
        route.setJob(job);
        route.setSex(sex);
        route.WriteFile();
        Page<TravelRoute> page = routeService.RecommendRouteListUI(route.getRoute(), pageable);
        List<TravelRoute> top10Route = routeService.findTop10Route();
        model.addAttribute("top10Route", top10Route);
        model.addAttribute("page", page);
        return "route/travelRoute";
    }
}
