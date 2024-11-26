CREATE DATABASE FitTogether;
USE FitTogether;

-- 사용자 테이블 (users)
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME ON UPDATE CURRENT_TIMESTAMP
);

-- 게시글 테이블 (posts)
CREATE TABLE posts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    description TEXT NOT NULL,
    image_url VARCHAR(2083),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 좋아요 테이블 (likes)
CREATE TABLE likes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    post_id INT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE
);

-- 인덱스 생성 (효율적인 검색을 위해)
CREATE INDEX idx_user_id ON posts(user_id);
CREATE INDEX idx_post_id ON likes(post_id);



-- 사용자 테이블에 더미 데이터 추가 (users)
INSERT INTO users (username, email, password, created_at, updated_at) VALUES
('user1', 'user1@example.com', 'password1', NOW(), NOW()),
('user2', 'user2@example.com', 'password2', NOW(), NOW()),
('user3', 'user3@example.com', 'password3', NOW(), NOW());

-- 게시글 테이블에 더미 데이터 추가 (posts)
INSERT INTO posts (user_id, description, image_url, created_at, updated_at) VALUES
(1, '운동이 최고~', 'https://image.ytn.co.kr/general/jpg/2020/1130/202011300700017010_d.jpg', NOW(), NOW()),
(2, '오늘도 운동 완료', 'https://i.ibb.co/8zSyk1h/DALL-E-2024-11-26-21-36-21-A-realistic-depiction-of-a-person-taking-a-selfie-in-front-of-a-mirror-af.webp', NOW(), NOW()),
(3, '열심히 운동합시다~', 'https://i.ibb.co/4RBm0sf/DALL-E-2024-11-26-21-36-24-A-realistic-depiction-of-a-person-taking-a-selfie-in-front-of-a-mirror-af.webp', NOW(), NOW());

-- 좋아요 테이블에 더미 데이터 추가 (likes)
INSERT INTO likes (user_id, post_id, created_at) VALUES
(1, 2, NOW()),
(2, 1, NOW()),
(3, 1, NOW());

INSERT INTO users (username, email, password, created_at, updated_at) VALUES
('master', 'ssafy@ssafy.com', '1234', NOW(), NOW());

SELECT * FROM users;