package com.springjsp.controller;


import com.springjsp.dto.CartDto;
import com.springjsp.dto.ItemDto;
import com.springjsp.model.Item;
import com.springjsp.model.User;
import com.springjsp.service.ItemService;
import com.springjsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private CartDto cartDto;


    public static int noOfThreads = 20;

    private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(noOfThreads);

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String Login(){
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String LoginPost(ModelMap modelMap, @RequestParam String userName, @RequestParam String password, HttpServletResponse response){
        new User();
        User user = userService.getUserByName(userName);
        if(user == null){
            modelMap.put("errorMsg","Please provide the correct username and password");
            return "login";
        }
        String ResponseUserName = user.getUserName();
        String ResponsePassWord = user.getPassword();
        if(userName.equals(ResponseUserName) && password.equals(ResponsePassWord)){
            Cookie cookie = new Cookie("useremail", user.getEmail());
            cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return  "redirect:/home";
        }
        modelMap.put("errorMsg","Please provide the correct username and password");
        return "login";
    }

    @RequestMapping("/register")
    public String Register(){
        return "register";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String ResgisterPost(ModelMap modelMap,@RequestParam String userName,@RequestParam String email,@RequestParam String password){
        if(userName.equals("")||email.equals("")||password.equals("")){
            modelMap.put("errorMsg","Please provide all the details to register");
            return "register";
        }
        userService.addUser(userName,email,password);
        return "login";
    }

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String getItems(Model model){
        List<Item> items = itemService.getAllItems();
        List<ItemDto> itemDtos = new ArrayList<>();
        ItemDto itemDto;
        for(Item item: items){
            String encodedData = DatatypeConverter.printBase64Binary(item.getData());
            itemDto = new ItemDto();
            itemDto.setItemName(item.getItemTitle());
            itemDto.setImageBase64(encodedData);
            itemDto.setId(item.getId());
            itemDtos.add(itemDto);
        }
        model.addAttribute("products",itemDtos);
        model.addAttribute("notification",cartDto.getLength());
        return "home";
    }

    @RequestMapping("/invoice")
    public String InvoiceItem(Model model,@RequestParam Long id){
        Item item = itemService.getItemById(id);
        ItemDto itemDto = new ItemDto();
        String encodedData = DatatypeConverter.printBase64Binary(item.getData());
        itemDto.setId(item.getId());
        itemDto.setItemName(item.getItemTitle());
        itemDto.setImageBase64(encodedData);
        itemDto.setAmount(item.getAmount());
        System.out.println(itemDto);
        cartDto.setCartItems(itemDto);
        cartDto.setTotalAmount(cartDto.getTotalAmount()+itemDto.getAmount());
        System.out.println(cartDto.getLength());
        return "redirect:/home";
    }

    @RequestMapping("/sendEmail")
    public String EmailSender(@CookieValue(value = "useremail", defaultValue = "") String userEmail){
        String itemList = "";
        for(ItemDto cartItems: cartDto.getArrayList()){
            itemList += cartItems.getItemName()+", ";
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kavinkumarbaskar@gmail.com");
        message.setTo(userEmail);
        message.setSubject("Invoice For The Purchased Item From ShoppingCart.com");
        message.setText("You've successfully purchased "+itemList+" from ShoppingCart.com for Rs."+cartDto.getTotalAmount());

        quickService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    mailSender.send(message);
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        });

        System.out.println("Mail sent successfully!!!!");
        cartDto.removeAllItemsInCart();
        cartDto.setTotalAmount(0l);
        return "redirect:/home";
    }

    @RequestMapping("/cart")
    public String CartPage(Model model){
        List<ItemDto> cartItemsList = new ArrayList<>();
        for(ItemDto item : cartDto.getArrayList()){
            cartItemsList.add(item);
        }
        model.addAttribute("cartItems",cartItemsList);
        model.addAttribute("noOfItems",cartDto.getLength());
        model.addAttribute("totalAmount",cartDto.getTotalAmount());
        return "cart";
    }

    @RequestMapping("/removeAll")
    public String removeCart(){
        cartDto.removeAllItemsInCart();
        System.out.println(cartDto.getLength());
        return "redirect:/home";
    }

    @RequestMapping("/removeItem")
    public String removeItem(@RequestParam("item") String item){
        cartDto.removeItem(item);
        return "redirect:/cart";
    }



    @RequestMapping("/logout")
    public String Logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        if(request.isRequestedSessionIdValid() && session != null)
        {
            session.invalidate();
            return "redirect:/login";
        }
        return "redirect:/login";
    }
}
