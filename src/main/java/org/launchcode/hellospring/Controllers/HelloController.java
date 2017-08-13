package org.launchcode.hellospring.Controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @RequestMapping(value = "")
    @ResponseBody

    //add HttpServletRequest object to methods to input parameter
    public String index(HttpServletRequest request) {
        //request.getParameter to pull the paremeters out of the request
        String name = request.getParameter("name");

        //add null check, if no name, then will output hello world
        //important to safeguard against null.
        if (name == null) {
            name = "World";
        }
        return "Hello " + name;
    }
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    @ResponseBody
    //new request handler
    public String helloForm(){
        //string to return some html
        String html = "<form method='post'>" +
                //plus sign enables creation of multiline string
                "<input type='text' name='name' />" +
                "<input type='submit' value='Greet Me!' /> " +
                "</form?>";

        return html;
    }

    @RequestMapping(value = "hello", method = RequestMethod.POST)
    @ResponseBody
    //handle requests that are submitted from the above form
    //addhttpServletRequest as an input parameter, provides access to the request object
    public String helloPost(HttpServletRequest request){

        String name = request.getParameter("name");

        return "Hello " + name;
    }

    //RequestMapping is route where we want this handler to live at
    //Here we specify that URL segment gets passed in as data
    //says that name should be a parameter for the handler method
    @RequestMapping(value = "hello/{name}")
    @ResponseBody
    public String helloUrlSegment(@PathVariable String name) {
        //tells Spring to look for the value of the name input parameter from a variable that is in the path
        return "hello " + name;
    }

    @RequestMapping(value = "goodbye")
    //redirect to the hello handler (says hello world) when /goodbye route is accessed
    public String goodbye() {
        return "redirect:/";
    }


}
