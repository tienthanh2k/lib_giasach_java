# Tích hợp commit hook với dự án

## Bước 1: Bổ sung file check:
- Bổ sung file /hooks/commit-msg (Viết mẫu validate commit message)
- Bổ sung file setup-hooks.bat vào thư mục chính của dự án (copy file commit-msg vào thư mục .git/hooks

## Bước 2: Commit 2 file lên repo dự án

## Bước 3: Khi dev clone dự án về thực hiện chạy file setup-hooks.bat

## Bước 4: Khi dev thực hiện 1 commit, hệ thống sẽ check nội dung commit message có đúng cú pháp commit không

-> Đúng: Thực hiện commit thành công

-> Sai: Thực hiện báo lỗi, hiển thị nội dung:

❌ Commit message không đúng chuẩn Conventional Commit!

✅ Ví dụ: VNPMO-123: thêm chức năng đăng nhập

✅ Ví dụ: feature: something

✅ Ví dụ: common: update readme

✅ Ví dụ: uat: deploy uat test


## Đánh giá:

### Ưu điểm:

- Set up đơn giản, không cần cài thêm plugin gì

- Áp dụng được cho tất cả các dự án (Không phân biệt ngôn ngữ)

### Nhược điểm:

- Yêu cầu chạy file setup-hooks.bat khi clone về, nếu không chạy sẽ không check được nội dung commit