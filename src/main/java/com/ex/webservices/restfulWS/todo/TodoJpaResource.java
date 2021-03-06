package com.ex.webservices.restfulWS.todo;

import com.ex.webservices.restfulWS.TodoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TodoJpaResource {

    @Autowired
    private TodoHardcodedService todoService;

    @Autowired
    private TodoJpaRepository todoJpaRepository;

    @GetMapping("/jpa/users/{username}/todos")
    public List<Todo> getAllTodos(@PathVariable String username) {
        // Thread.sleep(3000);
    return  todoJpaRepository.findByUsername(username);
       // return todoService.findAll();
    }

    @GetMapping("/jpa/users/{username}/todos/{id}")
    public Todo getTodo(@PathVariable String username, @PathVariable long id) {
        // Thread.sleep(3000);
        return todoJpaRepository.findById(id).get();
        //return todoService.findById(id);
    }


    // DELETE /users/{username}/todos/{id}
    @DeleteMapping("/jpa/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {

        todoJpaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //Edit/Update a Todo
    //PUT /users/{user_name}/todos/{todo_id}
    @PutMapping("/jpa/users/{username}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(
            @PathVariable String username,
            @PathVariable long id, @RequestBody Todo todo){

        todo.setUsername(username);

        Todo todoUpdated = todoJpaRepository.save(todo);

        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }

    @PostMapping("/jpa/users/{username}/todos")
    public ResponseEntity<Void> createTodo(
            @PathVariable String username, @RequestBody Todo todo){

        todo.setUsername(username);

        Todo createdTodo = todoJpaRepository.save(todo);

        //Location
        //Get current resource url
        ///{id}
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

}






//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//
//import java.net.URI;
//import java.util.List;
//
//
//@CrossOrigin(origins="http://localhost:4200")
//@RestController
//public class TodoResource {
//
//    @Autowired
//    private TodoHardcodedService todoService;
//
//    @GetMapping("/users/{username}/todos")
//    public List<Todo> getAllTodos(@PathVariable String username)  {
//       // Thread.sleep(3000);
//        return todoService.findAll();
//    }
//
//    @GetMapping("/users/{username}/todos/{id}")
//    public Todo  getTodo(@PathVariable String username, @PathVariable long id)  {
//        // Thread.sleep(3000);
//        return todoService.findById(id);
//    }
//
//    //DELETE /users/{username}/todos/{id}
//    @DeleteMapping("/users/{username}/todos/{id}")
//    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {
//
//      Todo todo = todoService.deleteById(id);
//      if (todo!=null) {
//          return ResponseEntity.noContent().build();
//      }
//      //404 Not Found
//      return    ResponseEntity.notFound().build();
//    }
//
//
//    // PUT   /users/{user_name}/todos/{todo_id}
//    @PutMapping("/users/{username}/todos/{id}")
//    public  ResponseEntity<Todo> updateTodo(
//            @PathVariable String username,
//            @PathVariable long id, @RequestBody Todo todo){
//    Todo todoUpdated = todoService.save(todo);
//
//    return new ResponseEntity<Todo>(todo, HttpStatus.OK);
//    }
//
//    // POST /users/{user_name}/todos/
//    @PostMapping("/users/{username}/todos")
//    public  ResponseEntity<Void> updateTodo(
//            @PathVariable String username, @RequestBody Todo todo) {
//        Todo createdTodo = todoService.save(todo);
//
//        // Location
//        //get current resource URL
//        //users/{username}/todos/{id}
//       URI uri =  ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
//
//
//        return ResponseEntity.created(uri).build();
//    }




