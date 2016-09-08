package com.dev.web;

import com.dev.dto.BookDTO;
import com.dev.service.BookService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value = "/rest/books")
public class BookController extends AbstractController<BookService, BookDTO> {

    @RequestMapping(value = "/getMore", method = RequestMethod.POST)
    public List<BookDTO> getMore(@RequestParam int position){
        return getService().getMore(position);
    }

}
