# #4 Quản lý Exception nâng cao và cách chuẩn hóa API Response đúng cách

***

## 1. Chuẩn hóa API Response là gì?

**Chuẩn hóa API Response:** là quá trình đảm bảo rằng các phản hồi từ API (Application Programming Interface) có một cấu
trúc thống nhất, dễ hiểu và dễ sử dụng cho người tiêu dùng của API, bất kể có sự thay đổi trong yêu cầu hoặc nguồn dữ
liệu.

***
Quá trình chuẩn hóa này có thể bao gồm những yếu tố sau:

1. Định dạng thống nhất:
    - **API Response** thường được trả về dưới dạng `JSON` (hoặc đôi khi là `XML`). Việc đảm bảo tất cả các phản hồi đều
      tuân theo một định dạng nhất quán giúp người dùng API dễ dàng xử lý và hiểu kết quả trả về.

2. Cấu trúc dữ liệu rõ ràng:
    - Phản hồi từ `API` cần được tổ chức một cách hợp lý với các trường dữ liệu rõ ràng, chẳng hạn như thông tin dữ liệu
      chính, trạng thái `(status)`, thông báo lỗi `(error messages)`, và các `metadata` (thông tin phụ trợ).

3. Thông tin về trạng thái và lỗi rõ ràng:
    - Các API cần phải cung cấp thông tin trạng thái (status code như 200 OK, 404 Not Found, 500 Internal Server Error)
      trong phản hồi. Khi có lỗi, phản hồi phải chỉ rõ lỗi xảy ra, ví dụ như:
     ```
        {
            "status": "error",
            "message": "Invalid request data",
            "error_code": 400
        }
     ```
4. Metadata và các thông tin hỗ trợ:
    - Các `API` có thể thêm các thông tin `metadata` như tổng số bản ghi `(total records)`, số trang `(pagination)`,
      hoặc các tham số liên quan để giúp người tiêu dùng `API` hiểu rõ hơn về dữ liệu được trả về và cách sử dụng các
      tài nguyên.
5. Consistency (Tính nhất quán):
    - Tất cả các `API Response` phải tuân theo một quy tắc hoặc chuẩn nhất quán về cách sắp xếp, tên
      trường `(naming convention)`, các loại dữ liệu trả về. Điều này giúp lập trình viên dễ dàng đọc hiểu và sử
      dụng `API` một cách hiệu quả.

***

## 2. Chuẩn hóa như thế nào?

***

1. Tạo một `API Response` để có thể quy định mẫu `API` chung.
2. Đối với `Exception` thì ta nên tạo một `enum` `ErrorCode` để có thể định nghĩa các mã lỗi và đó là lỗi gì. Bên cạnh
   đó ta cũng cần tạo một `ErrorCode` cho tất cả `exception` mà ta chưa định nghĩa.

## 3. Vấn đề gặp phải

1. Nên đặt mã lỗi như thế nào gì?
    - đây là tổng quát chung của các mã lỗi:

      | mã lỗi | loại lỗi                                                                                |
            |:-------|:----------------------------------------------------------------------------------------|
      | 1xx    | có thể là mã lỗi hệ thống trong một số máy chủ hoặc thiết bị.                           |
      | 2xx    | Lỗi liên quan đến kết nối mạng hoặc cấu hình hệ thống không chính xác.                  |
      | 3xx    | Lỗi liên quan đến quyền truy cập hoặc xác thực                                          |
      | 4xx    | Lỗi này có thể là do người dùng gửi yêu cầu không hợp lệ hoặc cấu hình không chính xác. |
      | 5xx    | Lỗi này báo hiệu vấn đề xảy ra trên máy chủ.                                            |

2. Tại sao `exception` lại gọi được `AppException`?
    - Vì là trong `service` nếu xảy ra ngoại lệ sẽ `throw` ra `AppException` (ví
      dụ: `throw new AppException(ErrorCode.USER_EXISTED);`)