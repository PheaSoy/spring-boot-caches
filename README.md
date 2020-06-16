# spring-boot-caches

## Testing
* Running the application
* Navigate to the endpoint 
```shell script
curl http://localhost:8081/books/snb-1
```
* The first time, you will the application taking around 2 seconds and slow respons back to the client.
* Then you can try again it should be fast because we are using `@Cacheable("books")`

```java
    @Transactional(readOnly = true,timeout = 20)
    @Cacheable("books")
    public Book findBookBySNB(String snb){
        log.info("Select book with snb:{}",snb);
        slow();
        return bookRepository.findBookBySnb(snb).orElseThrow(() -> new BookNotFoundException());
    }
```

* Accessing to the /actuator/caches
```shell script
curl http://localhost:8081/actuator/caches/books
```

* Result
```json
{
"target": "java.util.concurrent.ConcurrentHashMap",
"name": "books",
"cacheManager": "cacheManager"
}
```
