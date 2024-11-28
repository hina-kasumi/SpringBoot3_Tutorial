# #6 Mã hóa mật khẩu an toàn và matching nhanh chóng với Bcrypt

sử dụng `spring-security-crypto`.
***

## 1. Mã hóa

### a. Thuật toán băm mật khẩu (Password Hashing)

Thuật toán băm chuyển đổi mật khẩu thành một chuỗi cố định và không thể đảo ngược, giúp bảo vệ mật khẩu gốc. Các thuật
toán phổ biến:

- `MD5 (Message Digest Algorithm 5)`: Nhanh, nhưng không an toàn vì dễ bị tấn công `collision` và `rainbow table`.
- `SHA (Secure Hash Algorithm)`:Phù hợp cho ứng dụng băm thông tin nhưng cần thêm cơ chế `salt` và `pepper` để bảo mật
  tốt
  hơn.
- `PBKDF2 (Password-Based Key Derivation Function 2)`:Sử dụng cơ chế `salt` và nhiều vòng lặp để tăng độ an toàn. Được
  khuyến nghị sử dụng trong các ứng dụng bảo mật.
- `bcrypt`:Tích hợp cơ chế `salt` và điều chỉnh độ phức tạp qua `cost factor`. Rất an toàn và được sử dụng phổ biến
  trong
  các ứng dụng web hiện đại.
- `scrypt`:Sử dụng nhiều tài nguyên CPU và bộ nhớ để chống tấn công `brute force` và `hardware attack`. Được sử dụng
  trong
  các ứng dụng bảo mật cao.

### b. Thuật toán mã hóa mật khẩu (Password Encryption)

Khác với hashing, mã hóa cho phép khôi phục lại mật khẩu gốc. Các thuật toán mã hóa thông dụng:

- `AES (Advanced Encryption Standard)`: Dạng mã hóa đối xứng, nhanh và an toàn. Thường được sử dụng để mã hóa dữ liệu
  quan trọng hơn là mật khẩu.
- `RSA (Rivest-Shamir-Adleman)`: Dạng mã hóa bất đối xứng, dùng cặp khóa công khai và khóa riêng tư. Không thường dùng
  để mã hóa mật khẩu trực tiếp, nhưng được sử dụng để trao đổi khóa trong các giao thức bảo mật.
- `Blowfish`: Thay thế tốt cho AES trong một số trường hợp, nhưng ít phổ biến hơn.

***

## 2. Quá trình mã hóa mật khẩu?

- khi người dùng tạo một tài khoản mới thì khi dữ liệu được gửi đến `server` thì `server` sẽ mã hóa mật khẩu bằng
  hàm `encode()` của `interface` `PasswordEncoder` vào chuyển dữ liệu vào cơ sở dữ liệu.
- khi kiểm tra mật khẩu thì sẽ gọi hàm `matches()` của `interface` `PasswordEncoder` để kiểm tra mật khẩu người dùng gửi
  có đúng với mật khẩu hiện tại hay
  không.

***

## 3. Các vấn đề gặp phải

### 1. Optional<?> là gì?

`Optional` là một class trong `Java` được giới thiệu từ `Java 8` trong gói `java.util`. Nó được thiết kế để giải quyết vấn đề
liên quan đến việc xử lý giá trị `null` và giảm thiểu lỗi `NullPointerException (NPE)`.

Thay vì trả về `null` khi không có giá trị, một phương thức có thể trả về một đối tượng `Optional`, giúp bạn kiểm tra xem
giá trị có tồn tại hay không một cách rõ ràng.

a. Ưu điểm của `Optional`
- Giảm thiểu lỗi `NullPointerException`.
- Cung cấp cách tiếp cận rõ ràng và dễ đọc để xử lý giá trị `null`.
- Hỗ trợ lập trình theo phong cách `Functional Programming`.

b. Nhược điểm của `Optional`
- Không nên lạm dụng, đặc biệt trong các trường hợp đơn giản.
- Có thể làm tăng độ phức tạp khi xử lý các đối tượng lồng nhau.
- Không được khuyến nghị dùng `Optional` làm thuộc tính của một class hoặc trong các cấu trúc dữ liệu hiệu năng cao vì
  nó gây ra `overhead` không cần thiết.

c. Khi nào nên dùng `Optional`?
- Khi trả về kết quả của một phương thức có thể không có giá trị.
- Khi cần xử lý giá trị `null` một cách rõ ràng và an toàn.
- Không nên dùng trong các thành phần có thể kiểm tra `null` dễ dàng như trong `getter/setter` của `POJO` hoặc `entity`.
