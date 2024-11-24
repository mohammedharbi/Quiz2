package com.example.library.Service;

import com.example.library.Model.Book;
import com.example.library.Model.User;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Getter
@Service
public class BookService {

    ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(String id) {
        for (Book book : books) {
            if (book.getID().equals(id)) {books.remove(book);
            return true;}
        }
        return false;
    }

    public boolean updateBook(String id,Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getID().equals(id)) {
                books.set(i, book);
                return true;
            }
        }
        return false;
    }
    //Create an endpoint that takes a Book name then returns one Book .
    public Book getBookName(String name) {
        for (Book book : books) {
            if (book.getName().equals(name)) {return book;}
        }
        return null;
    }

    //Create an endpoint that takes a category then return all books have this category.
    public ArrayList<Book> getBooksCategory(String category) {
        ArrayList<Book> books1 = new ArrayList<>();
        for (Book book : books) {
            if (book.getCategory().equals(category)) {books1.add(book);}
        }
        return books1;
    }

    //Create an endpoint that takes a number of pages and returns all Books who have this number of pages or above .
    public ArrayList<Book> getBooksbyPages(int pages) {
        ArrayList<Book> books2 = new ArrayList<>();
        for (Book book : books) {
            if (book.getNumber_of_pages() >= pages) {books2.add(book);}
        }
        return books2;
    }
    //Create an endpoint that change a book status to unavailable (Only the librarian can change the status of the book)
    public boolean changeAvailablty(String bookid) {
        for (Book book : books) {
            if (book.getID().equals(bookid)) {
                if (book.getIsAvailable() == true){
                book.setIsAvailable(false);
                return true;}
            }
        }
        return false;
    }
}
