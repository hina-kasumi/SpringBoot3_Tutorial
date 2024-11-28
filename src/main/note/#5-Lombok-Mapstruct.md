# #5 Sử dụng Lombok và Mapstruct

sử dụng `lombok`, `mapstruct`.

`lombok-mapstruct-binding` sẽ giúp cho `lombok`, `mapstruct` kết hợp nhuần nhuyễn với nhau.ll
***

## 1. Lombok là gì?

`Lombok` là một thư viện `Java` giúp giảm thiểu lượng mã nguồn cần viết bằng cách tự động tạo ra các phương thức thông
dụng
như `getter`, `setter`, `constructor`, `equals`, `hashCode`, và nhiều hơn nữa thông qua việc sử dụng
các `annotation`. `Lombok` giúp
mã nguồn gọn gàng hơn, dễ đọc hơn và giảm thiểu các lỗi do viết mã thủ công.

### a. Cách hoạt động của Lombok

`Lombok` sử dụng `annotation processing` (xử lý chú thích) để can thiệp vào quá trình biên dịch của `Java`. Khi trình
biên
dịch xử lý mã nguồn, `Lombok` sẽ tạo ra mã nguồn cần thiết dựa trên các chú thích mà bạn sử dụng.

### b. Các Annotation Thường Dùng

| annotation               | cách dùng                                                                                                       |
|:-------------------------|:----------------------------------------------------------------------------------------------------------------|
| @Getter, @Setter         | Tự động sinh phương thức `get()` và `set()` cho các thuộc tính.                                                 |
| @Data                    | Tự động tạo các phương thức `getter`, `setter`, `toString()`, `equals()`, `hashCode()`, `constructor` mặc định. |
| @NoArgsConstructor       | Tạo constructor không tham số.                                                                                  |
| @AllArgsConstructor      | Tạo constructor với tất cả các tham số.                                                                         |
| @RequiredArgsConstructor | Tạo constructor  chỉ với các thuộc tính cần thiết.                                                              |
| @Builder                 | Tạo **design pattern Builder** để xây dựng các đối tượng phức tạp.                                              |
| @ToString                | Tạo phương thức `toString()` với tùy chọn để loại trừ một số trường hoặc bao gồm lớp cha.                       |
| @EqualsAndHashCode       | Tự động sinh phương thức `equals()` và `hashCode()`.                                                            |

***

## 2. Mapstruct là gì?

`MapStruct` là một thư viện `Java code generator (trình tạo mã)` dùng để tự động chuyển đổi `(mapping)` giữa
các `Java Beans`
hoặc `Data Transfer Objects (DTOs)` với nhau. Thay vì viết tay code chuyển đổi giữa các đối tượng, `MapStruct` sẽ tự
động
tạo ra các lớp `mapper` giúp chuyển đổi nhanh chóng và hiệu quả.

### a. Cách hoạt động của MapStruct

`MapStruct` hoạt động dựa trên việc tạo ra các `interface` hoặc `abstract class` gọi là `mapper`, trong đó định nghĩa
các phương
thức `mapping` giữa các đối tượng. Khi biên dịch, `MapStruct` sẽ tự động tạo ra lớp triển khai cho các `mapper` này.
***

### b. Vì sao lại sử dụng Mapstruct?

Vì trong ứng dụng web thì không phải lúc nào dữ liệu gửi về hay gửi đi cũng chứa toàn bộ `field` của dữ liệu
trong `database`.

- ví dụ trong ứng dụng này khi nhận `request` từ **client** rồi `response` lại sẽ có một số `field` của `User` chứ không
  phải toàn
  bộ.

## Vấn đề gặp phải

### 1. Builder là cái gì?

- Mục tiêu chính của `Builder` là cung cấp một cách linh hoạt để tạo ra các đối tượng phức tạp bằng cách tách
  biệt quá trình xây dựng đối tượng từ cấu trúc thực tế của nó. `Builder` *giúp ta tạo đối tượng linh hoạt hơn, dễ đọc,
  dễ
  bảo trì hơn*, **ta có thể tạo với đối tượng với những trường nào mà ta muồn mà không phải tạo `constructor` mới**. ví
  dụ:

```java
public class User {
    // Các thuộc tính của lớp User
    private String firstName;
    private String lastName;

    // Constructor private để ngăn không cho tạo đối tượng trực tiếp
    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
    }

    // Lớp Builder bên trong lớp User
    public static class UserBuilder {
        private String firstName;
        private String lastName;

        // Các phương thức để thiết lập giá trị cho các thuộc tính
        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this; // Trả về chính đối tượng UserBuilder
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        // Phương thức build để tạo đối tượng User
        public User build() {
            return new User(this);
        }
    }

    //cách sử dụng
    public static void main(String[] args) {
        User user = new User.UserBuilder()
                .setFirstName("John")
                .setLastName("Doe")
                .build();
        System.out.println("User created: " + user);
    }
}
```

***

### 2. mapper sẽ chuyển dữ liệu như thế nào?

- `MapStruct` sẽ tự động `mapping` các thuộc tính có tên giống nhau và kiểu dữ liệu tương thích giữa hai đối tượng.
- nếu muốn map 2 dữ liệu có tên khác nhau thì ta phải thêm `@Mapping(source = "name", target = "fullName")` lên
  trên `method` trong `interface mapper`.
- nếu muốn `mapping` 2 `field` có kiểu dữ liệu khác nhau thì ta cần thêm `default method` để định nghĩa cách của chuyển đổi 2
  kiểu dữ liệu. Đối với các kiểu dữ liệu khác sang `String` thì không cần phải thêm `default method`.

***

### 3. tại sao code này lại không báo lỗi:

```java

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
}
```

```java

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;
}
```

- vì `@RequiredArgsConstructor` sẽ kết hợp với `@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)` để tạo
  một `constructor` nên sẽ không báo lỗi.


- khi ứng dụng khởi tạo `Spring` phát hiện `UserController` nhờ `@RestController` và nhận thấy nó cần một `UserService`
  thông qua constructor., khi đó `Spring` nhận thấy `constructor` của `controller` yêu cầu một `instance` của `Service`(
  vì bạn đã khai báo `Service` là `@Service`) nên tìm trong `Application Context` nếu có thì sẽ tiêm vào. Ứng dụng gọi
  lần lượt từ `main -> controller -> service`. Đó là cách hoạt động của ứng dụng `Spring`.