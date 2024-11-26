# #2 Kết nối Database với Spring JPA và tạo API CRUD

sử dụng dependency `mysql-driver`, `spring-jpa`
***

## 1. JPA là gì?

- JPA là tiêu chuẩn thao tác với cơ sở dữ liệu sử dụng `Hibernate`, JPA cung cấp một giao diện mạnh mẽ, giúp giải quyết
  các vấn đề khi làm việc với `Hibernate`.

***

## 2. Kết nối với Database

Để kết nối với `Database` ta config url, username và password trong `application.yaml`.

    spring:
        datasource:
          url: "jdbc:mysql://localhost:3306/"
          username: username
          password: password

***
Nếu `Database` chưa tồn tại ta có thể config trong `application.yaml` để tạo một `Database` tự động.

    spring:
        jpa:
            hibernate:
                ddl-auto: update
            show-sql: true

***

## 3. Sử dụng JPA như thế nào?

1. Khai báo annotation `@Entity` trên class là đại diện cho dữ liệu của một hàng trong một bảng của `Database`.
2. Về phần ID thì ta cần khai báo `@ID` trên field là ID của đối tượng (ví dụ: mã sinh viên). Bạn nên sử dụng
   thêm `@GeneratedValue(strategy = GenerationType.UUID)` để tự động tạo một ID random và không trùng lặp.
3. Vì là Spring Boot sử dụng cấu trúc `3 Layer (hay 3 lớp) `thay vì MVC do đó ta cần một folder `repository`.
4. Bên trong folder `repository` một tạo interface `repository` kế thừa class `JpaRepository<Entity, typeof(ID)>` của
   JPA. Class `JpaRepository` nó sẽ tạo một Bean trong trong Spring do đó khi `@Autowired` sẽ không sảy ra lỗi ngay cả
   khi không class nào `implement` class này.
5. Layer Service gọi class `repository` và sử dụng các tiện ích của `JPA`.

***

## 4. Vấn đề gặp phải

1. Chưa chưa hiểu `Hibernate`:
   + Tìm hiểu `JPA`, `Hibernate`, `JDBC`.
2. Sự khác biệt giữa `JPA` và `Hibernate`:
   + `JPA` là một tiêu chuẩn `(specification)`, trong khi `Hibernate` là một thư viện `(framework)` có thể triển
     khai `JPA`.
3. tại sao `interface UserRepository extends JpaRepository` lại không lỗi?
   + Vì `JpaRepository` là một `interface`.