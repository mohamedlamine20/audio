package com.moha.demo.Conroller;


import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URISyntaxException;

@RequestMapping("/home")
public  abstract class AbstractController {

    abstract void test() throws URISyntaxException, IOException, InterruptedException;


}
