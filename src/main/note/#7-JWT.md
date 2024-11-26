# \#7. JWT là gì, cách tạo, ký và xác thực một JWT

sử dụng dependency `nimbus-jose-jwt`
***

## 1. JWT là gì?

- JWT là viết tắt của ***JSON WEB SERVICE*** được sử dụng để xác thử và ủy quyền trong ứng dụng web.

***

- JWT bao gồm 3 phần chính:
    + ***Header:*** chứa thông tin về loại token và thuật toán mã hóa.
    + ***Payload:*** chứa dữ liệu (claims) mà bạn muốn truyền đi. Clams có thể là:
        * **Registered claims (đã chuẩn hóa)**: như `iss` (issuer),`exp` (expiration time),`sub` (subject),`aud` (
          audience), v.v.
        * **Public claims (tự định nghĩa)**: như `username`, `roles`, v.v.
        * **Private claims (giữa các bên)**: các thông tin tùy chỉnh không chuẩn hóa.
    + ***Signature:*** được tạo bằng cách mã hóa `Header` và `Payload` với một khóa bí mật hoặc khóa riêng.

***

## 2. Cách hoạt động của JWT

1. **Client gửi yêu cầu đăng nhập:** Client gửi thông tin đăng nhập tới server.
2. **Server tạo JWT:** Sau khi xác thực thông tin đăng nhập, server tạo một JWT chứa thông tin người dùng và trả về cho
   client.
3. **Client gửi JWT trong các yêu cầu tiếp theo:** JWT được lưu trữ (thường là trong `localStorage` hoặc `cookie`) và
   được gửi kèm trong `Authorization` header của các yêu cầu HTTP.
4. **Server xác minh JWT:** Server kiểm tra chữ ký của JWT để xác nhận tính hợp lệ và lấy thông tin từ `Payload`.

***

## 3. JWT trong dự án này hoạt động như thế nào?

1. Khi truy cập `/auth/token` sẽ thực hiện hàm `authenticate()` của class `AuthenticationService` nếu như username được
   gửi từ
   client đã tồn tại và password là đúng thì gọi hàm `generateToken()` để tạo một token cho nguời dùng đó có hiệu lực
   trong vòng 1 tiếng với những thông tin cơ bản của một JWT.

2. Khi truy cập `/auth/introspect` sẽ gọi hàm `introspectResponse` của class `AuthenticationService`. hàm sẽ kiểm tra và
   xác thực một JWT có hợp lệ hay còn khả dụng hay không. Hàm sẽ so sánh thời gian hiện tại và so sánh JWT được gửi tới
   server với chữ kí với chữ kí của server. Nếu cùng chữ kí và vẫn còn khả dụng thì trả về true.
***
## 4. Vấn đề gặp phải
1. Chưa hiểu rõ lắm về JWT:
    + hãy đọc thêm về JWT.
2. Chưa hiểu hết được `nimbus-jose-jwt`:
    + Tìm hiểu thêm thông tin về dependency này.