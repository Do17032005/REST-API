package com.example.demo.controller;
import com.example.demo.model.entity.nh;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping ("api/v1/nh")
public class nhcontroller {
    List<nh> nh = new ArrayList<>(
            List.of(
            new nh(1, "Độ", "hanoi", "12423", "OPEN")
    )
    );

@GetMapping
public List<nh> getNh(){
    return nh;
}
}
