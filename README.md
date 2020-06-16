# spring-boot-caches

## Testing
* Running the application
* Navigate to the endpoint 
```shell script
curl http://localhost:8081/books/snb-1
```
* The first time you will this response slow and taking time more than 2 seconds.
* Then you can try again it should be fast because 

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