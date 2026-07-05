
## Danh Sách Endpoint

| #   | Method | Endpoint                            | Mô tả                                                                         |
| --- | ------ | ----------------------------------- | ----------------------------------------------------------------------------- |
| 1   | GET    | `/tasks`                            | Lấy toàn bộ danh sách công việc.                                              |
| 1   | GET    | `/users`                            | Lấy toàn bộ danh sách người dùng.                                             |
| 2   | POST   | `/tasks`                            | Tạo mới một công việc.                                                        |
| 2   | POST   | `/users`                            | Tạo mới một người dùng.                                                       |
| 3   | PATCH  | `/tasks/{id}/status`                | Cập nhật trạng thái của một công việc.                                        |
| 3   | PATCH  | `/users/{id}/role`                  | Cập nhật vai trò của một người dùng.                                          |
| 4   | DELETE | `/tasks/{id}`                       | Xóa một công việc.                                                            |
| 4   | DELETE | `/users/{id}`                       | Xóa một người dùng khỏi hệ thống.                                             |
| 5   | GET    | `/tasks?priority=high`              | Tìm các công việc có mức độ ưu tiên là `high`.                                |
| 6   | GET    | `/tasks?priority=high&assignedTo=1` | Tìm các công việc có độ ưu tiên là `high` và được giao cho user có id là `1`. |
| 7   | GET    | `/users/{id}/tasks`                 | Liệt kê toàn bộ công việc của một người dùng.                                 |
| 8   | POST   | `/tasks/{taskId}/assign/{userId}`   | Gắn công việc cho người dùng.                                                 |
