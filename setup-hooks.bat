@echo off
echo Copying Git hooks...

REM Tạo thư mục hooks nếu chưa có
IF NOT EXIST ".git\hooks" (
    echo Error: .git\hooks folder not found. Make sure you're inside a Git repo.
    exit /b 1
)

copy hooks\commit-msg .git\hooks\commit-msg > nul

copy hooks\post-checkout .git\hooks\post-checkout > nul

echo Git hook installed successfully!
pause