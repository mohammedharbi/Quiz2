package com.example.library.Controller;

import com.example.library.ApiResponse.ApiResponse;
import com.example.library.Model.Book;
import com.example.library.Service.BookService;
import com.example.library.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/library-book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getBooks(){
        ArrayList<Book> books = bookService.getBooks();
        return ResponseEntity.status(200).body(books);
    }

    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody @Valid Book book, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        bookService.addBook(book);
        return ResponseEntity.status(201).body(new ApiResponse("Book added"));
    }

    @PutMapping("/update/{ID}")
    public ResponseEntity updateBook(@PathVariable String ID,@RequestBody @Valid Book book, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdated = bookService.updateBook(ID,book);
        if(isUpdated){return ResponseEntity.status(201).body(new ApiResponse("Book updated"));
        }else {return ResponseEntity.status(400).body(new ApiResponse("Book not updated"));}
    }

    @DeleteMapping("/delete/{ID}")
    public ResponseEntity deleteBook(@PathVariable String ID){

        boolean isDeleted = bookService.removeBook(ID);
        if(isDeleted){return ResponseEntity.status(201).body(new ApiResponse("Book deleted"));
        }else {return ResponseEntity.status(400).body(new ApiResponse("Book not deleted"));}
    }

    @GetMapping("/getBookName/{name}")
    public ResponseEntity getBookName(@PathVariable String name){

        if (bookService.getBookName(name) != null) {return ResponseEntity.status(200).body(bookService.getBookName(name));
        }return ResponseEntity.status(404).body(new ApiResponse("Book not found"));

    }

    @GetMapping("/getBooksCategory/{category}")
    public ResponseEntity getBooksCategory(@PathVariable String category){
        ArrayList<Book> books = bookService.getBooksCategory(category);
        return ResponseEntity.status(200).body(books);
    }

    @GetMapping("/getBooksbyPages/{pages}")
    public ResponseEntity getBooksbyPages(@PathVariable int pages){
        ArrayList<Book> books = bookService.getBooksbyPages(pages);
        return ResponseEntity.status(200).body(books);
    }

    @PutMapping("/changeavailabilty/{idlibrarian}/{idbook}")
    public ResponseEntity changeAvailability(@PathVariable String idlibrarian,@PathVariable String idbook){
        if (userService.isLibrarian(idlibrarian)) {
            if (bookService.changeAvailablty(idbook)){
            return ResponseEntity.status(201).body(new ApiResponse("Book Availability set to unavailable "));
            }else return ResponseEntity.status(400).body(new ApiResponse("Book is already unavailable"));
        }else return ResponseEntity.status(400).body(new ApiResponse("the one makes the change role isn't librarian"));
    }



}
