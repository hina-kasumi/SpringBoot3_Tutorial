# #3 Quản lý Exception tập trung và Validation

***

## 1. Quản lý Exception như thế nào?

- các `exception` cần được quản lý trong một package `exception`.

1. Khai báo một class là nơi quản lý toàn bộ các `exception` với `@ControllerAdvice`.
2. Để một method có thể bắt và xử lý `Exception` ta cần khai báo `@ExceptionHandler`. Annotation này cần truyền vào
   một `param` là class `exception` mà ta muốn bắt (ví dụ: `@ExceptionHandler(value = Exception.class)`).

***

## 2. Validation dữ liệu đầu vào

sử dụng dependency `spring-boot-starter-validation`.

1. Có thể khai báo một `field` của một class bắt `field` đó phải tuân theo những yêu cầu cụ thể, có thể truyền thêm
   vào `message` để trả về khi mà dữ liệu vi phạm yêu cầu của `field`. Sau đó khi tạo một đối tượng tượng thì cần
   thêm `@Valid` để framework biết rằng ta cần kiểm tra định dạng của đối tượng này.
2. Nếu dữ liệu truyền vào vi phạm yêu cầu của dữ liệu nó sẽ `throw` ra `MethodArgumentNotValidException`.

***

## 3. Vấn đề gặp phải

### 1. trong interface `UserRepository` chưa được `implement` nhưng tại sao lại vẫn có thể sử dụng method `existsByUsername()`?

 ```
   public interface UserRepository extends JpaRepository<User, String> {
      boolean existsByUsername(String username);
   }
 ```

- `JpaRepository` là một interface trong `Spring Data JPA` cung cấp các phương thức `CRUD` cơ bản và các phương thức
  tiện ích
  như `existsById()`, `findAll()`, `deleteById()`, v.v. Nó sử dụng cơ chế `"query derivation"` (xây dựng truy vấn tự
  động từ tên
  phương thức).


- Khi bạn định nghĩa phương thức `existsByUsername(String username)` trong `UserRepository`, `Spring Data JPA` tự động
  tạo ra
  một `truy vấn SQL` tương ứng để kiểm tra sự tồn tại của `username` trong cơ sở dữ liệu mà không cần bạn phải cung cấp
  mã
  `SQL` hoặc viết một `implementation` cụ thể.


- `Query Derivation:` **Spring Data JPA** sử dụng tên phương thức bạn đã định nghĩa (ở đây là `existsByUsername()`) để tự động tạo
  ra một truy vấn SQL. Cách đặt tên này tuân theo một quy tắc chuẩn của **Spring Data**, và **Spring** sẽ tự động hiểu rằng
  phương thức này cần kiểm tra xem có một `User` với `username` cụ thể hay không.
    + Cách đặt tên này bao gồm các phần như sau:
        * `existsBy`: Được Spring Data hiểu là "kiểm tra sự tồn tại của".
        * `Username`: Là tên trường trong entity `User`, và Spring sẽ tự động sử dụng tên này để tạo truy vấn.